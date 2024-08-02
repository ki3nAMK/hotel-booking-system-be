package com.loco.demo.DTO.JSON.Socket;

import java.util.Date;

public record MessageRequest(
        String senderId,
        String recipientId,
        String content,
        Date timestamp
) {
}
