package com.loco.demo.DTO.JSON;

import com.loco.demo.AuthenModel.User;

public record RegisterServiceReturnDTO(
        User user,
        String message
) {
}
