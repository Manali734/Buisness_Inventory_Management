package com.inventory.service.impl;

import com.inventory.model.AppUser;
import com.inventory.model.dto.AppUserDto;
import com.inventory.repository.AppUserRepository;
import com.inventory.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class AppUserServiceImpl implements AppUserService {
    @Autowired
    private AppUserRepository appUserRepository;
    @Autowired
    private PaginationResponseImpl paginationResponse;

    @Override
    public AppUserDto createAdmin(AppUserDto appUserDto) {
        AppUser appUser = new AppUser();
        appUser.setAppUserId(appUserDto.getAppUserId());
        appUser.setAppUserName(appUserDto.getAppUserName());
        appUser.setAppUserAddress(appUserDto.getAppUserAddress());
        appUser.setAppUserContact(appUserDto.getAppUserContact());
        appUser.setUsername(appUserDto.getUsername());
        appUser.setPassword(appUserDto.getPassword());
        AppUser save = appUserRepository.save(appUser);
        AppUserDto dto = new AppUserDto();
        dto.setAppUserId(save.getAppUserId());
        return dto;
    }

    @Override
    public AppUserDto updateAdmin(AppUserDto appUserDto) {
        AppUser appUser1 = new AppUser();
        appUser1.setAppUserId(appUserDto.getAppUserId());
        appUser1.setAppUserName(appUserDto.getAppUserName());
        appUser1.setAppUserAddress(appUserDto.getAppUserAddress());
        appUser1.setAppUserContact(appUserDto.getAppUserContact());
        AppUser save = appUserRepository.save(appUser1);
        AppUserDto dto = new AppUserDto();
        dto.setAppUserId(save.getAppUserId());
        return dto;
    }

    @Override
    public Boolean deleteAdmin(Long id) {
        Optional<AppUser> byId = appUserRepository.findById(id);
        if(byId.isPresent()){
            appUserRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
