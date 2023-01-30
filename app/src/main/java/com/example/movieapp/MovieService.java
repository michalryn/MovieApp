package com.example.movieapp;

import android.content.Context;
import android.widget.Toast;

import com.example.movieapp.Listeners.OnDetailsApiListener;
import com.example.movieapp.Listeners.OnSearchApiListener;
import com.example.movieapp.Listeners.OnTopMoviesApiListener;
import com.example.movieapp.Models.DetailsApiResponse;
import com.example.movieapp.Models.SearchApiResponse;
import com.example.movieapp.Models.TopMovie;
import com.example.movieapp.Models.TopMoviesList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public class MovieService {
    Context context;
    String apiKey = "k_78zl3f66";
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://imdb-api.com/en/API/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public MovieService(Context context) {
        this.context = context;
    }

    public void searchMovies(OnSearchApiListener listener, String movie_name) {
        getMovies getMovies = retrofit.create(MovieService.getMovies.class);
        Call<SearchApiResponse> call = getMovies.callMovies(movie_name);

        call.enqueue(new Callback<SearchApiResponse>() {
            @Override
            public void onResponse(Call<SearchApiResponse> call, Response<SearchApiResponse> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(context, "Couldn't fetch data", Toast.LENGTH_SHORT).show();
                    return;
                }
                listener.onResponse(response.body());
            }

            @Override
            public void onFailure(Call<SearchApiResponse> call, Throwable t) {
                listener.onError(t.getMessage());
            }
        });
    }

    public void searchMovieDetails(OnDetailsApiListener listener, String movie_id) {
        getMovieDetails getMovieDetails = retrofit.create(MovieService.getMovieDetails.class);
        Call<DetailsApiResponse> call = getMovieDetails.callMovieDetails(movie_id);

        call.enqueue(new Callback<DetailsApiResponse>() {
            @Override
            public void onResponse(Call<DetailsApiResponse> call, Response<DetailsApiResponse> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(context, "Couldn't fetch data", Toast.LENGTH_SHORT).show();
                    return;
                }
                listener.onResponse(response.body());
            }

            @Override
            public void onFailure(Call<DetailsApiResponse> call, Throwable t) {
                listener.onError(t.getMessage());
            }
        });
    }

    public void searchTopMovies(OnTopMoviesApiListener listener) {
        getTop250Movies getTop250Movies = retrofit.create(MovieService.getTop250Movies.class);
        Call<TopMoviesList> call = getTop250Movies.callTopMovies();

        call.enqueue(new Callback<TopMoviesList>() {
            @Override
            public void onResponse(Call<TopMoviesList> call, Response<TopMoviesList> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(context, "Couldn't fetch data", Toast.LENGTH_SHORT).show();
                    return;
                }
                listener.onResponse(response.body());
            }

            @Override
            public void onFailure(Call<TopMoviesList> call, Throwable t) {
                listener.onError(t.getMessage());
            }
        });
    }

    public interface getMovies {
        @Headers({
                "Accept: application/json"
        })
        @GET("SearchMovie/k_78zl3f66/{movie_name}")
        Call<SearchApiResponse> callMovies(
                @Path("movie_name") String movie_name
        );
    }

    public interface getMovieDetails {
        @Headers({
                "Accept: application/json"
        })
        @GET("Title/k_78zl3f66/{movie_id}")
        Call<DetailsApiResponse> callMovieDetails(
                @Path("movie_id") String movie_id
        );
    }

    public interface getTop250Movies {
        @Headers({
                "Accept: application/json"
        })
        @GET("Top250Movies/k_78zl3f66")
        Call<TopMoviesList> callTopMovies();
    }
}
