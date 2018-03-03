package com.gayratrakhimov.popularmovies.data.model.response;

import com.gayratrakhimov.popularmovies.data.model.content.Review;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Artur Vasilov
 */
public class ReviewsResponse {

    @SerializedName("results")
    private List<Review> mReviews;

    public List<Review> getReviews() {
        if (mReviews == null) {
            mReviews = new ArrayList<>();
        }
        return mReviews;
    }

}
