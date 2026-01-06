package com.inventory.model.dto;

import com.inventory.model.Product;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Data
public class SupplierDto {
    private Long supplierId;
    private String supplierName;
    private String supplierContact;
    private String supplierEmail;
    private String supplierAddress;
    private String gstNumber;
    private List<Product> products;
}
