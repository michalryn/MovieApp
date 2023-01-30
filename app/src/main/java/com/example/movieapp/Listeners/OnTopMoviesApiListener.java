package com.example.movieapp.Listeners;

import com.example.movieapp.Models.TopMoviesList;

public interface OnTopMoviesApiListener {
    void onResponse(TopMoviesList response);
    void onError(String message);
}
