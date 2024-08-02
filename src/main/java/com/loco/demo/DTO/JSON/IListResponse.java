package com.loco.demo.DTO.JSON;

import java.util.List;

public record IListResponse<T>(
        List<T> items,
        int total
) {
}
