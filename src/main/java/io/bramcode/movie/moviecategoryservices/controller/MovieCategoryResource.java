package io.bramcode.movie.moviecategoryservices.controller;


import io.bramcode.movie.moviecategoryservices.model.CategoryResponse;
import io.bramcode.movie.moviecategoryservices.model.entity.Category;
import io.bramcode.movie.moviecategoryservices.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/movie/category")
public class MovieCategoryResource {

    //Autowire (consumer) 
    //telling spring somebody has bean somewhere of type resttemplate inject me that thing
//    @Autowired
//    private RestTemplate restTemplate;
    private static final Logger logger = LoggerFactory.getLogger(MovieCategoryResource.class);

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/save")
    public Category createCategory(@Valid @RequestBody Category category) {
        return categoryService.saveCategory(category);
    }

    @GetMapping("/categories")
    public List<Category> getAllCategory(){
        return categoryService.retreiveAll();
    }

    @GetMapping("/categories/{id}")
    public CategoryResponse getCategory(@PathVariable(value = "id") Long categoryId){
        CategoryResponse categoryResponse =categoryService.retreiveById(categoryId);
        logger.info("..fecthing category id");
        logger.info(categoryResponse.getCategoryName());
        return categoryResponse;
     //  return categoryService.retreiveById(categoryId);
    }

    @PutMapping("/update/{id}")
    public Category updateCategory(@PathVariable(value = "id") long categoryId,
                                   @Valid @RequestBody Category categoryDetails){

        return categoryService.updateCategory(categoryId, categoryDetails);
    }

    @DeleteMapping("/delete/{id}")
    public Map<String, Boolean> deleteCategory(@PathVariable(value = "id") long categoryId){
        return categoryService.deleteCategory(categoryId);
    }

//    @RequestMapping("/{userId}")
//    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){
//
//    //get all rated movied id
//
//    //for each movie id, call movie info service and get details
//
//    //put them all together
//
//   // RestTemplate restTemplate = new RestTemplate();
//
//
//    // List<Rating> ratings = Arrays.asList(
//    //     new Rating("1234",4),
//    //     new Rating("5678",3)
//    // );
//    UserRating userRating = restTemplate.getForObject("http://localhost:8083/ratingsdata/user/" + userId, UserRating.class);
//
//    return userRating.getUserRating().stream().map(rating -> {
//        //unmarshall object from rest template to object movie
//        //need empty constructor
//        Movie movie = restTemplate.getForObject("http://localhost:8082/movies/" +rating.getMovieId(),Movie.class);
//        return new CatalogItem(movie.getMovieId(),"desc a", rating.getRating());
//    }).collect(Collectors.toList());
//
//    // return Collections.singletonList(
//    //     new CatalogItem("Transformer","Test",4)
//    // );
//    }
}
