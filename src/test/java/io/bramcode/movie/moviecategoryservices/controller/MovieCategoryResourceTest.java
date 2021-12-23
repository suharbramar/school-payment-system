package io.bramcode.movie.moviecategoryservices.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.bramcode.movie.moviecategoryservices.model.CategoryResponse;
import io.bramcode.movie.moviecategoryservices.model.entity.Category;
import io.bramcode.movie.moviecategoryservices.service.CategoryService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(MovieCategoryResource.class) // will be limited to bootstrap a single controller
class MovieCategoryResourceTest {

    // to create and inject a mock for category service
    @MockBean
    private CategoryService categoryService;

    //mock http request
    @Autowired
    private MockMvc mockMvc;

    List<Category> listCategory = new ArrayList<>();
    Category dataCategory;
    Category updateCategory;

    CategoryResponse categoryResponse;

    @BeforeEach
    void setUp() {
        dataCategory = new Category();
        dataCategory.setCategoryId(1l);

        listCategory.add(dataCategory);

        categoryResponse = new CategoryResponse();
        categoryResponse.setCategoryName("Horror");

        updateCategory = new Category();
        updateCategory.setCategoryName("Hollywood");
        updateCategory.setUpdateBy("SYSTEM");
        updateCategory.setUpdateDate(new Timestamp(System.currentTimeMillis()));
    }

    @AfterEach
    void tearDown() {
        listCategory.clear();
    }

    @Test
    void createCategory() throws Exception {
        mockMvc.perform(post("/movie/category/save")
                .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(dataCategory))
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void updateCategory() throws Exception {
        mockMvc.perform(put("/movie/category/update/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(updateCategory))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void deleteCategory() throws Exception {
        mockMvc.perform(delete("/movie/category/delete/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getAllCategory() throws Exception {
        when(categoryService.retreiveAll()).thenReturn(listCategory);
        mockMvc.perform(get("/movie/category/categories")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("[{}]"));
    }

    @Test
    void getCategoryById() throws Exception {
        when(categoryService.retreiveById(dataCategory.getCategoryId())).thenReturn(categoryResponse);
        mockMvc.perform(get("/movie/category/categories/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("categoryName",is("Horror")));
    }

}