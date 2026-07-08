package com.code.productmanager.controller;

import com.code.productmanager.model.Category;
import com.code.productmanager.model.Product;
import com.code.productmanager.service.CategoryService;
import com.code.productmanager.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping({"/", "/product"})
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String showProductList(
            @RequestParam(value = "name", required = false, defaultValue = "") String name,
            @RequestParam(value = "price", required = false) Double price,
            @RequestParam(value = "categoryId", required = false) Long categoryId,
            @RequestParam(value = "page", defaultValue = "0") int page,
            Model model) {

        Pageable pageable = PageRequest.of(page, 5);
        Page<Product> productPage = productService.searchProducts(name, price, categoryId, pageable);

        List<Category> categories = categoryService.findAll();

        model.addAttribute("productPage", productPage);
        model.addAttribute("categories", categories);
        model.addAttribute("name", name);
        model.addAttribute("price", price);
        model.addAttribute("categoryId", categoryId);
        
        return "product/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryService.findAll());
        return "product/create";
    }

    @PostMapping("/create")
    public String createProduct(
            @Valid @ModelAttribute("product") Product product,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", categoryService.findAll());
            return "product/create";
        }

        productService.save(product);
        redirectAttributes.addFlashAttribute("successMessage", "Thêm mới sản phẩm thành công!");
        return "redirect:/product";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
        Product product = productService.findById(id);
        if (product == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Sản phẩm không tồn tại!");
            return "redirect:/product";
        }
        model.addAttribute("product", product);
        model.addAttribute("categories", categoryService.findAll());
        return "product/edit";
    }

    @PostMapping("/edit/{id}")
    public String editProduct(
            @PathVariable("id") Long id,
            @Valid @ModelAttribute("product") Product product,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", categoryService.findAll());
            return "product/edit";
        }

        Product existingProduct = productService.findById(id);
        if (existingProduct == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Sản phẩm không tồn tại!");
            return "redirect:/product";
        }

        product.setId(id);

        product.setStatus(existingProduct.getStatus());
        productService.save(product);
        
        redirectAttributes.addFlashAttribute("successMessage", "Cập nhật sản phẩm thành công!");
        return "redirect:/product";
    }

    @PostMapping("/delete-multiple")
    public String deleteMultiple(
            @RequestParam(value = "selectedIds", required = false) List<Long> selectedIds,
            RedirectAttributes redirectAttributes) {

        if (selectedIds == null || selectedIds.isEmpty()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Vui lòng chọn ít nhất một sản phẩm để xóa!");
            return "redirect:/product";
        }


        List<Product> products = productService.findByIds(selectedIds);
        String names = products.stream().map(Product::getName).collect(Collectors.joining(", "));

        productService.deleteMultiple(selectedIds);

        redirectAttributes.addFlashAttribute("successMessage", "Đã xóa thành công các sản phẩm: " + names);
        return "redirect:/product";
    }
}
