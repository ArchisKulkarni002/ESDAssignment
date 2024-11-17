package com.esd1.esd1.service;

import com.esd1.esd1.dto.ProductRequest;
import com.esd1.esd1.entity.Product;
import com.esd1.esd1.exception.CustomerNotFoundException;
import com.esd1.esd1.mapper.ProductMapper;
import com.esd1.esd1.repo.ProductRepo;
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

    public List<Product> detailsProduct(ProductRequest request) {
        //first we search the customer
        return findProduct(request.productName());
    }

    //    Utility functions
    public List<Product> findProduct(String name){
        List<Product> products = repo.findByName(name);

        if (products.isEmpty()) {
            throw new CustomerNotFoundException(
                    String.format("Failed to find Product:: No product found with the provided name:: %s", name)
            );
        }

        return products;
    }
}
