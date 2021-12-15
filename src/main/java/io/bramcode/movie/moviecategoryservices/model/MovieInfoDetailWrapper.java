package io.bramcode.movie.moviecategoryservices.model;

import java.util.List;

public class MovieInfoDetailWrapper {
    List<MovieInfoDetail> movieInfoDetail;

    public MovieInfoDetailWrapper() {
    }

    public MovieInfoDetailWrapper(List<MovieInfoDetail> movieInfoDetail) {
        this.movieInfoDetail = movieInfoDetail;
    }

    public List<MovieInfoDetail> getMovieInfoDetail() {
        return movieInfoDetail;
    }

    public MovieInfoDetailWrapper setMovieInfoDetail(List<MovieInfoDetail> movieInfoDetail) {
        this.movieInfoDetail = movieInfoDetail;
        return this;
    }
}
