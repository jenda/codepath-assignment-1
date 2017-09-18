package com.codepath.flicks.controllers;

import android.util.Log;
import android.widget.AbsListView;
import android.widget.Toast;

import com.codepath.flicks.adapters.MovieArrayAdapter;
import com.codepath.flicks.models.Movie;
import com.codepath.flicks.models.Movies;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cz.msebera.android.httpclient.Header;

import static com.codepath.flicks.models.Movies.fromJsonArray;

/**
 * Created by jan_spidlen on 9/17/17.
 */

public class Controller implements AbsListView.OnScrollListener {

    final MovieArrayAdapter movieArrayAdapter;
    final List<Movie> movies;
    final AsyncHttpClient client = new AsyncHttpClient();
    final String urlTemplate = "https://api.themoviedb.org/3/movie/now_playing" +
            "?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed" +
            "&page=%s";

    int nextPage = 1;
    int totalPages = 0;
    final Set<Integer> alreadyConsumedMovies = new HashSet<>();

    public Controller(List<Movie> movies, MovieArrayAdapter movieArrayAdapter) {
        this.movies = movies;
        this.movieArrayAdapter = movieArrayAdapter;
    }

    public void pullForNewData() {
        if (nextPage == totalPages) {
            return;
        }
        final String url = String.format(urlTemplate, nextPage);
        client.get(url, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                JSONArray movieJSONResults = null;

                try {
                    movieJSONResults = response.getJSONArray("results");
                    nextPage = response.getInt("page") + 1;
                    totalPages = response.getInt("total_pages");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                List<Movie>  returnedMovies = Movies.fromJsonArray(movieJSONResults);
                for(Movie movie: returnedMovies) {
                    if (alreadyConsumedMovies.add(movie.getId())) {
                        movies.add(movie);
                    }
                }
                movieArrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        });
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (firstVisibleItem + visibleItemCount == totalItemCount) {
            this.pullForNewData();
        }
    }
}
