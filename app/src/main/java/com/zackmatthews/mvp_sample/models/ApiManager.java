package com.zackmatthews.mvp_sample.models;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by zmatthews on 3/15/18.
 */

public class ApiManager extends ApiContract{
    private static final String jsonUrl = "https://zackmatthews.com/movies.json";
    private static ApiManager instance;
    public static ApiManager getInstance(){

        if(instance == null) instance = new ApiManager();
        return instance;
    }

    public void getJson(Context context, Response.Listener<JSONArray> listener, Response.ErrorListener errorListener){
        getQueue(context).add(getRequest(listener, errorListener));
    }

    public void getImage(Context context, String url, Response.Listener<Bitmap> bmpListener, Response.ErrorListener errorListener){
        getQueue(context).add(getImageRequest(url, bmpListener, errorListener));
    }

    private ImageRequest getImageRequest(String url, Response.Listener<Bitmap> bmpListener, Response.ErrorListener errorListener){
        return new ImageRequest(url, bmpListener, 240, 240, ImageView.ScaleType.FIT_CENTER, Bitmap.Config.ARGB_8888, errorListener);
    }
    private JsonArrayRequest getRequest(Response.Listener<JSONArray> listener, Response.ErrorListener errorListener){
        return new JsonArrayRequest(jsonUrl, listener, errorListener);
    }

}
