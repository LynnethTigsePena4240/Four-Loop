package com.example.FourLoop.Repository;

import com.example.FourLoop.Model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    Page<Product> findByNameContainingIgnoreCase(String name, Pageable pageable);
    // Add methods for sorting by price, name, and quantity
    Page<Product> findAllByOrderByPriceAsc(Pageable pageable);
    Page<Product> findAllByOrderByNameAsc(Pageable pageable);
    Page<Product> findAllByOrderByQuantityAsc(Pageable pageable);


}