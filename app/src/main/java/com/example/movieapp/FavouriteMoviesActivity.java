package com.example.movieapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.example.movieapp.Adapters.FavouritesRecyclerAdapter;
import com.example.movieapp.Data.AppDatabase;
import com.example.movieapp.Data.MovieModel;
import com.example.movieapp.Data.MovieViewModel;
import com.example.movieapp.Listeners.OnMovieClickListener;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FavouriteMoviesActivity extends AppCompatActivity implements OnMovieClickListener {

    //AppDatabase database;
    RecyclerView recyclerViewFavourites;
    FavouritesRecyclerAdapter adapter;
    //private ExecutorService executorService;
    //private Handler handler;
    private MovieViewModel mMovieViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite_movies);

        recyclerViewFavourites = findViewById(R.id.recycler_view_favourites);
        recyclerViewFavourites.setHasFixedSize(true);
        recyclerViewFavourites.setLayoutManager(new GridLayoutManager(FavouriteMoviesActivity.this, 2));
        mMovieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);
        //adapter = new FavouritesRecyclerAdapter(this, mMovieViewModel.getMovies(), this);
        mMovieViewModel.getMovies().observe(this, movieModels -> {
            adapter = new FavouritesRecyclerAdapter(this, movieModels, this);
            recyclerViewFavourites.setAdapter(adapter);
        });
        recyclerViewFavourites.setAdapter(adapter);
        //database = AppDatabase.getInstance(this);
        //executorService = Executors.newSingleThreadExecutor();
        //handler = new Handler(Looper.getMainLooper());


        //showResult(favouritesList);
    }

    private void showResult(List<MovieModel> list) {
        recyclerViewFavourites.setHasFixedSize(true);
        recyclerViewFavourites.setLayoutManager(new GridLayoutManager(FavouriteMoviesActivity.this, 2));
        adapter = new FavouritesRecyclerAdapter(this, list, this);
        recyclerViewFavourites.setAdapter(adapter);
    }

    @Override
    public void onMovieClicked(String id) {
        startActivity(new Intent(FavouriteMoviesActivity.this, FavouriteDetailsActivity.class)
                .putExtra("movie_data", id));
    }

    /*private void getFavourites() {

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                String name = "insertDataTask";
                database.movieDao().insert(movieModel);

                for (int i = 0; i < database.movieDao().getAllMovies().size(); i++) {
                    if(database.movieDao().getAllMovies().get(i).getImbdId().equals(movieModel.imbdId)) {
                        isInserted[0] = true;
                    }
                }

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if(isInserted[0]) {
                            Toast.makeText(DetailsActivity.this, "Movie added", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(DetailsActivity.this, "Operation failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }*/
}