package com.esd1.esd1.controller;

import com.esd1.esd1.dto.ProductRequest;
import com.esd1.esd1.entity.Product;
import com.esd1.esd1.exception.ProductNotFoundException;
import com.esd1.esd1.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<String> createProduct(@RequestBody @Valid ProductRequest request) {
        return ResponseEntity.ok(productService.createProduct(request));
    }

    @GetMapping
    public ResponseEntity<?> detailsProduct(@RequestParam @Valid String product_name) {
        try{
            Product product = productService.detailsProduct(product_name);
            return ResponseEntity.ok(product);
        }catch (ProductNotFoundException e){
            return ResponseEntity.ok("Specified element not found");
        }
    }

    @GetMapping
    @RequestMapping("/Top2")
    public ResponseEntity<?> detailsTop2Product() {
        try{
            List<Product> product = productService.detailsTop2Product();
            return ResponseEntity.ok(product);
        }catch (ProductNotFoundException e){
            return ResponseEntity.ok("Specified element not found");
        }
    }

    @PutMapping
    public ResponseEntity<?> updateProduct(@RequestBody @Valid ProductRequest request) {
        try{
            Product product = productService.updateProduct(request);
            return ResponseEntity.ok(product);
        }catch (ProductNotFoundException e){
            return ResponseEntity.ok("Specified element not found");
        }
    }

    @DeleteMapping
    public ResponseEntity<String> deleteProduct(@RequestBody @Valid ProductRequest request) {
        return ResponseEntity.ok(productService.deleteProduct(request));
    }
}
