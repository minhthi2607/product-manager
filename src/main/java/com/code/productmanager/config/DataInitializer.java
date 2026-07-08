package com.code.productmanager.config;

import com.code.productmanager.model.Category;
import com.code.productmanager.model.Product;
import com.code.productmanager.service.CategoryService;
import com.code.productmanager.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @Override
    public void run(String... args) throws Exception {
        if (categoryService.findAll().isEmpty()) {
            Category dienThoai = categoryService.save(new Category(null, "Điện thoại"));
            Category tuLanh = categoryService.save(new Category(null, "Tủ lạnh"));
            Category tivi = categoryService.save(new Category(null, "Tivi"));
            Category mayGiat = categoryService.save(new Category(null, "Máy giặt"));

            productService.save(new Product(null, "iPhone 11", 120000.0, "chờ duyệt", dienThoai));
            productService.save(new Product(null, "iPhone 11 plus", 150000.0, "chờ duyệt", dienThoai));
            productService.save(new Product(null, "Tủ lạnh Toshiba 500L", 220000.0, "đang đấu giá", tuLanh));
            productService.save(new Product(null, "iPhone X", 120000.0, "chờ duyệt", dienThoai));
            productService.save(new Product(null, "Smart Tivi Sony 55 inch", 120000.0, "đã bán", tivi));
            productService.save(new Product(null, "Máy giặt Electrolux 9kg", 350000.0, "chờ duyệt", mayGiat));
            productService.save(new Product(null, "iPhone 13 Pro Max", 500000.0, "đang đấu giá", dienThoai));
            productService.save(new Product(null, "Tivi Samsung 65 inch", 450000.0, "chờ duyệt", tivi));
            productService.save(new Product(null, "Tủ lạnh Panasonic 300L", 180000.0, "đã bán", tuLanh));
        }
    }
}
