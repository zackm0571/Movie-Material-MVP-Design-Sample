package com.zackmatthews.mvp_sample.models;

import android.graphics.Bitmap;

import com.zackmatthews.mvp_sample.R;

/**
 * Created by zmatthews on 3/14/18.
 */

public class GenericItem {
    public static boolean useDefaultImg = true;
    public static int defaultImgRes = R.drawable.ic_launcher_background;

    private String title;
    private Bitmap img;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Bitmap getImg() {
        return img;
    }

    public void setImg(Bitmap img) {
        this.img = img;
    }
}
