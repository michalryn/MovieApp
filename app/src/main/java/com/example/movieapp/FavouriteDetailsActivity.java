package com.example.movieapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.movieapp.Adapters.CastRecyclerAdapter;
import com.example.movieapp.Data.MovieModel;
import com.example.movieapp.Data.MovieViewModel;
import com.example.movieapp.Models.DetailsApiResponse;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

public class FavouriteDetailsActivity extends AppCompatActivity {

    TextView textViewMovieName, textViewMovieReleased, textViewMovieRuntime, textViewMovieRating, textViewMovieVotes, textViewMoviePlot;
    ImageView imageViewMoviePoster;
    FloatingActionButton floatingActionButtonDeleteMovie;
    private MovieViewModel mMovieViewModel;
    private MovieModel movie;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite_details);

        mMovieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);
        textViewMovieName = findViewById(R.id.textView_movie_name);
        textViewMovieReleased = findViewById(R.id.textView_movie_released);
        textViewMovieRuntime = findViewById(R.id.textView_movie_runtime);
        textViewMovieRating = findViewById(R.id.textView_movie_rating);
        textViewMovieVotes = findViewById(R.id.textView_movie_votes);
        textViewMoviePlot = findViewById(R.id.textView_movie_plot);
        imageViewMoviePoster = findViewById(R.id.imageView_movie_poster);
        floatingActionButtonDeleteMovie = findViewById(R.id.floatingActionButton_delete_movie);

        String movie_id = getIntent().getStringExtra("movie_data");


        mMovieViewModel.getMovie(movie_id).observe(this, movieModel -> {
            movie = movieModel;
            showResults(movieModel);
            floatingActionButtonDeleteMovie.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    delete(movie.getImbdId());
                }
            });
        });

        //showResults(movie);

        /*floatingActionButtonDeleteMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delete(movie.getImbdId());
            }
        });*/
    }

    private void showResults(MovieModel movieModel) {
        if(movieModel != null) {
            textViewMovieName.setText(movieModel.getTitle());
            textViewMovieReleased.setText("Year Released: " + movieModel.getYear());
            textViewMovieRuntime.setText("Length: " + movieModel.getRuntimeStr());
            textViewMovieRating.setText("Rating: " + movieModel.getImDbRating());
            textViewMovieVotes.setText(movieModel.getImDbRatingVotes() + " Votes");
            textViewMoviePlot.setText(movieModel.getPlot());

            try {
                Picasso.get().load(movieModel.getImage()).into(imageViewMoviePoster);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private void delete(String id) {
        mMovieViewModel.deleteMovie(id);
        setResult(Activity.RESULT_OK);
        FavouriteDetailsActivity.this.finish();
    }
}