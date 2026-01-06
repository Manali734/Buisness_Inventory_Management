package com.inventory.model.dto;

import com.inventory.model.Supplier;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class ProductDto {
    private Long productId;
    private String productName;
    private String description;
    private Double productPrice;
    private int quantity;
    private String category;
    private Supplier supplier;
}
