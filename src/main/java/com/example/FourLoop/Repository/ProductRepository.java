package com.example.FourLoop.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.FourLoop.Model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    Page<Product> findByNameContainingIgnoreCase(String name, Pageable pageable);

    Page<Product> findAllByOrderByPriceAsc(Pageable pageable);

    Page<Product> findAllByOrderByNameAsc(Pageable pageable);

    Page<Product> findAllByOrderByQuantityAsc(Pageable pageable);
}