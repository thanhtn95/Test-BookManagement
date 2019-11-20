package com.test.service;

import com.test.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService {
    Page<Category> findAll(Pageable pageable);
    Category findById(int id);
    void save(Category category);
    void delete(int id);
    Iterable<Category> findAll();
}
