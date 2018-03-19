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

    private String year, director, img_url;

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

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }
}
