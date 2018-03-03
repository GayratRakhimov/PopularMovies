package com.gayratrakhimov.popularmovies.screen.movies;

import android.support.annotation.NonNull;

import com.gayratrakhimov.popularmovies.domain.model.Movie;
import com.gayratrakhimov.popularmovies.screen.general.LoadingView;

import java.util.List;



/**
 * @author Artur Vasilov
 */
public interface MoviesView extends LoadingView {

    void showMovies(@NonNull List<Movie> movies);

    void showError();

}


