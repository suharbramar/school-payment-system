package io.bramcode.movie.moviecategoryservices.service;

import io.bramcode.movie.moviecategoryservices.model.entity.Category;
import io.bramcode.movie.moviecategoryservices.model.CategoryResponse;
import io.bramcode.movie.moviecategoryservices.repository.CategoryRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryService(){}

    public Category execute(Category category){
        return categoryRepository.save(category);
    }

    public List<Category> retreiveAll(){
        return categoryRepository.findAll();
    }

    public CategoryResponse retreiveById(Long categoryId) throws NotFoundException {

        Optional<Category> dataCategory = categoryRepository.findById(categoryId);

        var category = dataCategory.map(
                value -> {
                    CategoryResponse categoryResponse = new CategoryResponse();

                    categoryResponse.setCategoryId(value.getCategoryId());
                    categoryResponse.setCategoryName(value.getCategoryName());
                    categoryResponse.setIsActive(value.getIsActive());
                    categoryResponse.setCreateBy(value.getCreateBy());
                    categoryResponse.setCreateDate(value.getCreateDate());
                    categoryResponse.setUpdateBy(value.getUpdateBy());
                    categoryResponse.setUpdateDate(value.getUpdateDate());

                    return categoryResponse;
                }
        ).orElseThrow(() -> new NotFoundException(categoryId +"Not Found"));

        return category;
    }

    public Category updateCategory(Long categoryId, Category category){
        Optional<Category> optCategory = categoryRepository.findById(categoryId);

      if(optCategory.isPresent()){
          Category updCategory = optCategory.get();
          updCategory.setCategoryName(category.getCategoryName());
          updCategory.setIsActive(category.getIsActive());
          updCategory.setUpdateBy("SYSTEM");
          updCategory.setUpdateDate(new Timestamp(System.currentTimeMillis()));
          categoryRepository.save(updCategory);
          return updCategory;

      }else{
          ResponseEntity.notFound();
      }
          return category;
    }

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
