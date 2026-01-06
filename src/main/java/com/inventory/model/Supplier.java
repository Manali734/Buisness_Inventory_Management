package com.inventory.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Entity
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long supplierId;
    private String supplierName;
    @Column(unique = true)
    private String supplierContact;
    @Column(unique = true)
    private String supplierEmail;
    private String supplierAddress;
    @Column(unique = true)
    private String gstNumber;
    //One Supplier → Many Products
    @OneToMany
    private List<Product> products;

}
