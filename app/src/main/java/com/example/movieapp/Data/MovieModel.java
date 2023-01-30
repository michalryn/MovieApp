package com.example.movieapp.Data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "movies")
public class MovieModel {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String imbdId;
    public String title;
    public String year;
    public String image;
    public String runtimeStr;
    public String imDbRating;
    public String imDbRatingVotes;
    public String plot;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImbdId() {
        return imbdId;
    }

    public void setImbdId(String imbdId) {
        this.imbdId = imbdId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getRuntimeStr() {
        return runtimeStr;
    }

    public void setRuntimeStr(String runtimeStr) {
        this.runtimeStr = runtimeStr;
    }

    public String getImDbRating() {
        return imDbRating;
    }

    public void setImDbRating(String imDbRating) {
        this.imDbRating = imDbRating;
    }

    public String getImDbRatingVotes() {
        return imDbRatingVotes;
    }

    public void setImDbRatingVotes(String imDbRatingVotes) {
        this.imDbRatingVotes = imDbRatingVotes;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }
}
