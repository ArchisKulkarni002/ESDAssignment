package com.esd1.esd1.mapper;

import com.esd1.esd1.dto.CustomerRequest;
import com.esd1.esd1.dto.ProductRequest;
import com.esd1.esd1.entity.Customer;
import com.esd1.esd1.entity.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {
    public Product toEntity(ProductRequest request) {
        return Product.builder()
                .name(request.productName())
                .price(request.productPrice())
                .build();

    }
}
