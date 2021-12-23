package io.bramcode.movie.moviecategoryservices.adaptor;

import io.bramcode.movie.moviecategoryservices.model.Movie;
import io.bramcode.movie.moviecategoryservices.model.MovieCategory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MovieInfoAdaptorTest {

    @InjectMocks
    private MovieInfoAdaptor movieInfoAdaptor;

    @Mock
    RestTemplate restTemplate;

    MovieCategory movieCategory;
    Movie movie;

    @BeforeEach
    void setUp() {
        movie = new Movie();
        movie.setMovieId(1l);
        movie.setMovieName("Marvel");

        movieCategory = new MovieCategory();
        movieCategory.setCategoryId(1l);
        movieCategory.setMovies(
                List.of(movie)
        );
    }

    @AfterEach
    void tearDown() {
        movie = null;
        movieCategory = null;
        movieInfoAdaptor = null;
    }

    @Test
    void getMovieByCategoryId() {
        ReflectionTestUtils.setField(movieInfoAdaptor,
                "getMovieByCategoryUrl",
                "http://movie-info-service/movie/list/category/{categoryId}");

        when(restTemplate.exchange("http://movie-info-service/movie/list/category/1", HttpMethod.GET,
                null, MovieCategory.class)).thenReturn(ResponseEntity.ok(movieCategory));
        var response = movieInfoAdaptor.getMovieByCategoryId(String.valueOf(movieCategory.getCategoryId()));

        assertEquals(1l, response.getCategoryId());
        assertFalse(response.getMovies().isEmpty());
    }
}