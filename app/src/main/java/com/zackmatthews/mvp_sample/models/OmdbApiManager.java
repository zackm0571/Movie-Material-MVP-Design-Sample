package com.zackmatthews.mvp_sample.models;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

/**
 * Created by zmatthews on 3/19/18.
 */

public class OmdbApiManager extends ApiContract{
    public static final String apiKey="c52dc8fd";
    public static final String apiUrl ="http://www.omdbapi.com/?i=tt3896198&apikey=%s&t=%s&plot=full";

    private static OmdbApiManager instance;
    private RequestQueue queue;

    public static OmdbApiManager getInstance() {
        if(instance == null) instance = new OmdbApiManager();
        return instance;
    }

    public void getMovieDetailsForTitle(Context context, String title, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener){
        getQueue(context).add(getMovieDetailsRequest(title, listener, errorListener));
    }

    private JsonObjectRequest getMovieDetailsRequest(String title, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener){
        return new JsonObjectRequest(String.format(apiUrl, apiKey, title), null, listener, errorListener);
    }
}
