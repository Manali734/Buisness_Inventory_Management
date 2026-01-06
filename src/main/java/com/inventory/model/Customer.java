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
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;
    @Column(nullable = false)
    private String customerName;
    @Column(unique = true)
    private String customerContact;
    private String customerAddress;
    @Column(unique = true)
    private String customerEmail;
    @OneToMany(mappedBy = "customer")
    private List<CutomerOrder> cutomerOrder;
}
