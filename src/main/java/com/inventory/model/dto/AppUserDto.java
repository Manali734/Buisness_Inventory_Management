package com.inventory.model.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class AppUserDto {
    private Long appUserId;
    private String appUserName;
    private String appUserContact;
    private String appUserAddress;
    private String username;
    private String password;
}
