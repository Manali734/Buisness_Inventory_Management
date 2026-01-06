package com.inventory.model.dto;

import com.inventory.model.CutomerOrder;
import com.inventory.model.Order;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Data
public class CustomerDto {
    private Long customerId;
    private String customerName;
    private String customerContact;
    private String customerAddress;
    private String customerEmail;
    private List<CutomerOrder> cutomerOrder;
}
