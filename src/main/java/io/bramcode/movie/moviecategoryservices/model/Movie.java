package io.bramcode.movie.moviecategoryservices.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Movie {
    private Long movieId;
    private String movieName;
    private String movieDescription;
    private BigDecimal rating;
    private Boolean isActive;
    private Timestamp createDate;
    private Timestamp updateDate;

    public Movie(){}

    public Movie(Long movieId, String movieName, String movieDescription, BigDecimal rating, Boolean isActive, Timestamp createDate, Timestamp updateDate) {
        this.movieId = movieId;
        this.movieName = movieName;
        this.movieDescription = movieDescription;
        this.rating = rating;
        this.isActive = isActive;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    public Long getMovieId() {
        return movieId;
    }

    public Movie setMovieId(Long movieId) {
        this.movieId = movieId;
        return this;
    }

    public String getMovieName() {
        return movieName;
    }

    public Movie setMovieName(String movieName) {
        this.movieName = movieName;
        return this;
    }

    public String getMovieDescription() {
        return movieDescription;
    }

    public Movie setMovieDescription(String movieDescription) {
        this.movieDescription = movieDescription;
        return this;
    }

    public BigDecimal getRating() {
        return rating;
    }

    public Movie setRating(BigDecimal rating) {
        this.rating = rating;
        return this;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public Movie setIsActive(Boolean active) {
        isActive = active;
        return this;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public Movie setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
        return this;
    }

    public Timestamp getUpdateDate() {
        return updateDate;
    }

    public Movie setUpdateDate(Timestamp updateDate) {
        this.updateDate = updateDate;
        return this;
    }
}

