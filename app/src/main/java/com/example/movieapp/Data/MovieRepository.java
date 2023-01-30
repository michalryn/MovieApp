package com.example.movieapp.Data;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class MovieRepository {

    private MovieDao mMovieDao;
    private LiveData<List<MovieModel>> mAllMovies;

    MovieRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        mMovieDao = db.movieDao();
        mAllMovies = mMovieDao.getAllMovies();
    }

    LiveData<List<MovieModel>> getFavouriteMovies() {
        return mAllMovies;
    }

    void insert(MovieModel movie) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            boolean exists = mMovieDao.movieExists(movie.imbdId);
            if(!exists) {
                mMovieDao.insert(movie);
            }
        });
    }

    LiveData<MovieModel> getMovie(String id) {
        return mMovieDao.getMovie(id);
    }

    void deleteMovie(String id) {
        new insertAsyncTask(mMovieDao).execute(id);
    }

    private static class insertAsyncTask extends AsyncTask<String, Void, Void> {

        private MovieDao mAsyncTaskDao;

        insertAsyncTask(MovieDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final String... params) {
            mAsyncTaskDao.deleteMovie(params[0]);
            return null;
        }
    }
}
