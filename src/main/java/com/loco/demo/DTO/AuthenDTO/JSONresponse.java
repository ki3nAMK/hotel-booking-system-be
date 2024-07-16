package com.loco.demo.DTO.AuthenDTO;

import com.loco.demo.AuthenModel.User;

public record JSONresponse (
        User user,
        String message
) {
}

