package com.example.FourLoop.Service;

import com.example.FourLoop.Model.Product;
import com.example.FourLoop.Repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repo;

    public ProductService(ProductRepository repo) {
        this.repo = repo;
    }

    public List<Product> getAllProducts() {
        return repo.findAll();
    }

    public Page<Product> getAllProductsPageable(Pageable pageable) {
        return repo.findAll(pageable);
    }

    public void addProduct(Product product) {
        repo.save(product);
    }

    public Page<Product> findProductByName(String name, Pageable pageable) {
        return repo.findByNameContainingIgnoreCase(name, pageable);
    }

    public Product getProductById(int id) {
        return repo.findById(id).orElse(null);
    }
      

    // Delete a product by ID or update a product by ID
    public void deleteProductById(int id) {
        repo.deleteById(id);
    }

    public void updateProduct(int id, Product updatedProduct) {
        Product existingProduct = repo.findById(id).orElse(null);
        if (existingProduct != null) {
            existingProduct.setName(updatedProduct.getName());
            existingProduct.setPrice(updatedProduct.getPrice());
            existingProduct.setQuantity(updatedProduct.getQuantity());
            repo.save(existingProduct);
        }
    }

    public Page<Product> getProductsSortedByPrice(Pageable pageable) {
        return repo.findAllByOrderByPriceAsc(pageable);
    }

    public Page<Product> getProductsSortedByName(Pageable pageable) {
        return repo.findAllByOrderByNameAsc(pageable);
    }

    public Page<Product> getProductSortedByQuantity(Pageable pageable) {
        return repo.findAllByOrderByQuantityAsc(pageable);
    }
}