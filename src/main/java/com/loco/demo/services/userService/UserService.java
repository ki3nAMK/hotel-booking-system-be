package com.loco.demo.services.userService;

import com.loco.demo.DTO.JSON.UpdateUserForm;
import com.loco.demo.utils.Converters.SecureUser;

public interface UserService {
    public SecureUser updateUser(String id, UpdateUserForm updateUserForm);
    public SecureUser updateUserPhone(String id, UpdateUserForm updateUserForm);
    public SecureUser updateUserLocation(String id, UpdateUserForm updateUserForm);
    public SecureUser updateUserEmail(String id, UpdateUserForm updateUserForm);
    public boolean checkIdAndRole(String id);
}
