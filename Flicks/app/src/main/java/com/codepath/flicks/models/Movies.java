package com.codepath.flicks.models;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jan_spidlen on 9/16/17.
 */

public class Movies {
    public static List<Movie> fromJsonArray(JSONArray array) {
        List<Movie> results = new ArrayList<>();

        for(int i = 0; i < array.length(); i++) {
            try {
                results.add(new Movie(array.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return results;
    }
}
