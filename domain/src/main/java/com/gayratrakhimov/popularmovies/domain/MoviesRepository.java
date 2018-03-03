package com.gayratrakhimov.popularmovies.domain;

import com.gayratrakhimov.popularmovies.domain.model.Movie;

import java.util.List;

import rx.Observable;

/**
 * @author Artur Vasilov
 */
public interface MoviesRepository {

    Observable<List<Movie>> popularMovies();

}
