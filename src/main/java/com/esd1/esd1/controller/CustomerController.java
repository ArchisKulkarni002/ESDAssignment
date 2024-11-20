package com.esd1.esd1.controller;

import com.esd1.esd1.dto.CustomerRequest;
import com.esd1.esd1.dto.LoginRequest;
import com.esd1.esd1.dto.CustomerRequest;
import com.esd1.esd1.entity.Customer;
import com.esd1.esd1.entity.Customer;
import com.esd1.esd1.exception.CustomerNotFoundException;
import com.esd1.esd1.helper.JWTHelper;
import com.esd1.esd1.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;
    private final JWTHelper jwtHelper;

    @PostMapping
    public ResponseEntity<String> createCustomer(@RequestBody @Valid CustomerRequest request) {
        return ResponseEntity.ok(customerService.createCustomer(request));
    }

    @PostMapping
    @RequestMapping("/login")
    public ResponseEntity<String> loginCustomer(@RequestBody @Valid LoginRequest request) {
        return ResponseEntity.ok(customerService.loginCustomer(request));
    }

    @GetMapping
    public ResponseEntity<?> detailsCustomer(@RequestHeader(value="Authorization") String authHeader, @RequestParam @Valid String email) {
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String jwtToken = authHeader.substring(7);
            if(jwtHelper.validateToken(jwtToken, email))
                return ResponseEntity.ok(customerService.detailsCustomer(email));
            return ResponseEntity.ok("Invalid Token");
        }
        return ResponseEntity.ok("Token not found.");
    }

    @PutMapping
    public ResponseEntity<?> updateCustomer(@RequestHeader(value="Authorization") String authHeader, @RequestBody @Valid CustomerRequest request) {
        try{
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                String jwtToken = authHeader.substring(7);
                if(jwtHelper.validateToken(jwtToken, request.email())) {
                    Customer customer = customerService.updateCustomer(request);
                    return ResponseEntity.ok(customer);
                }
                return ResponseEntity.ok("Invalid Token");
            }
        }catch (CustomerNotFoundException e){
            return ResponseEntity.ok("Specified element not found");
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteCustomer(@RequestHeader(value="Authorization") String authHeader, @RequestBody @Valid CustomerRequest request) {
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String jwtToken = authHeader.substring(7);
            if(jwtHelper.validateToken(jwtToken, request.email()))
                return ResponseEntity.ok(customerService.deleteCustomer(request));
            return ResponseEntity.ok("Invalid Token");
        }
        return ResponseEntity.ok("Token not found.");
    }
}
