package com.esd1.esd1.service;

import com.esd1.esd1.dto.ProductRequest;
import com.esd1.esd1.entity.Product;
import com.esd1.esd1.exception.ProductNotFoundException;
import com.esd1.esd1.mapper.ProductMapper;
import com.esd1.esd1.repo.ProductRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepo repo;
    private final ProductMapper mapper;

    public String createProduct(ProductRequest request) {
        Product product = mapper.toEntity(request);
        repo.save(product);
        return "Created";
    }

    public Product detailsProduct(String productName) {
        //first we search the customer
        return findProduct(productName);
    }

    public List<Product> detailsTop2Product() {
        //first we search the customer
        return repo.findTop2Between15And30();
    }

    public Product updateProduct(ProductRequest request) {
        //first we search the customer
        Product product = findProduct(request.productName());
        product.setPrice(request.productPrice());
        repo.save(product);
        return product;
    }

    @Transactional
    public String deleteProduct(ProductRequest request) {
        //first we search the customer
        repo.deleteByName(request.productName());
        return "Deleted product with name "+request.productName()+".";
    }

    //    Utility functions
    public Product findProduct(String name){
        return repo.findByName(name).orElseThrow((() -> new ProductNotFoundException(
                format("Failed to find Product:: No product found with the provided name:: %s", name)
        )));
    }
}
