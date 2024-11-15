package com.esd1.esd1.controller;

import com.esd1.esd1.dto.CustomerRequest;
import com.esd1.esd1.dto.LoginRequest;
import com.esd1.esd1.entity.Customer;
import com.esd1.esd1.helper.JWTHelper;
import com.esd1.esd1.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class CustomerController {

    private final CustomerService customerService;
    private final JWTHelper jwtHelper;

    @PostMapping
    @RequestMapping("/api/v1/customer/create")
    public ResponseEntity<String> createCustomer(@RequestBody @Valid CustomerRequest request) {
        return ResponseEntity.ok(customerService.createCustomer(request));
    }

    @PostMapping
    @RequestMapping("/api/v1/customer/login")
    public ResponseEntity<String> loginCustomer(@RequestBody @Valid LoginRequest request) {
        return ResponseEntity.ok(customerService.loginCustomer(request));
    }

    @PostMapping
    @RequestMapping("/api/v1/customer/details")
    public ResponseEntity<?> detailsCustomer(@RequestHeader(value="Authorization") String authHeader, @RequestBody @Valid LoginRequest request) {
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String jwtToken = authHeader.substring(7);
            if(jwtHelper.validateToken(jwtToken, request.email()))
                return ResponseEntity.ok(customerService.detailsCustomer(request));
            return ResponseEntity.ok("Invalid Token");
        }
        return ResponseEntity.ok("Token not found.");
    }
}
