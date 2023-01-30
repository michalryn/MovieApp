package com.example.movieapp.Data;

import android.app.Application;
import android.graphics.Movie;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MovieViewModel extends AndroidViewModel {
    private MovieRepository mRepository;

    private final LiveData<List<MovieModel>> mAllMovies;
    //private final LiveData<MovieModel> mMovieModel = new LiveData<MovieModel>() {};
    public MovieViewModel(Application application) {
        super(application);
        mRepository = new MovieRepository(application);
        mAllMovies = mRepository.getFavouriteMovies();
    }

    public LiveData<List<MovieModel>> getMovies() {
        return mAllMovies;
    }

    public void insert(MovieModel movie) {mRepository.insert(movie);}

    public LiveData<MovieModel> getMovie(String id) {
        return mRepository.getMovie(id);
    }

    public void deleteMovie(String id) {
        mRepository.deleteMovie(id);
    }
}
