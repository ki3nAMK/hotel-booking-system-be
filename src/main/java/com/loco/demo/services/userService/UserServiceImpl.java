package com.loco.demo.services.userService;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.loco.demo.AuthenModel.Role;
import com.loco.demo.AuthenModel.User;
import com.loco.demo.DTO.JSON.LocationForm;
import com.loco.demo.DTO.JSON.UpdateUserForm;
import com.loco.demo.entity.Location;
import com.loco.demo.repository.AuthenRepo.RoleAuthenRepo;
import com.loco.demo.repository.AuthenRepo.UserAuthenRepo;
import com.loco.demo.services.authenService.AuthenticationService;
import com.loco.demo.utils.Converters.SecureUser;

@Service
public class UserServiceImpl implements UserService {
    private final UserAuthenRepo userAuthenRepo;
    private final AuthenticationService authenticationService;
    private final RoleAuthenRepo roleAuthenRepo;

    @Autowired
    public UserServiceImpl(UserAuthenRepo userAuthenRepo, AuthenticationService authenticationService,
            RoleAuthenRepo roleAuthenRepo) {
        this.userAuthenRepo = userAuthenRepo;
        this.authenticationService = authenticationService;
        this.roleAuthenRepo = roleAuthenRepo;
    }

    @Override
    public SecureUser updateUser(String id, UpdateUserForm updateUserForm) {
        if (updateUserForm.getLocationForm() != null || updateUserForm.getPhone() != null
                || updateUserForm.getEmail() != null) {
            throw new IllegalArgumentException("Illegal update field !!! (Just name, birthday, gender)");
        }
        if (checkIdAndRole(id)) {
            User existUser = userAuthenRepo.findUserByUserId(id)
                    .orElseThrow(() -> new UsernameNotFoundException("NO USERNAME FOUND IN DATABASE !!!"));
            existUser.setName(updateUserForm.getName());
            existUser.setGender(updateUserForm.getGender());
            existUser.setBirthday(updateUserForm.getBirthday());
            userAuthenRepo.save(existUser);
            return new SecureUser(existUser);
        }
        throw new RuntimeException("You can't update another user account !!!");
    }

    @Override
    public SecureUser updateUserPhone(String id, UpdateUserForm updateUserForm) {
        if (updateUserForm.getLocationForm() != null || updateUserForm.getName() != null
                || updateUserForm.getEmail() != null || updateUserForm.getGender() != null
                || updateUserForm.getBirthday() != null) {
            throw new IllegalArgumentException("Illegal update field !!! (Just phone)");
        }
        if (checkIdAndRole(id)) {
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
        throw new RuntimeException("You can't update another user account !!!");
    }

    @Override
    public SecureUser updateUserLocation(String id, @RequestBody UpdateUserForm updateUserForm) {
        if (updateUserForm.getPhone() != null || updateUserForm.getName() != null
                || updateUserForm.getEmail() != null || updateUserForm.getGender() != null
                || updateUserForm.getBirthday() != null) {
            throw new IllegalArgumentException("Illegal update field !!! (Just location)");
        }
        if (checkIdAndRole(id)) {
            User existUser = userAuthenRepo.findUserByUserId(id)
                    .orElseThrow(() -> new UsernameNotFoundException("NO USERNAME FOUND IN DATABASE !!!"));
            LocationForm locationForm = updateUserForm.getLocationForm();
            existUser.setLocation(locationForm.getLocation());
            existUser.setAddress(new Location(UUID.randomUUID().toString(), locationForm.getXCoordinate(),
                    locationForm.getYCoordinate()));
            userAuthenRepo.save(existUser);
            return new SecureUser(existUser);
        }
        throw new RuntimeException("You can't update another user account !!!");
    }

    @Override
    public SecureUser updateUserEmail(String id, UpdateUserForm updateUserForm) {
        if (updateUserForm.getPhone() != null || updateUserForm.getName() != null
                || updateUserForm.getLocationForm() != null || updateUserForm.getGender() != null
                || updateUserForm.getBirthday() != null) {
            throw new IllegalArgumentException("Illegal update field !!! (Just email)");
        }
        if (checkIdAndRole(id)) {
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
        throw new RuntimeException("You can't update another user account !!!");
    }

    @Override
    public boolean checkIdAndRole(String id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String usernameFromToken = authentication.getName();
        User getUserFromDB = authenticationService.getDataFromUserNameService(usernameFromToken);
        Role role = roleAuthenRepo.findByAuthority("ADMIN").get();
        if (id.equals(getUserFromDB.getUserId())) {
            return true;
        } else if (getUserFromDB.getAuthorities().contains(role)) {
            return true;
        }
        return false;
    }

    public User getMyInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userAuthenRepo.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("NO USERNAME FOUND IN DATABASE !!!"));
        return user;
    }
    
    public List<User> findOnlineUser(boolean status) {
        return userAuthenRepo.findUsersByOnlineStatus(status);
    }

    public User handleChangeStatus(boolean status,String id) {
        User storedUser = userAuthenRepo.findUserByUserId(id).isPresent()
                ?  userAuthenRepo.findUserByUserId(id).get()
                : null;
        if ( storedUser != null) {
            storedUser.setOnlineStatus(status);
            userAuthenRepo.save(storedUser);
        }
        return storedUser;
    }

    public User disconnect(String id) {
        return handleChangeStatus(false,id);
    }

    public User connect(String id) {
        return handleChangeStatus(true,id);
    }

    public List<User> findAdminAndSeller() {
        return userAuthenRepo.findUsersByRoleAuthority();
    }
}
