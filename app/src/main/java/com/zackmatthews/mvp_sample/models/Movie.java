package com.zackmatthews.mvp_sample.models;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.zackmatthews.mvp_sample.R;

/**
 * Created by zmatthews on 3/13/18.
 */

public class Movie extends GenericItem{
    public static final String YEAR_KEY = "Year";
    public static final String DIRECTOR_KEY = "Director";
    public static final String PLOT_KEY = "Plot";
    public static final String RATED_KEY = "Rated";
    public static final String GENRE_KEY="Genre";
    public static final String RUNTIME_KEY="Runtime";
    public static final String ACTORS_KEY="Actors";


    private String year="", director="", img_url="", plot="", rated="", genre="", runtime="", actors="";

    public String getYear() {
        if(year == null)  year = "";
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDirector() {
        if(director == null) director = "";
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getImg_url() {
        if(img_url == null) img_url = "";
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getPlot() {
        if(plot == null) plot = "";
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getRated() {
        if(rated == null) rated = "";
        return rated;
    }

    public void setRated(String rated) {
        this.rated = rated;
    }

    public String getGenre() {
        if(genre == null) genre = "";
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getRuntime() {
        if(runtime == null) runtime = "";
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getActors() {
        if(actors == null) actors = "";
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }


    public static Movie fromMovie(Movie _movie){
        Movie newMovie = _movie;
        return newMovie;
    }

}
