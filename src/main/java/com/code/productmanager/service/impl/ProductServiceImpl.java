package com.code.productmanager.service.impl;

import com.code.productmanager.model.Product;
import com.code.productmanager.repository.ProductRepository;
import com.code.productmanager.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Page<Product> searchProducts(String name, Double price, Long categoryId, Pageable pageable) {
        // Normalize parameters for query
        String searchName = (name != null && !name.trim().isEmpty()) ? name.trim() : null;
        Long searchCategoryId = (categoryId != null && categoryId > 0) ? categoryId : null;
        return productRepository.searchProducts(searchName, price, searchCategoryId, pageable);
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteMultiple(List<Long> ids) {
        if (ids != null && !ids.isEmpty()) {
            productRepository.deleteAllByIdInBatch(ids);
        }
    }

    @Override
    public List<Product> findByIds(List<Long> ids) {
        return productRepository.findAllById(ids);
    }
}
