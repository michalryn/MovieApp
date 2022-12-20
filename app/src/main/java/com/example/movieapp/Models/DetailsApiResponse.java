package com.example.movieapp.Models;

import java.util.List;

public class DetailsApiResponse {
    String id = "";
    String title = "";
    String originalTitle = "";
    String fullTitle = "";
    String type = "";
    String year = "";
    String image = "";
    String releaseDate = "";
    String runtimeMins = "";
    String runtimeStr = "";
    String plot = "";
    String plotLocal = "";
    String plotLocalIsRtl = "";
    String awards = "";
    String directors = "";
    List<Person> directorList = null;
    String writers = "";
    List<Person> writerList = null;
    String stars = "";
    List<Person> starList = null;
    List<Actor> actorList = null;
    List<Actor> fullCast = null;
    String genres = "";
    String companies = "";
    String contentRating = "";
    String imDbRating = "";
    String imDbRatingVotes = "";
    String metacriticRating = "";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getFullTitle() {
        return fullTitle;
    }

    public void setFullTitle(String fullTitle) {
        this.fullTitle = fullTitle;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getRuntimeMins() {
        return runtimeMins;
    }

    public void setRuntimeMins(String runtimeMins) {
        this.runtimeMins = runtimeMins;
    }

    public String getRuntimeStr() {
        return runtimeStr;
    }

    public void setRuntimeStr(String runtimeStr) {
        this.runtimeStr = runtimeStr;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getPlotLocal() {
        return plotLocal;
    }

    public void setPlotLocal(String plotLocal) {
        this.plotLocal = plotLocal;
    }

    public String getPlotLocalIsRtl() {
        return plotLocalIsRtl;
    }

    public void setPlotLocalIsRtl(String plotLocalIsRtl) {
        this.plotLocalIsRtl = plotLocalIsRtl;
    }

    public String getAwards() {
        return awards;
    }

    public void setAwards(String awards) {
        this.awards = awards;
    }

    public String getDirectors() {
        return directors;
    }

    public void setDirectors(String directors) {
        this.directors = directors;
    }

    public List<Person> getDirectorList() {
        return directorList;
    }

    public void setDirectorList(List<Person> directorList) {
        this.directorList = directorList;
    }

    public String getWriters() {
        return writers;
    }

    public void setWriters(String writers) {
        this.writers = writers;
    }

    public List<Person> getWriterList() {
        return writerList;
    }

    public void setWriterList(List<Person> writerList) {
        this.writerList = writerList;
    }

    public String getStars() {
        return stars;
    }

    public void setStars(String stars) {
        this.stars = stars;
    }

    public List<Person> getStarList() {
        return starList;
    }

    public void setStarList(List<Person> starList) {
        this.starList = starList;
    }

    public List<Actor> getActorList() {
        return actorList;
    }

    public void setActorList(List<Actor> actorList) {
        this.actorList = actorList;
    }

    public List<Actor> getFullCast() {
        return fullCast;
    }

    public void setFullCast(List<Actor> fullCast) {
        this.fullCast = fullCast;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public String getCompanies() {
        return companies;
    }

    public void setCompanies(String companies) {
        this.companies = companies;
    }

    public String getContentRating() {
        return contentRating;
    }

    public void setContentRating(String contentRating) {
        this.contentRating = contentRating;
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

    public String getMetacriticRating() {
        return metacriticRating;
    }

    public void setMetacriticRating(String metacriticRating) {
        this.metacriticRating = metacriticRating;
    }
}
