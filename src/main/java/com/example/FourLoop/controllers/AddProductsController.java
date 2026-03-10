package com.example.FourLoop.controllers;

import com.example.FourLoop.Model.Product;
import com.example.FourLoop.Service.ProductService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class AddProductsController {

    private final ProductService productService;

    public AddProductsController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/Add-Product")
    public String showAddProductForm(Model model) {

        model.addAttribute("product", new Product());
        return "AddProduct";
    }

    @PostMapping("/Add-Product")
    public String createProduct(
            @Valid @ModelAttribute("product") Product product,
            BindingResult result) {

        if (result.hasErrors()) {
            return "AddProduct";
        }

        productService.addProduct(product);

        return "redirect:/products";
    }
}