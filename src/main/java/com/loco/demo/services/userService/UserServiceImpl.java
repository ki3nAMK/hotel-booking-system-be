package com.loco.demo.services.userService;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.loco.demo.AuthenModel.User;
import com.loco.demo.DTO.JSON.LocationForm;
import com.loco.demo.DTO.JSON.UpdateUserForm;
import com.loco.demo.entity.Location;
import com.loco.demo.repository.AuthenRepo.UserAuthenRepo;
import com.loco.demo.utils.Converters.SecureUser;

@Service
public class UserServiceImpl implements UserService {
    private UserAuthenRepo userAuthenRepo;

    @Autowired
    public UserServiceImpl(UserAuthenRepo userAuthenRepo) {
        this.userAuthenRepo = userAuthenRepo;
    }

    @Override
    public SecureUser updateUser(String id, UpdateUserForm updateUserForm) {
        User existUser = userAuthenRepo.findUserByUserId(id)
                .orElseThrow(() -> new UsernameNotFoundException("NO USERNAME FOUND IN DATABASE !!!"));
        existUser.setName(updateUserForm.getName());
        existUser.setGender(updateUserForm.getGender());
        existUser.setBirthday(updateUserForm.getBirthday());
        userAuthenRepo.save(existUser);
        return new SecureUser(existUser);
    }

    @Override
    public SecureUser updateUserPhone(String id, UpdateUserForm updateUserForm) {
        User existUser = userAuthenRepo.findUserByUserId(id)
                .orElseThrow(() -> new UsernameNotFoundException("NO USERNAME FOUND IN DATABASE !!!"));
        User existPhone = userAuthenRepo.findUserByPhoneNumber(updateUserForm.getPhone()).orElse(null);
        if (existPhone == null) {
            existUser.setPhoneNumber(updateUserForm.getPhone());
            userAuthenRepo.save(existUser);
            return new SecureUser(existUser);
        } else {
            throw new RuntimeException("Phone number already exists");
        }
    }

    @Override
    public SecureUser updateUserLocation(String id, @RequestBody UpdateUserForm updateUserForm) {
        User existUser = userAuthenRepo.findUserByUserId(id)
                .orElseThrow(() -> new UsernameNotFoundException("NO USERNAME FOUND IN DATABASE !!!"));
        LocationForm locationForm = updateUserForm.getLocationForm();
        existUser.setLocation(locationForm.getLocation());
        existUser.setAddress(new Location(UUID.randomUUID().toString(), locationForm.getXCoordinate(),
                locationForm.getYCoordinate()));
        userAuthenRepo.save(existUser);
        return new SecureUser(existUser);
    }

    @Override
    public SecureUser updateUserEmail(String id, UpdateUserForm updateUserForm) {
        User existUser = userAuthenRepo.findUserByUserId(id)
                .orElseThrow(() -> new UsernameNotFoundException("NO USERNAME FOUND IN DATABASE !!!"));
        User existEmail = userAuthenRepo.findUserByEmail(updateUserForm.getEmail()).orElse(null);
        if (existEmail == null) {
            existUser.setEmail(updateUserForm.getEmail());
            userAuthenRepo.save(existUser);
            return new SecureUser(existUser);
        } else {
            throw new RuntimeException("Email already exists");
        }
    }
}
