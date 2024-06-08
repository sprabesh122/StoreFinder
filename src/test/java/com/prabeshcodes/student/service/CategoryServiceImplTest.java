package com.prabeshcodes.student.service;

import com.prabeshcodes.student.model.Category;
import com.prabeshcodes.student.repository.CategoryRepository;
import com.prabeshcodes.student.service.CategoryService;
import com.prabeshcodes.student.service.Impl.CategoryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class CategoryServiceImplTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryServiceImpl categoryServiceImpl;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetCategoryById() {
        // Arrange
        Category category = new Category();
        category.setId(1L);
        category.setName("Test Category");

        when(categoryRepository.findById(anyLong())).thenReturn(Optional.of(category));

        // Act
        Category foundCategory = categoryServiceImpl.getCategoryById(1L);

        // Assert
        assertEquals(category, foundCategory);
    }

    @Test
    public void testAddCategory() {
        // Arrange
        Category category = new Category();
        category.setName("Test Category");

        Category savedCategory = new Category();
        savedCategory.setId(1L);
        savedCategory.setName("Test Category");

        when(categoryRepository.save(any(Category.class))).thenReturn(savedCategory);

        // Act
        Category createdCategory = categoryServiceImpl.addCategory(category);

        // Assert
        assertEquals(savedCategory, createdCategory);
    }
}
