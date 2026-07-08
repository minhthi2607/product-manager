package com.code.productmanager.service;

import com.code.productmanager.model.Category;
import java.util.List;

public interface CategoryService {
    List<Category> findAll();
    Category findById(Long id);
    Category save(Category category);
}
