package com.zackmatthews.mvp_sample.presenters;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.Toast;

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

public class MainPresenter {
    public interface MainContract{
        void onDataLoaded(List<Movie> data);
        void updateEntry(Movie _old, Movie _new);
        Context getContext();
    }
    protected MainContract contract;
    private JsonListener jsonListener = new JsonListener();

    public MainPresenter(MainContract contract){
        this.contract = contract;
    }

    public void refreshData(){
        ApiManager.getInstance().getJson(contract.getContext(), jsonListener, jsonListener);
    }
    public void getBitmapForMovie(Movie movie){
        BitmapListener bmpListener = new BitmapListener(movie);
        ApiManager.getInstance().getImage(contract.getContext(), movie.getImg_url(), bmpListener, bmpListener);
    }

    class JsonListener implements Response.Listener<JSONArray>, Response.ErrorListener{
        @Override
        public void onResponse(JSONArray response) {

            if(response.getClass().equals(JSONArray.class)) {
                Log.d(MainPresenter.class.getSimpleName(), response.toString());
                List<Movie> data = new ArrayList<>();

                for (int i = 0; i < ((JSONArray)response).length(); i++) {
                    try {
                        JSONObject obj = ((JSONArray)response).getJSONObject(i);

                        Movie movie = new Movie();
                        movie.setTitle(obj.optString(Movie.TITLE_KEY));
                        movie.setDirector(obj.optString(Movie.DIRECTOR_KEY));
                        movie.setYear(obj.optString(Movie.YEAR_KEY));
                        movie.setImg_url(obj.optString(Movie.IMG_URL_KEY));
                        BitmapListener bmpListener = new BitmapListener(movie);
                        ApiManager.getInstance().getImage(contract.getContext(), movie.getImg_url(), bmpListener, bmpListener);
                        data.add(movie);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                contract.onDataLoaded(data);
            }
        }

        @Override
        public void onErrorResponse(VolleyError error) {
            Log.e(MainPresenter.class.getSimpleName(), error.getLocalizedMessage());
            Toast.makeText(contract.getContext(), String.format("JSON LOAD ERROR %s", error.getLocalizedMessage()), Toast.LENGTH_SHORT).show();
        }
    }

    class BitmapListener implements Response.Listener<Bitmap>, Response.ErrorListener{
        Movie movie;
        public BitmapListener(Movie movie){
            this.movie = movie;
        }
        @Override
        public void onErrorResponse(VolleyError error) {

        }

        @Override
        public void onResponse(Bitmap response) {
            Movie newMovie = new Movie();
            newMovie.setTitle(movie.getTitle());
            newMovie.setYear(movie.getYear());
            newMovie.setDirector(movie.getDirector());
            newMovie.setImg_url(movie.getImg_url());
            newMovie.setImg(response);
            contract.updateEntry(movie, newMovie);
        }
    }
}
