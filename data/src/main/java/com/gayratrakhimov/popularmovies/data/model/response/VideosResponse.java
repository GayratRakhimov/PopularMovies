package com.gayratrakhimov.popularmovies.data.model.response;

import com.gayratrakhimov.popularmovies.data.model.content.Video;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;



/**
 * @author Artur Vasilov
 */
public class VideosResponse {

    @SerializedName("results")
    private List<Video> mVideos;

    public List<Video> getVideos() {
        if (mVideos == null) {
            return new ArrayList<>();
        }
        return mVideos;
    }
}
