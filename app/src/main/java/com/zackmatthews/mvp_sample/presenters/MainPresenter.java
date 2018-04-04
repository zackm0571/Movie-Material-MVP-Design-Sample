package com.zackmatthews.mvp_sample.presenters;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.zackmatthews.mvp_sample.models.ApiManager;
import com.zackmatthews.mvp_sample.models.Movie;
import com.zackmatthews.mvp_sample.models.OmdbApiManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by zmatthews on 3/14/18.
 */

public class MainPresenter {
    public interface MainContract{
        void onDataLoaded(List<Movie> data);
        void updateEntry(Movie movie);
        int getDataSize();
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


                for (int i = 0; i < ((JSONArray)response).length(); i++) {
                    try {
                        JSONObject obj = ((JSONArray)response).getJSONObject(i);

                        Movie movie = new Movie();
                        movie.setTitle(obj.optString(Movie.TITLE_KEY));
                        movie.setDirector(obj.optString(Movie.DIRECTOR_KEY));
                        movie.setYear(obj.optString(Movie.YEAR_KEY));
                        movie.setImg_url(obj.optString(Movie.IMG_URL_KEY));
                        movie.setId(String.valueOf(contract.getDataSize()));

                        JsonMovieDataListener movieListener = new JsonMovieDataListener(movie);
                        OmdbApiManager.getInstance().getMovieDetailsForTitle(contract.getContext(), movie.getTitle(), movieListener, movieListener);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
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
            Movie newMovie = Movie.fromMovie(movie);
            newMovie.setImg(response);
            contract.updateEntry(newMovie);
        }
    }

    class JsonMovieDataListener implements Response.Listener<JSONObject>, Response.ErrorListener{

        private Movie tmp;
        public JsonMovieDataListener(Movie movie){
            this.tmp = movie;
        }

        @Override
        public void onErrorResponse(VolleyError error) {
            Log.e(MainPresenter.class.getSimpleName(), error.getLocalizedMessage());
            Toast.makeText(contract.getContext(), String.format("JSON LOAD ERROR %s", error.getLocalizedMessage()), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onResponse(JSONObject response) {
            Movie movie = Movie.fromMovie(tmp);
            movie.setDirector(response.optString(Movie.DIRECTOR_KEY));
            movie.setRuntime(response.optString(Movie.RUNTIME_KEY));
            movie.setRated(response.optString(Movie.RATED_KEY));
            movie.setPlot(response.optString(Movie.PLOT_KEY));
            movie.setActors(response.optString(Movie.ACTORS_KEY));
            movie.setGenre(response.optString(Movie.GENRE_KEY));
            movie.setYear(response.optString(Movie.YEAR_KEY));
            contract.updateEntry(movie);
        }
    }
}
