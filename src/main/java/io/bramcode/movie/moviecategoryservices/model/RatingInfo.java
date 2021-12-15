package io.bramcode.movie.moviecategoryservices.model;

import java.util.List;


public class RatingInfo {

    private Long movieId;
    private List<UserRatingComment> userRatingCommentList;

    public RatingInfo(){}

    public RatingInfo(Long movieId, List<UserRatingComment> userRatingCommentList) {
        this.movieId = movieId;
        this.userRatingCommentList = userRatingCommentList;
    }

    public Long getMovieId() {
        return movieId;
    }

    public RatingInfo setMovieId(Long movieId) {
        this.movieId = movieId;
        return this;
    }

    public List<UserRatingComment> getUserRatingCommentList() {
        return userRatingCommentList;
    }

    public RatingInfo setUserRatingCommentList(List<UserRatingComment> userRatingCommentList) {
        this.userRatingCommentList = userRatingCommentList;
        return this;
    }
}
