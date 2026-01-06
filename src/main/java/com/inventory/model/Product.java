package com.inventory.model;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    private String productName;
    private String description;
    private Double productPrice;
    private int quantity;
    private String category;
    //Many Products → One Supplier
    @ManyToOne
    private Supplier supplier;

}
