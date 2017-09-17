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

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

import static android.R.attr.resource;
import static com.codepath.flicks.R.id.overviewTextView;
import static com.codepath.flicks.R.id.posterImageView;
import static com.codepath.flicks.R.id.titleTextView;

/**
 * Created by jan_spidlen on 9/16/17.
 */

public class MovieArrayAdapter extends ArrayAdapter<Movie> {

    static class ViewHolder {
        ImageView posterImageView;
        TextView titleTextView;
        TextView overviewTextView;
    }

    public MovieArrayAdapter(@NonNull Context context, @NonNull List<Movie> objects) {
        super(context, android.R.layout.simple_list_item_1, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Movie movie = getItem(position);
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            convertView = layoutInflater.inflate(R.layout.movie_item, parent, false);
            viewHolder.titleTextView = (TextView) convertView.findViewById(titleTextView);
            viewHolder.overviewTextView = (TextView) convertView.findViewById(overviewTextView);
            viewHolder.posterImageView = (ImageView) convertView.findViewById(posterImageView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        viewHolder.posterImageView.setImageResource(0);

        viewHolder.titleTextView.setText(movie.getOriginalTitle());
        viewHolder.overviewTextView.setText(movie.getOverview());

        Picasso.with(getContext())
                .load(movie.getPosterPath())
                .placeholder(android.R.drawable.ic_menu_report_image)
                .transform(new RoundedCornersTransformation(10, 10))
                .into(viewHolder.posterImageView);
        return convertView;
    }
}
