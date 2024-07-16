package com.loco.demo.DTO.JSON;

import com.loco.demo.AuthenModel.User;
import com.loco.demo.DTO.Status.StatusAuthenticationRegister;

public record SchemaResponseStatusAuthenticationRegister(

        String message,
        String code,
        StatusAuthenticationRegister statusAuthenticationRegister,
        User applicationUser

) {
}
