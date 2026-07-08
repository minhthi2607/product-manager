package com.code.productmanager.service;

import com.code.productmanager.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface ProductService {
    Page<Product> findAll(Pageable pageable);
    Page<Product> searchProducts(String name, Double price, Long categoryId, Pageable pageable);
    Product findById(Long id);
    Product save(Product product);
    void delete(Long id);
    void deleteMultiple(List<Long> ids);
    List<Product> findByIds(List<Long> ids);
}
