package com.gayratrakhimov.popularmovies.data.mapper;

import com.gayratrakhimov.popularmovies.domain.model.Movie;

import rx.functions.Func1;

/**
 * @author Artur Vasilov
 */
public class MoviesMapper implements Func1<com.gayratrakhimov.popularmovies.data.model.content.Movie, Movie> {

    @Override
    public Movie call(com.gayratrakhimov.popularmovies.data.model.content.Movie movie) {
        return new Movie(movie.getPosterPath(), movie.getOverview(),
                movie.getTitle(), movie.getReleasedDate(), movie.getVoteAverage());
    }

}
