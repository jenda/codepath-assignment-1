package com.codepath.flicks.view;

import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.codepath.flicks.R;
import com.codepath.flicks.models.Movie;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

/**
 * Created by jan_spidlen on 9/17/17.
 */

public class MovieInfoFragment extends DialogFragment {

    static final String MOVIE_KEY = "movie";

    public static MovieInfoFragment newInstance(Movie movie) {
        MovieInfoFragment movieInfoFragment = new MovieInfoFragment();

        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putSerializable(MOVIE_KEY, movie);
        movieInfoFragment.setArguments(args);

        return movieInfoFragment;
    }

    private Movie movie;
    @BindView(R.id.titleTextView)TextView titleTextView;
    @BindView(R.id.overviewTextView)TextView overviewTextView;
    @BindView(R.id.backDropImageView)ImageView backDropImageView;
    @BindView(R.id.ratingBar)RatingBar ratingBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        movie = (Movie) getArguments().getSerializable(MOVIE_KEY);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.movie_info_fragment, container, false);
        ButterKnife.bind(this, v);

        titleTextView.setText(movie.getOriginalTitle());
        overviewTextView.setText(movie.getOverview());

        Picasso.with(getActivity())
                .load(movie.getBackdropPath())
                .resize(1000, 0)
                .placeholder(android.R.drawable.ic_menu_report_image)
                .transform(new RoundedCornersTransformation(10, 10))
                .into(backDropImageView);

//        ratingBar.setMax(10);
//        ratingBar.set
        ratingBar.setRating((float)movie.getVoteAverage());
        return v;
    }

    @OnClick(R.id.exitButton)
    public void sayHi(Button button) {
        dismiss();
    }
}
