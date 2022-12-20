package com.example.movieapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.movieapp.Adapters.HomeRecyclerAdapter;
import com.example.movieapp.Listeners.OnMovieClickListener;
import com.example.movieapp.Listeners.OnSearchApiListener;
import com.example.movieapp.Models.SearchApiResponse;
import com.example.movieapp.Models.SearchArrayObject;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity implements OnMovieClickListener {
    RecyclerView recyclerViewHome;
    HomeRecyclerAdapter adapter;
    MovieService service;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        recyclerViewHome = findViewById(R.id.recycler_view_home);
        dialog = new ProgressDialog(this);
        service = new MovieService(this);

        String search_query = getIntent().getStringExtra("search_query");

        dialog = new ProgressDialog(this);
        dialog.setTitle("Please wait...");
        dialog.show();

        service.searchMovies(listener, search_query);
    }

    private final OnSearchApiListener listener = new OnSearchApiListener() {
        @Override
        public void onResponse(SearchApiResponse response) {
            dialog.dismiss();
            if (response == null) {
                Toast.makeText(SearchActivity.this, "No data available", Toast.LENGTH_SHORT).show();
                return;
            }
            showResult(response);
        }

        @Override
        public void onError(String message) {
            dialog.dismiss();
            Toast.makeText(SearchActivity.this, "An error occurred!", Toast.LENGTH_SHORT).show();
        }
    };

    private void showResult(SearchApiResponse response) {
        recyclerViewHome.setHasFixedSize(true);
        recyclerViewHome.setLayoutManager(new GridLayoutManager(SearchActivity.this, 2));
        adapter = new HomeRecyclerAdapter(this, response.getResults(), this);
        recyclerViewHome.setAdapter(adapter);
    }

    @Override
    public void onMovieClicked(String id) {
        startActivity(new Intent(SearchActivity.this, DetailsActivity.class)
                .putExtra("movie_data", id));
    }
}