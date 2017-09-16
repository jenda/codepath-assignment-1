package com.codepath.flicks.adapters;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.flicks.R;
import com.codepath.flicks.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

import static android.R.attr.resource;

/**
 * Created by jan_spidlen on 9/16/17.
 */

public class MovieArrayAdapter extends ArrayAdapter<Movie> {
    public MovieArrayAdapter(@NonNull Context context, @NonNull List<Movie> objects) {
        super(context, android.R.layout.simple_list_item_1, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Movie movie = getItem(position);

        if (convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            convertView = layoutInflater.inflate(R.layout.movie_item, parent, false);
        }
        ImageView posterImageView = (ImageView) convertView.findViewById(R.id.posterImageView);
        posterImageView.setImageResource(0);

        TextView titleTextView = (TextView) convertView.findViewById(R.id.titleTextView);
        TextView overviewTextView = (TextView) convertView.findViewById(R.id.overviewTextView);

        titleTextView.setText(movie.getOriginalTitle());
        overviewTextView.setText(movie.getOverview());

        Picasso.with(getContext()).load(movie.getPosterPath()).into(posterImageView);
        return convertView;
    }
}
