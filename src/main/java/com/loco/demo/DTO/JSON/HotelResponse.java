package com.loco.demo.DTO.JSON;

import java.util.List;

import com.loco.demo.entity.Hotel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class HotelResponse {
    private List<Hotel> items;
    private long total;

}
