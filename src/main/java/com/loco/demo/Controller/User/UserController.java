package com.loco.demo.Controller.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loco.demo.DTO.JSON.UpdateUserForm;
import com.loco.demo.services.userService.UserService;
import com.loco.demo.utils.Converters.SecureUser;

@RequestMapping("/api/v1/client")
@RestController
@CrossOrigin("*")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PutMapping("/{id}")
    public SecureUser updateUser(@PathVariable String id, @RequestBody UpdateUserForm updateUserForm){
        return userService.updateUser(id,updateUserForm);
    }

    @PutMapping("/{id}/phone")
    public SecureUser updateUserPhone(@PathVariable String id, @RequestBody UpdateUserForm updateUserForm){
        return userService.updateUserPhone(id,updateUserForm);
    }

    @PutMapping("/{id}/location")
    public SecureUser updateUserLocation(@PathVariable String id, @RequestBody UpdateUserForm updateUserForm){
        return userService.updateUserLocation(id,updateUserForm);
    }

    @PutMapping("/{id}/email")
    public SecureUser updateUserEmail(@PathVariable String id, @RequestBody UpdateUserForm updateUserForm){
        return userService.updateUserEmail(id,updateUserForm);
    }
}
