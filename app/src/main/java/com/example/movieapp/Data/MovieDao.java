package com.example.movieapp.Data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(MovieModel movie);

    @Query("select * from movies")
    LiveData<List<MovieModel>> getAllMovies();

    @Query("select * from movies where imbdId like :id")
    LiveData<MovieModel> getMovie(String id);

    @Query("select exists (select * from movies where imbdId like :id)")
    boolean movieExists(String id);

    @Query("delete from movies where imbdId like :id")
    void deleteMovie(String id);
}
