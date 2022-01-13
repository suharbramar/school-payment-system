package io.bramcode.movie.moviecategoryservices.service;

import io.bramcode.movie.moviecategoryservices.exception.CustomException;
import io.bramcode.movie.moviecategoryservices.model.CategoryResponse;
import io.bramcode.movie.moviecategoryservices.model.entity.Category;
import io.bramcode.movie.moviecategoryservices.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    @Resource
    private CategoryService categoryService;

    private Category category;
    private Category updCategory;

    @BeforeEach
    void setUp() {
        category = new Category();
        category.setCategoryId(1l);

        updCategory = new Category();
        updCategory.setCategoryName("ABC");
        updCategory.setUpdateBy("SYSTEM");
        updCategory.setUpdateDate(new Timestamp(System.currentTimeMillis()));

    }


    @Test
    void saveCategory() {
        when(categoryRepository.save(any(Category.class))).thenReturn(category);
        Category responseCategory = categoryService.saveCategory(category);

        assertNotNull(responseCategory);
    }

    @Test
    void retreiveAll() {
        /*
          this not actual test the repository because its already done in repository test class
         */
        //when
        categoryService.retreiveAll();
        //then
        verify(categoryRepository).findAll(Sort.by(Sort.Direction.ASC,"categoryId"));

    }

    @Test
    void retreiveById(){
        when(categoryRepository.findById(category.getCategoryId())).thenReturn(Optional.of(category));

        Category categoryResponse = categoryService.retreiveById(category.getCategoryId());
        assertTrue(Objects.nonNull(categoryResponse));

    }

    @Test
    void retreiveByIdNotFound(){
        CustomException expectedThrown = assertThrows(CustomException.class,
                () -> categoryService.retreiveById(category.getCategoryId()));
        assertEquals("CategoryID Not Found", expectedThrown.getMessage());
    }

    @Test
    void updateCategory() {
        when(categoryRepository.findById(category.getCategoryId())).thenReturn(Optional.of(category));
        Category responseCategory = categoryService.updateCategory(category.getCategoryId(), updCategory);
        assertNotNull(responseCategory);
    }


    @Test
    void deleteCategory() {
        when(categoryRepository.findById(category.getCategoryId())).thenReturn(Optional.of(category));
        Map<String, Boolean> delStatus = categoryService.deleteCategory(category.getCategoryId());
        assertTrue(delStatus.get("Deleted"));
    }
}