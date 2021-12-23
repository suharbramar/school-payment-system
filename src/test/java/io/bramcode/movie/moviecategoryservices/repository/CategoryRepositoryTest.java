package io.bramcode.movie.moviecategoryservices.repository;

import io.bramcode.movie.moviecategoryservices.model.entity.Category;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


@DataJpaTest
class CategoryRepositoryTest {

    @Autowired
    CategoryRepository categoryRepository;

    @AfterEach
    void tearDown() {
        //delete everything after test (clean test)
        categoryRepository.deleteAll();
    }

    @Test
    void existsById() {
        //given
        Long categoryId = 1L;
        Category category = new Category();
        category.setCategoryId(categoryId);
        category.setCategoryName("Horror");
        category.setIsActive(true);
        category.setCreateBy("SYSTEM");
        category.setCreateDate(new Timestamp(System.currentTimeMillis()));
        category.setUpdateBy("SYSTEM");
        category.setUpdateDate(new Timestamp(System.currentTimeMillis()));

        categoryRepository.save(category);

        //when
        boolean expected = categoryRepository.existsById(categoryId);
        //then
        assertTrue(expected);

    }

    @Test
    void NotExistsById() {
        //given
        Long categoryId = 1L;

        //when
        boolean expected = categoryRepository.existsById(categoryId);

        //then
        assertFalse(expected);

    }
}