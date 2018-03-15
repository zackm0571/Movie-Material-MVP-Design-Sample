package com.zackmatthews.mvp_sample.models;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by zmatthews on 3/15/18.
 */

public class ApiManager {
    private static final String jsonUrl = "https://zackmatthews.com/movies.json";
    private static ApiManager instance;
    private RequestQueue queue;
    public static ApiManager getInstance(){

        if(instance == null) instance = new ApiManager();
        return instance;
    }

    public void getJson(Context context, Response.Listener<JSONArray> listener, Response.ErrorListener errorListener){
        getQueue(context).add(getRequest(listener, errorListener));
    }

    private JsonArrayRequest getRequest(Response.Listener<JSONArray> listener, Response.ErrorListener errorListener){
        return new JsonArrayRequest(jsonUrl, listener, errorListener);
    }

    private RequestQueue getQueue(Context context) {
        if(queue == null) {
            queue = Volley.newRequestQueue(context);
            queue.getCache().clear();
            queue.start();
        }
        return queue;
    }
}
