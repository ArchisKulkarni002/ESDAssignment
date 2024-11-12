package com.esd1.esd1.service;

import com.esd1.esd1.dto.CustomerRequest;
import com.esd1.esd1.dto.CustomerResponse;
import com.esd1.esd1.entity.Customer;
import com.esd1.esd1.mapper.CustomerMapper;
import com.esd1.esd1.repo.CustomerRepo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepo repo;
    private final CustomerMapper mapper;
    public String createCustomer(CustomerRequest request) {
        Customer customer = mapper.toEntity(request);
        repo.save(customer);
        return "Created";
    }
}
