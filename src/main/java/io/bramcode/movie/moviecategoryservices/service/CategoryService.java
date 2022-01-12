package io.bramcode.movie.moviecategoryservices.service;

import io.bramcode.movie.moviecategoryservices.exception.CustomException;
import io.bramcode.movie.moviecategoryservices.model.entity.Category;
import io.bramcode.movie.moviecategoryservices.repository.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CategoryService {

    private static final Logger logger = LoggerFactory.getLogger(CategoryService.class);

    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryService(){}

    @CacheEvict(value = "categories", allEntries = true)
    public Category saveCategory(Category category){
        return categoryRepository.save(category);
    }

    @Cacheable(value = "categories")
    public List<Category> retreiveAll(){
        return categoryRepository.findAll(Sort.by(Sort.Direction.ASC,"categoryId"));
    }

    @Cacheable(value = "categories", key = "#categoryId")
    public Category retreiveById(Long categoryId) {
        logger.info("Inside category response..");
        Optional<Category> dataCategory = categoryRepository.findById(categoryId);

        var categoryResponse = dataCategory.map(
                    value -> {
                        Category categoryInfo = new Category();
                        //CategoryResponse categoryInfo = new CategoryResponse();

                        categoryInfo.setCategoryName(value.getCategoryName());
                        categoryInfo.setIsActive(value.getIsActive());
                        categoryInfo.setCategoryId(value.getCategoryId());
                        categoryInfo.setCreateBy(value.getCreateBy());
                        categoryInfo.setCreateDate(value.getCreateDate());
                        categoryInfo.setUpdateBy(value.getUpdateBy());
                        categoryInfo.setUpdateDate(value.getUpdateDate());

                        return categoryInfo;
                    }
            ).orElseThrow(
                    () -> new CustomException("Data Not Found",
                            "CategoryID Not Found",
                            "Please Check CategoryID "+categoryId));

        return categoryResponse;

    };

    @CachePut(value = "categories", key = "#categoryId")
    public Category updateCategory(Long categoryId, Category category){
        Optional<Category> optCategory = categoryRepository.findById(categoryId);
        Category updCategory = new Category();
      if(optCategory.isPresent()){
          updCategory = optCategory.get();
          updCategory.setCategoryName(category.getCategoryName());
          updCategory.setIsActive(category.getIsActive());
          updCategory.setUpdateBy(category.getUpdateBy());
          updCategory.setUpdateDate(new Timestamp(System.currentTimeMillis()));
          updCategory = categoryRepository.save(updCategory);

      }else{
          ResponseEntity.notFound();
      }
          return updCategory;
    }

    @CacheEvict(value = "categories", allEntries = true)
    public Map<String, Boolean> deleteCategory(Long categoryId){
        Optional<Category> optCategory = categoryRepository.findById(categoryId);

        Map<String, Boolean> response = new HashMap<>();
        if(optCategory.isPresent()){
            Category updCategory = optCategory.get();
            categoryRepository.delete(updCategory);

            response.put("Deleted", Boolean.TRUE);

        }else{
            ResponseEntity.notFound();
        }

        return response;
    }
}
