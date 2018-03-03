package com.gayratrakhimov.popularmovies.domain.usecase;

import com.gayratrakhimov.popularmovies.domain.MoviesRepository;
import com.gayratrakhimov.popularmovies.domain.model.Movie;

import java.util.List;

import rx.Observable;

/**
 * @author Artur Vasilov
 */
public class MoviesUseCase {

    private final MoviesRepository mRepository;
    private final Observable.Transformer<List<Movie>, List<Movie>> mAsyncTransformer;

    public MoviesUseCase(MoviesRepository repository,
                         Observable.Transformer<List<Movie>, List<Movie>> asyncTransformer) {
        mRepository = repository;
        mAsyncTransformer = asyncTransformer;
    }

    public Observable<List<Movie>> popularMovies() {
        return mRepository.popularMovies()
                .compose(mAsyncTransformer);
    }
}


