package com.prabeshcodes.student.controller;

import com.prabeshcodes.student.dtos.CategoryResponse;
import com.prabeshcodes.student.model.Category;
import com.prabeshcodes.student.repository.CategoryRepository;
import com.prabeshcodes.student.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/categories")
@CrossOrigin
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryRepository categoryRepository;

    // Logic to get category by ID
    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable Long id) {
        return categoryService.getCategoryById(id);
    }

    @PostMapping("/add")
    public Category addCategory(@RequestBody Category category){
        return categoryService.addCategory(category);
    }

    @GetMapping("/getAll")
    public List<CategoryResponse> getCategory(){
        List<CategoryResponse> result = new ArrayList<>();
        List<Category> categories = categoryRepository.findAll();
        for(Category category:categories){
            CategoryResponse categoryResponse = new CategoryResponse();
            categoryResponse.setId(category.getId());
            categoryResponse.setName(category.getName());
            result.add(categoryResponse);
        }
        return result;
    }
}