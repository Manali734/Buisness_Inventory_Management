package com.inventory.service;

import com.inventory.model.dto.AppUserDto;
import org.springframework.stereotype.Service;

@Service
public interface AppUserService {
    AppUserDto createAdmin(AppUserDto appUserDto);
    AppUserDto updateAdmin(AppUserDto appUserDto);
    Boolean deleteAdmin(Long id);

}
