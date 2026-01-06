package com.inventory.model;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Entity
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long appUserId;
    @Column(nullable = false)
    private String appUserName;
    @Column(unique = true)
    private String appUserContact;
    private String appUserAddress;
    private String username;
    private String password;
}
