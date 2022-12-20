package com.example.movieapp.Listeners;

import com.example.movieapp.Models.DetailsApiResponse;

public interface OnDetailsApiListener {
    void onResponse(DetailsApiResponse response);
    void onError(String message);
}
