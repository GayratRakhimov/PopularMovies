package com.gayratrakhimov.popularmovies.data.repository;

import com.gayratrakhimov.popularmovies.data.cache.MoviesCacheTransformer;
import com.gayratrakhimov.popularmovies.data.mapper.MoviesMapper;
import com.gayratrakhimov.popularmovies.data.model.response.MoviesResponse;
import com.gayratrakhimov.popularmovies.data.network.ApiFactory;
import com.gayratrakhimov.popularmovies.domain.MoviesRepository;
import com.gayratrakhimov.popularmovies.domain.model.Movie;

import java.util.List;

import rx.Observable;

/**
 * @author Artur Vasilov
 */
public class MoviesDataRepository implements MoviesRepository {

    @Override
    public Observable<List<Movie>> popularMovies() {
        return ApiFactory.getMoviesService()
                .popularMovies()
                .map(MoviesResponse::getMovies)
                .compose(new MoviesCacheTransformer())
                .flatMap(Observable::from)
                .map(new MoviesMapper())
                .toList();
    }
}

