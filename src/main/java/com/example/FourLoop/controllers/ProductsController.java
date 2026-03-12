package com.example.FourLoop.controllers;

import com.example.FourLoop.Model.Product;
import com.example.FourLoop.Service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProductsController {

    private final ProductService productService;

    public ProductsController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public String getProductsPage(
            @RequestParam(name = "search", required = false, defaultValue = "") String search,
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "size", required = false, defaultValue = "10") int size,
            @RequestParam(name = "sort", required = false, defaultValue = "id") String sortBy,
            @RequestParam(name = "direction", required = false, defaultValue = "ASC") String direction,
            Model model) {

        Sort.Direction sortedDirection =
                direction.equalsIgnoreCase("DESC") ? Sort.Direction.DESC : Sort.Direction.ASC;

        Sort sort = Sort.by(sortedDirection, sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Product> productsPage;

        if (!search.trim().isEmpty()) {
            productsPage = productService.findProductByName(search, pageable);
        } else {
            productsPage = productService.getAllProductsPageable(pageable);
        }

        long total = productsPage.getTotalElements();
        long startIndex = total == 0 ? 0 : (long) page * size + 1;
        long endIndex = total == 0 ? 0 : Math.min((long) (page + 1) * size, total);

        model.addAttribute("products", productsPage.getContent());
        model.addAttribute("total", total);
        model.addAttribute("size", size);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", productsPage.getTotalPages());
        model.addAttribute("search", search);
        model.addAttribute("sort", sortBy);
        model.addAttribute("direction", direction);
        model.addAttribute("hasPrevious", productsPage.hasPrevious());
        model.addAttribute("hasNext", productsPage.hasNext());
        model.addAttribute("startIndex", startIndex);
        model.addAttribute("endIndex", endIndex);

        return "Products";
    }

    @GetMapping("/delete-product/{id}")
    public String deleteProduct(@PathVariable int id) {
        productService.deleteProductById(id);
        return "redirect:/products";
    }

    @GetMapping("/update-product/{id}")
    public String showUpdateProductForm(@PathVariable int id, Model model) {
        Product product = productService.getProductById(id);

        if (product == null) {
            return "redirect:/products";
        }

        model.addAttribute("product", product);
        return "UpdateProduct";
    }

    @PostMapping("/update-product/{id}")
    public String updateProduct(@PathVariable int id, @ModelAttribute("product") Product product) {
        productService.updateProduct(id, product);
        return "redirect:/products";
    }
}