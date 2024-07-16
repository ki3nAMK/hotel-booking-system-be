package com.loco.demo.DTO.JSON;

import com.loco.demo.DTO.Status.StatusResponseAPI;

public record ResponseSendMail (
        StatusResponseAPI status,
        String code,
        String message,
        String errorMessage
) {
}
