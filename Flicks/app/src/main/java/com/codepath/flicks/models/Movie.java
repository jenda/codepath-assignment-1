package com.codepath.flicks.models;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by jan_spidlen on 9/16/17.
 */

public class Movie {

    public String getPosterPath() {
        return String.format("https://image.tmdb.org/t/p/w342/s%", posterPath);
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getOverview() {
        return overview;
    }

    String posterPath;
    String originalTitle;
    String overview;

    public Movie(JSONObject jsonObject) throws JSONException {
        this.posterPath = jsonObject.getString("poster_path");
        this.originalTitle = jsonObject.getString("origina_title");
        this.overview = jsonObject.getString("overview");
    }
}
