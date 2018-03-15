package com.zackmatthews.mvp_sample.models;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.zackmatthews.mvp_sample.R;

/**
 * Created by zmatthews on 3/13/18.
 */

public class Movie extends GenericItem{
    public static final String YEAR_KEY = "year";
    public static final String DIRECTOR_KEY = "director";

    private String year, director;

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }
}
