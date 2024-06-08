package com.prabeshcodes.student.controller;

import com.prabeshcodes.student.model.Category;
import com.prabeshcodes.student.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CategoryControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private CategoryController categoryController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build();
    }

    @Test
    public void testGetCategoryById() throws Exception {
        // Arrange
        Category category = new Category();
        category.setId(1L);
        category.setName("Test Category");

        when(categoryService.getCategoryById(anyLong())).thenReturn(category);

        // Act & Assert
        mockMvc.perform(get("/categories/1"))
                .andExpect(status().isOk())
                .andExpect(content().json("{'id':1,'name':'Test Category'}"));
    }

    @Test
    public void testAddCategory() throws Exception {
        // Arrange
        Category category = new Category();
        category.setId(1L);
        category.setName("Test Category");

        when(categoryService.addCategory(any(Category.class))).thenReturn(category);

        // Act & Assert
        mockMvc.perform(post("/categories/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Test Category\"}"))
                .andExpect(status().isOk())
                .andExpect(content().json("{'id':1,'name':'Test Category'}"));
    }
}
