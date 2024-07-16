package com.loco.demo.DTO.JSON;

public record RegisterForm(
        String username,
        String password,
        String email,
        String phoneNumber
) {
}
