package io.bramcode.movie.moviecategoryservices.service;

import io.bramcode.movie.moviecategoryservices.adaptor.MovieInfoAdaptor;
import io.bramcode.movie.moviecategoryservices.adaptor.MovieRatingAdaptor;
import io.bramcode.movie.moviecategoryservices.exception.CustomException;
import io.bramcode.movie.moviecategoryservices.model.*;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieInfoService {

    @Autowired
    CategoryService categoryService;

    @Autowired
    MovieInfoAdaptor movieInfoAdaptor;

    @Autowired
    MovieRatingAdaptor movieRatingAdaptor;

    public MovieInfoDetailWrapper getMovieInfoDetail(Long categoryId) throws NotFoundException, CustomException {

        CategoryResponse categoryResponse = categoryService.retreiveById(categoryId);

        MovieCategory movieCategory = movieInfoAdaptor.getMovieByCategoryId(String.valueOf(categoryResponse.getCategoryId()));

        List<MovieInfoDetail> movieInfoDetails = new ArrayList<>();
        MovieInfoDetailWrapper movieInfoDetailWrapper = new MovieInfoDetailWrapper();
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

        movieInfoDetailWrapper.setMovieInfoDetail(movieInfoDetails);

        return movieInfoDetailWrapper;
    }
}
