package com.inventory.controller;

import com.inventory.model.dto.CustomerDto;
import com.inventory.model.dto.ProductDto;
import com.inventory.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping("/create-customer")
    public ResponseEntity<?> createCustomer(@RequestBody CustomerDto customerDto) {

        try {
            return ResponseEntity.ok(customerService.createCustomer(customerDto));
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body("Customer already exists");
        }
    }


    // Fetch category by ID
    @GetMapping("/get-product-by-id/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable("id") Long customerId) {

       CustomerDto dto = customerService.getCustomerById(customerId);

        if (dto != null) {
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer Not Found");
    }


    @DeleteMapping("/delete-customer-by-id/{id}")
    public boolean deleteCustomer(@PathVariable("id") Long customerId){
        return customerService.deleteCustomer(customerId);
    }

    @PutMapping("/update-product")
    public CustomerDto updateCustomer(@RequestBody CustomerDto dto){

        return  customerService.updateCustomer(dto);
    }

}
