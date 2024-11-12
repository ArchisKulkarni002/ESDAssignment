package com.esd1.esd1.mapper;

import com.esd1.esd1.dto.CustomerRequest;
import com.esd1.esd1.entity.Customer;
import org.springframework.stereotype.Service;

@Service
public class CustomerMapper {
    public Customer toEntity(CustomerRequest request) {
        return Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .password(request.password())
                .address(request.address())
                .city(request.city())
                .pincode(request.pinCode())
                .build();
    }
}
