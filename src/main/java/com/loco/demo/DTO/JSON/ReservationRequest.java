package com.loco.demo.DTO.JSON;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservationRequest {
    private Date checkIn;
    private Date checkOut;
    private Integer guest;
}
