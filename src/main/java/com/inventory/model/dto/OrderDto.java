package com.inventory.model.dto;

import com.inventory.model.Customer;
import com.inventory.model.Product;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderDto {
    private Long orderId;
    private String status;
    private Double totalAmount;
    private LocalDateTime orderDate;
    private CustomerDto customer;
    private List<ProductDto> products;
}
