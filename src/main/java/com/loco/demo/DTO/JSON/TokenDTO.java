package com.loco.demo.DTO.JSON;

import java.time.Instant;
import java.util.Date;

public record TokenDTO(
        String token,
        Instant expiredAt
) {
}
