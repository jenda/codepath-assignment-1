package com.codepath.flicks.controllers;

import android.util.Log;
import android.widget.Toast;

import com.codepath.flicks.adapters.MovieArrayAdapter;
import com.codepath.flicks.models.Movie;
import com.codepath.flicks.models.Movies;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by jan_spidlen on 9/17/17.
 */

public class Controller {

    final MovieArrayAdapter movieArrayAdapter;
    final List<Movie> movies;
    final AsyncHttpClient client = new AsyncHttpClient();
    String url = "https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";

    public Controller(List<Movie> movies, MovieArrayAdapter movieArrayAdapter) {
        this.movies = movies;
        this.movieArrayAdapter = movieArrayAdapter;
    }

    public void pullForNewData() {
        client.get(url, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                JSONArray movieJSONResults = null;

                try {
                    movieJSONResults = response.getJSONArray("results");
                    Log.d("jenda", "movieJSONResults: " + movieJSONResults.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                movies.addAll(Movies.fromJsonArray(movieJSONResults));
                movieArrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        });
    }

}
