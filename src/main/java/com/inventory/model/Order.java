package com.inventory.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    private String status;
    private Double totalAmount;
    private LocalDateTime orderDate;
    //Many Orders → One Customer
    @ManyToOne
    private Customer customer;
    @ManyToMany
    private List<Product> products;

}
