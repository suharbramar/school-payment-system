package io.bramcode.movie.moviecategoryservices.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

public class MovieInfoDetail {
    private Long categoryId;
    private String categoryName;
    private Long movieId;
    private String movieName;
    private String movieDescription;
    private BigDecimal rating;
    private Boolean isActive;
    private Timestamp movieCreateDate;
    private List<UserRatingComment> userRatingCommentList;

    public MovieInfoDetail(){}

    public MovieInfoDetail(Long categoryId, String categoryName,
                           Long movieId, String movieName,
                           String movieDescription, BigDecimal rating, Boolean isActive, Timestamp movieCreateDate, List<UserRatingComment> userRatingCommentList) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.movieId = movieId;
        this.movieName = movieName;
        this.movieDescription = movieDescription;
        this.rating = rating;
        this.isActive = isActive;
        this.movieCreateDate = movieCreateDate;
        this.userRatingCommentList = userRatingCommentList;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public MovieInfoDetail setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
        return this;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public MovieInfoDetail setCategoryName(String categoryName) {
        this.categoryName = categoryName;
        return this;
    }

    public Long getMovieId() {
        return movieId;
    }

    public MovieInfoDetail setMovieId(Long movieId) {
        this.movieId = movieId;
        return this;
    }

    public String getMovieName() {
        return movieName;
    }

    public MovieInfoDetail setMovieName(String movieName) {
        this.movieName = movieName;
        return this;
    }

    public String getMovieDescription() {
        return movieDescription;
    }

    public MovieInfoDetail setMovieDescription(String movieDescription) {
        this.movieDescription = movieDescription;
        return this;
    }

    public BigDecimal getRating() {
        return rating;
    }

    public MovieInfoDetail setRating(BigDecimal rating) {
        this.rating = rating;
        return this;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public MovieInfoDetail setIsActive(Boolean active) {
        isActive = active;
        return this;
    }

    public Timestamp getMovieCreateDate() {
        return movieCreateDate;
    }

    public MovieInfoDetail setMovieCreateDate(Timestamp movieCreateDate) {
        this.movieCreateDate = movieCreateDate;
        return this;
    }

    public List<UserRatingComment> getUserRatingCommentList() {
        return userRatingCommentList;
    }

    public MovieInfoDetail setUserRatingCommentList(List<UserRatingComment> userRatingCommentList) {
        this.userRatingCommentList = userRatingCommentList;
        return this;
    }
}
