package com.codepath.flicks;

import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.codepath.flicks.adapters.MovieArrayAdapter;
import com.codepath.flicks.controllers.Controller;
import com.codepath.flicks.models.Movie;
import com.codepath.flicks.models.Movies;
import com.codepath.flicks.view.MovieInfoFragment;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;

public class MovieActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    List<Movie> movies;
    MovieArrayAdapter movieArrayAdapter;
    Controller controller;

    @BindView(R.id.moviesListView)ListView movieListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        ButterKnife.bind(this);

        movies = new ArrayList<>();
        movieArrayAdapter = new MovieArrayAdapter(this, movies);
        movieListView.setAdapter(movieArrayAdapter);
        controller = new Controller(movies, movieArrayAdapter);

        movieListView.setOnScrollListener(controller);
        movieListView.setOnItemClickListener(this);

        controller.pullForNewData();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Movie m = movieArrayAdapter.getItem(position);
        Log.d("jenda", m.getOriginalTitle());
//        MovieInfoFragment.newInstance(m).showDialog(m);

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        Fragment prev = getFragmentManager().findFragmentByTag("dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);

        // Create and show the dialog.
        DialogFragment newFragment = MovieInfoFragment.newInstance(m);
        newFragment.show(ft, "dialog");
    }
}
