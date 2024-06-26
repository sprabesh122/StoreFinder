package com.prabeshcodes.student.service.Impl;

import com.prabeshcodes.student.model.Category;
import com.prabeshcodes.student.repository.CategoryRepository;
import com.prabeshcodes.student.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }
}