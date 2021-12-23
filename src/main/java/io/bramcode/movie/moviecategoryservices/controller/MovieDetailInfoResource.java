package io.bramcode.movie.moviecategoryservices.controller;

import io.bramcode.movie.moviecategoryservices.exception.CustomException;
import io.bramcode.movie.moviecategoryservices.model.MovieInfoDetailWrapper;
import io.bramcode.movie.moviecategoryservices.repository.CategoryRepository;
import io.bramcode.movie.moviecategoryservices.service.CategoryService;
import io.bramcode.movie.moviecategoryservices.service.MovieInfoService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie/info")
public class MovieDetailInfoResource {

    //Autowire (consumer) 
    //telling spring somebody has bean somewhere of type resttemplate inject me that thing
//    @Autowired
//    private RestTemplate restTemplate;

    @Autowired
    private MovieInfoService movieInfoService;

    @GetMapping("/category/{categoryId}")
    public MovieInfoDetailWrapper getMovieByCategory(@PathVariable("categoryId") Long categoryId) throws NotFoundException, CustomException {
        return movieInfoService.getMovieInfoDetail(categoryId);
    }

}
