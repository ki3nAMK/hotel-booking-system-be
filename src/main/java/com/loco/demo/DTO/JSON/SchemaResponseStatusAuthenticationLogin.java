package com.loco.demo.DTO.JSON;

import com.loco.demo.AuthenModel.User;
import com.loco.demo.DTO.Status.StatusAuthenticationLogin;

public record SchemaResponseStatusAuthenticationLogin(
        StatusAuthenticationLogin statusAuthenticationLogin ,
        User applicationUser
) {
}