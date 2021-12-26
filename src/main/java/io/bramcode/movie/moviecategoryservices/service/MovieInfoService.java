package io.bramcode.movie.moviecategoryservices.service;

import io.bramcode.movie.moviecategoryservices.adaptor.MovieInfoAdaptor;
import io.bramcode.movie.moviecategoryservices.adaptor.MovieRatingAdaptor;
import io.bramcode.movie.moviecategoryservices.controller.MovieCategoryResource;
import io.bramcode.movie.moviecategoryservices.exception.CustomException;
import io.bramcode.movie.moviecategoryservices.model.*;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieInfoService {

    private static final Logger logger = LoggerFactory.getLogger(MovieCategoryResource.class);

    @Autowired
    MovieInfoAdaptor movieInfoAdaptor;

    @Autowired
    MovieRatingAdaptor movieRatingAdaptor;


    @CircuitBreaker(name = "custom", fallbackMethod = "getMovieInfoDetailFallBack")
    public MovieInfoDetailWrapper getMovieInfoDetail(CategoryResponse categoryResponse) throws NotFoundException, CustomException {

        MovieCategory movieCategory = movieInfoAdaptor.getMovieByCategoryId(String.valueOf(categoryResponse.getCategoryId()));

        List<MovieInfoDetail> movieInfoDetails = getMovieInfoDetail(categoryResponse, movieCategory);
        MovieInfoDetailWrapper movieInfoDetailWrapper = new MovieInfoDetailWrapper();

        movieInfoDetailWrapper.setMovieInfoDetail(movieInfoDetails);

        return movieInfoDetailWrapper;
    }

    public List<MovieInfoDetail> getMovieInfoDetail(CategoryResponse categoryResponse,
                                                    MovieCategory movieCategory){
        logger.info("Call Movie Rating ...");
        List<MovieInfoDetail> movieInfoDetails = new ArrayList<>();
        movieCategory.getMovies().stream().forEach(
                value -> {
                    RatingInfo ratingInfo = movieRatingAdaptor.getMovieRating(String.valueOf(value.getMovieId()));

                    MovieInfoDetail movieInfoDetail = new MovieInfoDetail();

                    movieInfoDetail.setCategoryId(categoryResponse.getCategoryId());
                    movieInfoDetail.setCategoryName(categoryResponse.getCategoryName());
                    movieInfoDetail.setMovieId(value.getMovieId());
                    movieInfoDetail.setMovieName(value.getMovieName());
                    movieInfoDetail.setMovieDescription(value.getMovieDescription());
                    movieInfoDetail.setMovieCreateDate(value.getCreateDate());
                    movieInfoDetail.setIsActive(value.getIsActive());
                    movieInfoDetail.setRating(value.getRating());
                    movieInfoDetail.setUserRatingCommentList(ratingInfo.getUserRatingCommentList());

                    movieInfoDetails.add(movieInfoDetail);
                }
        );
        return movieInfoDetails;
    }

    public MovieInfoDetailWrapper getMovieInfoDetailFallBack(Exception e){
        e.printStackTrace();
        return new MovieInfoDetailWrapper();
    }
}
