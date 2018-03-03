package com.gayratrakhimov.popularmovies.data.network;

import com.gayratrakhimov.popularmovies.data.model.response.MoviesResponse;

import retrofit2.http.GET;
import rx.Observable;

/**
 * @author Artur Vasilov
 */
public interface MovieService {

    @GET("popular/")
    Observable<MoviesResponse> popularMovies();

}
