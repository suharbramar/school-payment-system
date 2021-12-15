package io.bramcode.movie.moviecategoryservices.adaptor;

import io.bramcode.movie.moviecategoryservices.model.MovieCategory;
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
public class MovieInfoAdaptor {

    @Value("${integration.movie.get-movie-by-category-url}")
    private String getMovieByCategoryUrl;

    @Autowired
    RestTemplate restTemplate;

    public MovieCategory getMovieByCategoryId(String categoryId){

        Map<String, String> params = new HashMap<>();
        params.put("categoryId", categoryId);
        URI uriComponents = UriComponentsBuilder
                .fromHttpUrl(getMovieByCategoryUrl)
                .buildAndExpand(params)
                .toUri();
        return restTemplate.exchange(uriComponents.toString(),
                HttpMethod.GET, null, MovieCategory.class).getBody();
    }

}
