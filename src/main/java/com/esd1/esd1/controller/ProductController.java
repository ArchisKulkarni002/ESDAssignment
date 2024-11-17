package com.esd1.esd1.controller;

import com.esd1.esd1.dto.CustomerRequest;
import com.esd1.esd1.dto.ProductRequest;
import com.esd1.esd1.entity.Product;
import com.esd1.esd1.service.CustomerService;
import com.esd1.esd1.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ProductController {
    private final ProductService productService;

    @PostMapping
    @RequestMapping("/api/v1/product/create")
    public ResponseEntity<String> createProduct(@RequestBody @Valid ProductRequest request) {
        return ResponseEntity.ok(productService.createProduct(request));
    }

    @PostMapping
    @RequestMapping("/api/v1/product/details")
    public ResponseEntity<List<Product>> detailsProduct(@RequestBody @Valid ProductRequest request) {
        return ResponseEntity.ok(productService.detailsProduct(request));
    }
}
