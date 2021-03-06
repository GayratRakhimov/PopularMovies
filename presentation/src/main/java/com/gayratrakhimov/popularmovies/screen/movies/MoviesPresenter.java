package com.gayratrakhimov.popularmovies.screen.movies;

import android.support.annotation.NonNull;

import com.gayratrakhimov.popularmovies.R;
import com.gayratrakhimov.popularmovies.domain.usecase.MoviesUseCase;

import ru.arturvasilov.rxloader.LifecycleHandler;


/**
 * @author Artur Vasilov
 */
public class MoviesPresenter {

    private final MoviesView mMoviesView;
    private final MoviesUseCase mMoviesUseCase;
    private final LifecycleHandler mLifecycleHandler;

    public MoviesPresenter(@NonNull MoviesView moviesView, @NonNull MoviesUseCase moviesUseCase,
                           @NonNull LifecycleHandler lifecycleHandler) {
        mMoviesView = moviesView;
        mMoviesUseCase = moviesUseCase;
        mLifecycleHandler = lifecycleHandler;
    }

    public void init() {
        mMoviesUseCase.popularMovies()
                .doOnSubscribe(mMoviesView::showLoadingIndicator)
                .doAfterTerminate(mMoviesView::hideLoadingIndicator)
                .compose(mLifecycleHandler.load(R.id.movies_request_id))
                .subscribe(mMoviesView::showMovies, throwable -> mMoviesView.showError());
    }
}

