package com.esd1.esd1.controller;

import com.esd1.esd1.dto.CustomerRequest;
import com.esd1.esd1.dto.ProductRequest;
import com.esd1.esd1.entity.Product;
import com.esd1.esd1.exception.ProductNotFoundException;
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
    public ResponseEntity<?> detailsProduct(@RequestBody @Valid ProductRequest request) {
        try{
            Product product = productService.detailsProduct(request);
            return ResponseEntity.ok(product);
        }catch (ProductNotFoundException e){
            return ResponseEntity.ok("Specified element not found");
        }
    }

    @PostMapping
    @RequestMapping("/api/v1/product/detailsTop2")
    public ResponseEntity<?> detailsTop2Product(@RequestBody @Valid ProductRequest request) {
        try{
            List<Product> product = productService.detailsTop2Product(request);
            return ResponseEntity.ok(product);
        }catch (ProductNotFoundException e){
            return ResponseEntity.ok("Specified element not found");
        }
    }

    @PostMapping
    @RequestMapping("/api/v1/product/update")
    public ResponseEntity<?> updateProduct(@RequestBody @Valid ProductRequest request) {
        try{
            Product product = productService.updateProduct(request);
            return ResponseEntity.ok(product);
        }catch (ProductNotFoundException e){
            return ResponseEntity.ok("Specified element not found");
        }
    }

    @PostMapping
    @RequestMapping("/api/v1/product/delete")
    public ResponseEntity<String> deleteProduct(@RequestBody @Valid ProductRequest request) {
        return ResponseEntity.ok(productService.deleteProduct(request));
    }
}
