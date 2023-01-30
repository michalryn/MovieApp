package com.example.movieapp.Data;

import android.content.Context;

import androidx.room.AutoMigration;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {MovieModel.class}, exportSchema = false, version = 2)
//@Database(entities = {MovieModel.class}, exportSchema = true, version = 2, autoMigrations = { @AutoMigration(from = 1, to = 2)})
public abstract class AppDatabase extends RoomDatabase {

    public abstract MovieDao movieDao();

    private static volatile AppDatabase instance = null;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static AppDatabase getInstance(final Context context) {
        if(instance == null) {
            synchronized (AppDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "MovieAppDB")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }

        return instance;
    }

}
