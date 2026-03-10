package com.example.FourLoop.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Product name is required")
    @Size(min = 2, max = 240, message = "Name must be between 2 and 240 characters")
    @Column(name = "name")
    private String name;

    @Min(value = 0, message = "Quantity must be 0 or greater")
    @Column(name = "quantity")
    private int quantity;

    @Positive(message = "Price must be greater than 0")
    @Column(name = "price")
    private double price;
}