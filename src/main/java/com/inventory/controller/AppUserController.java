package com.inventory.controller;

import com.inventory.model.dto.AppUserDto;
import com.inventory.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory/admin")
public class AppUserController {
    @Autowired
    private AppUserService appUserService;
    // Create single category
    @PostMapping("/create-admin")
    public ResponseEntity<?> createAdmin(@RequestBody AppUserDto appUserDto) {

        try {
            return ResponseEntity.ok(appUserService.createAdmin(appUserDto));
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body("Admin already exists");
        }
    }


    @DeleteMapping("/delete-admin-by-id/{id}")
    public boolean deleteAdmin(@PathVariable("id") Long adminId){

        return appUserService.deleteAdmin(adminId);
    }

    @PutMapping("/update-admin")
    public AppUserDto updateAdmin(@RequestBody AppUserDto appUserDto){

        return appUserService.updateAdmin(appUserDto);
    }

}
