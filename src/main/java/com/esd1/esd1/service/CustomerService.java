package com.esd1.esd1.service;

import com.esd1.esd1.dto.CustomerRequest;
import com.esd1.esd1.dto.CustomerResponse;
import com.esd1.esd1.dto.LoginRequest;
import com.esd1.esd1.entity.Customer;
import com.esd1.esd1.exception.CustomerNotFoundException;
import com.esd1.esd1.helper.EncryptionService;
import com.esd1.esd1.mapper.CustomerMapper;
import com.esd1.esd1.repo.CustomerRepo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepo repo;
    private final CustomerMapper mapper;
    private final EncryptionService encryptionService;
    public String createCustomer(CustomerRequest request) {
        Customer customer = mapper.toEntity(request);
        customer.setPassword(encryptionService.encode(customer.getPassword()));
        repo.save(customer);
        return "Created";
    }

    public String loginCustomer(LoginRequest request) {
        //first we search the customer
        Customer customer = findCustomer(request.email());
        if (customer.getEmail().equals(request.email()) && encryptionService.validates(request.password(),customer.getPassword())) {
            return "Logged in";
        }else{
            return "Wrong email or password";
        }
    }

    public Customer detailsCustomer(LoginRequest request) {
        //first we search the customer
        return findCustomer(request.email());
    }

//    Utility functions
    public Customer findCustomer(String email){
        return repo.findByEmail(email).orElseThrow(() -> new CustomerNotFoundException(
                format("Failed to find Customer:: No customer found with the provided email:: %s", email)
        ));
    }
}
