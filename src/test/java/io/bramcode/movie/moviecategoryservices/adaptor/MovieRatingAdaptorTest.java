package io.bramcode.movie.moviecategoryservices.adaptor;

import io.bramcode.movie.moviecategoryservices.model.RatingInfo;
import io.bramcode.movie.moviecategoryservices.model.UserRatingComment;
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

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MovieRatingAdaptorTest {

    @InjectMocks
    private MovieRatingAdaptor movieRatingAdaptor;

    @Mock
    RestTemplate restTemplate;

    RatingInfo ratingInfo;

    @BeforeEach
    void setUp() {
        ratingInfo = new RatingInfo();
        ratingInfo.setMovieId(1L);
        ratingInfo.setUserRatingCommentList(List.of(new UserRatingComment(
                "Budi", new BigDecimal(4.5), "Good"
        )));
    }

    @AfterEach
    void tearDown() {
        ratingInfo = null;
    }

    @Test
    void getMovieRating() {
        ReflectionTestUtils.setField(movieRatingAdaptor,"movieRatingUrl",
                "http://movie-rating-service/rating/list/movie/1");
        when(restTemplate.exchange("http://movie-rating-service/rating/list/movie/1", HttpMethod.GET,
                null, RatingInfo.class)).thenReturn(ResponseEntity.ok(ratingInfo));
        var response = movieRatingAdaptor.getMovieRating("1");

        assertEquals(1L, response.getMovieId());
        assertFalse(response.getUserRatingCommentList().isEmpty());


    }
}