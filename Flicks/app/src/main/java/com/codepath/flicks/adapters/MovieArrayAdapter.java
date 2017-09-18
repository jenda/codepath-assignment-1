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

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;


/**
 * Created by jan_spidlen on 9/16/17.
 */

public class MovieArrayAdapter extends ArrayAdapter<Movie> {

    static class ViewHolder {
        @Nullable @BindView(R.id.posterImageView) ImageView posterImageView;
        @Nullable @BindView(R.id.backdropImage) ImageView backdropImageView;
        @BindView(R.id.titleTextView) TextView titleTextView;
        @BindView(R.id.overviewTextView) TextView overviewTextView;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
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
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            convertView = layoutInflater.inflate(R.layout.movie_item, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder)convertView.getTag();
        }


        viewHolder.titleTextView.setText(movie.getOriginalTitle());
        viewHolder.overviewTextView.setText(movie.getOverview());


        if (viewHolder.posterImageView != null) {
            viewHolder.posterImageView.setImageResource(0);
            Picasso.with(getContext())
                    .load(movie.getPosterPath())
                    .placeholder(android.R.drawable.ic_menu_report_image)
                    .transform(new RoundedCornersTransformation(10, 10))
                    .into(viewHolder.posterImageView);
        } else if (viewHolder.backdropImageView != null) {
            viewHolder.backdropImageView.setImageResource(0);
            Picasso.with(getContext())
                    .load(movie.getBackdropPath())
                    .resize(1000, 0)
                    .placeholder(android.R.drawable.ic_menu_report_image)
                    .transform(new RoundedCornersTransformation(10, 10))
                    .into(viewHolder.backdropImageView);
        }
        return convertView;
    }
}
