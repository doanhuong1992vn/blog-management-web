package com.blog_application.service.impl;

import com.blog_application.entity.Category;
import com.blog_application.repository.CategoryRepository;
import com.blog_application.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public Iterable<Category> findAll() {
        return categoryRepository.findAll();
    }


    @Override
    public void save(Category category) {
        categoryRepository.save(category);
    }


    @Override
    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public void update(Category category) {
        categoryRepository.save(category);
    }

}
