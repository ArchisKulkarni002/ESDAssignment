package com.esd1.esd1.repo;

import com.esd1.esd1.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepo extends JpaRepository<Product, Long> {

    List<Product> findByName(String name);
    List<Product> findByPrice(float price);
}
