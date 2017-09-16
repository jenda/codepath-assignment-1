package com.codepath.flicks;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.codepath.flicks.models.Movie;
import com.codepath.flicks.models.Movies;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class MovieActivity extends AppCompatActivity {

    List<Movie> movies;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        movies = new ArrayList<>();

        String url = "https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";

        AsyncHttpClient client = new AsyncHttpClient();
        Toast.makeText(getApplicationContext(), "geat", Toast.LENGTH_LONG).show();
        client.get(url, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Toast.makeText(getApplicationContext(), "Succcess", Toast.LENGTH_LONG).show();
                JSONArray movieJSONResults = null;

                try {
                    movieJSONResults = response.getJSONArray("results");
                    Log.d("jenda", "movieJSONResults: " + movieJSONResults.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                movies = Movies.fromJsonArray(movieJSONResults);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Toast.makeText(getApplicationContext(), "failue", Toast.LENGTH_LONG).show();
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        });
    }
}
