package com.gayratrakhimov.popularmovies.data.model.response;

import com.gayratrakhimov.popularmovies.data.model.content.Movie;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Artur Vasilov
 */
public class MoviesResponse {

    @SerializedName("results")
    private List<Movie> mMovies;

    public List<Movie> getMovies() {
        if (mMovies == null) {
            return new ArrayList<>();
        }
        return mMovies;
    }

}
