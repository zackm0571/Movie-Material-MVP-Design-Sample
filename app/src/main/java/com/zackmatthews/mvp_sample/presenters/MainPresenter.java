package com.zackmatthews.mvp_sample.presenters;

import android.content.Context;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.zackmatthews.mvp_sample.models.ApiManager;
import com.zackmatthews.mvp_sample.models.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zmatthews on 3/14/18.
 */

public class MainPresenter implements Response.Listener<JSONArray>, Response.ErrorListener{
    public interface MainContract{
        void onDataLoaded(List<Movie> data);
        Context getContext();
    }
    public MainContract contract;

    public MainPresenter(MainContract contract){
        this.contract = contract;
    }

    public void refreshData(){
        ApiManager.getInstance().getJson(contract.getContext(), this, this);
    }

    @Override
    public void onResponse(JSONArray response) {
            Log.d(MainPresenter.class.getSimpleName(), response.toString());
            List<Movie> data = new ArrayList<>();

            for(int i = 0; i < response.length(); i++){
                try {
                    JSONObject obj = response.getJSONObject(i);

                    Movie movie = new Movie();
                    movie.setTitle(obj.optString(Movie.TITLE_KEY));
                    movie.setDirector(obj.optString(Movie.DIRECTOR_KEY));
                    movie.setYear(obj.optString(Movie.YEAR_KEY));
                    data.add(movie);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            contract.onDataLoaded(data);
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }
}
