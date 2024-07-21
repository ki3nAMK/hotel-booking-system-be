package com.loco.demo.DTO.JSON;

import com.loco.demo.DTO.Status.StatusAuthenticationRegister;
import com.loco.demo.utils.Converters.SecureUser;

public record SchemaResponseStatusAuthenticationRegister(

        String message,
        String code,
        StatusAuthenticationRegister statusAuthenticationRegister,
        SecureUser applicationUser

) {
}
