package com.example.movieapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.movieapp.Adapters.HomeRecyclerAdapter;
import com.example.movieapp.Listeners.OnMovieClickListener;
import com.example.movieapp.Listeners.OnSearchApiListener;
import com.example.movieapp.Models.SearchApiResponse;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    SearchView searchView;
    MovieService service;
    //TextView text;
    private SensorManager sensorManager;
    private int shake_count = 0;
    private float acceleration = 0f;
    private float currentAcceleration = 0f;
    private float lastAcceleration = 0f;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchView = findViewById(R.id.search_view);
        //text = findViewById(R.id.text_test);
        service = new MovieService(this);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor sensorShake = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(sensorEventListener, sensorShake, SensorManager.SENSOR_DELAY_NORMAL);

        acceleration = 10f;
        currentAcceleration = SensorManager.GRAVITY_EARTH;
        lastAcceleration = SensorManager.GRAVITY_EARTH;

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchView.clearFocus();
                startActivity(new Intent(MainActivity.this, SearchActivity.class)
                        .putExtra("search_query", query));
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    private final SensorEventListener sensorEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            if (sensorEvent != null) {
                float x_accl = sensorEvent.values[0];
                float y_accl = sensorEvent.values[1];
                float z_accl = sensorEvent.values[2];
                lastAcceleration = currentAcceleration;

                currentAcceleration = (float)Math.sqrt(x_accl * x_accl * y_accl * y_accl * 1);

                float delta = currentAcceleration - lastAcceleration;
                acceleration = acceleration * 0.9f + delta;

                if (shake_count > 15) {
                    String id = buildRandomId();
                    shake_count = 0;
                    //Toast.makeText(MainActivity.this, "Shake complete " + id, Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this, DetailsActivity.class)
                            .putExtra("movie_data", id));
                }

                if (acceleration > 25) {
                    shake_count++;
                    //text.setText("Shaking" + shake_count);

                }
                else {
                    //text.setText("NOT Shaking");
                }
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {
        }
    };

    private int getRandomId(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
        //return (int)((Math.random() * (max - min)) + min);
    }

    private String buildRandomId() {
        int min = 1, max = 9916880;
        int randomInt = getRandomId(min, max);
        String rawId = String.valueOf(randomInt);
        StringBuilder id = new StringBuilder(rawId);

        if (rawId.length() < 7) {
            for (int i = 0; i < 7 - rawId.length(); i++) {
                id.setCharAt(0, '0');
            }
        }

        id.insert(0, "tt");

        return id.toString();
    }
    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(sensorEventListener, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        sensorManager.unregisterListener(sensorEventListener);
        super.onPause();
    }
}