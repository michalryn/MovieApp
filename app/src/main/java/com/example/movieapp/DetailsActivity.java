package com.example.movieapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.movieapp.Adapters.CastRecyclerAdapter;
import com.example.movieapp.Listeners.OnDetailsApiListener;
import com.example.movieapp.Models.DetailsApiResponse;
import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {

    TextView textViewMovieName, textViewMovieReleased, textViewMovieRuntime, textViewMovieRating, textViewMovieVotes, textViewMoviePlot;
    ImageView imageViewMoviePoster;
    RecyclerView recyclerViewMovieCast;
    CastRecyclerAdapter adapter;
    MovieService service;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        textViewMovieName = findViewById(R.id.textView_movie_name);
        textViewMovieReleased = findViewById(R.id.textView_movie_released);
        textViewMovieRuntime = findViewById(R.id.textView_movie_runtime);
        textViewMovieRating = findViewById(R.id.textView_movie_rating);
        textViewMovieVotes = findViewById(R.id.textView_movie_votes);
        textViewMoviePlot = findViewById(R.id.textView_movie_plot);
        imageViewMoviePoster = findViewById(R.id.imageView_movie_poster);
        recyclerViewMovieCast = findViewById(R.id.recycler_movie_cast);

        service = new MovieService(this);

        String movie_id = getIntent().getStringExtra("movie_data");

        dialog = new ProgressDialog(this);
        dialog.setTitle("Please wait...");
        dialog.show();

        service.searchMovieDetails(listener, movie_id);
    }
    
    private OnDetailsApiListener listener = new OnDetailsApiListener() {
        @Override
        public void onResponse(DetailsApiResponse response) {
            dialog.dismiss();
            if (response.equals(null)) {
                Toast.makeText(DetailsActivity.this, "Error!", Toast.LENGTH_SHORT).show();
            }
            showResults(response);
        }

        @Override
        public void onError(String message) {
            dialog.dismiss();
            Toast.makeText(DetailsActivity.this, "Error!", Toast.LENGTH_SHORT).show();
        }
    };

    private void showResults(DetailsApiResponse response) {
        textViewMovieName.setText(response.getTitle());
        textViewMovieReleased.setText("Year Released: " + response.getYear());
        textViewMovieRuntime.setText("Length: " + response.getRuntimeStr());
        textViewMovieRating.setText("Rating: " + response.getImDbRating());
        textViewMovieVotes.setText(response.getImDbRatingVotes() + " Votes");
        textViewMoviePlot.setText(response.getPlot());

        try {
            Picasso.get().load(response.getImage()).into(imageViewMoviePoster);
        } catch (Exception e){
            e.printStackTrace();
        }

        recyclerViewMovieCast.setHasFixedSize(true);
        recyclerViewMovieCast.setLayoutManager(new GridLayoutManager(this, 1));
        adapter = new CastRecyclerAdapter(this, response.getActorList());
        recyclerViewMovieCast.setAdapter(adapter);
    }
}