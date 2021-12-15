package io.bramcode.movie.moviecategoryservices.adaptor;

import io.bramcode.movie.moviecategoryservices.model.RatingInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Component
public class MovieRatingAdaptor {
    @Value("${integration.rating.get-movie-rating-url}")
    private String movieRatingUrl;

    @Autowired
    private RestTemplate restTemplate;

    public RatingInfo getMovieRating(String movieId){

        Map<String, String> params = new HashMap<>();
        params.put("movieId", movieId);
        URI uriComponents = UriComponentsBuilder
                .fromHttpUrl(movieRatingUrl)
                .buildAndExpand(params)
                .toUri();
        return restTemplate.exchange(uriComponents.toString(),
                HttpMethod.GET, null, RatingInfo.class).getBody();
    }
}
