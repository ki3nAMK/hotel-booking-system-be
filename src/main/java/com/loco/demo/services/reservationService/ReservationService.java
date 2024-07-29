package com.loco.demo.services.reservationService;

import com.loco.demo.DTO.JSON.ReservationRequest;
import com.loco.demo.entity.Reservation;

public interface ReservationService {
    public Reservation makeReservation(String id, ReservationRequest reservationRequest);
}
