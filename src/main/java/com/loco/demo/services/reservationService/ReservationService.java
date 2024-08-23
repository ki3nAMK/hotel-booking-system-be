package com.loco.demo.services.reservationService;

import java.util.List;

import com.loco.demo.AuthenModel.User;
import com.loco.demo.DTO.JSON.ListResponse;
import com.loco.demo.DTO.JSON.ReservationRequest;
import com.loco.demo.DTO.JSON.UpdateCancelRservationForm;
import com.loco.demo.DTO.JSON.UpdateReservationForm;
import com.loco.demo.entity.Reservation;

public interface ReservationService {
    public Reservation makeReservation(String id, ReservationRequest reservationRequest);

    public ListResponse<Reservation> getListReservation(int page, int limit, boolean isUser, User user, String type);

    public Reservation updateReservation(String id, UpdateReservationForm updateReservationForm);

    public Reservation cancelReservation(String id, UpdateCancelRservationForm updateCancelRservationForm);

    public List<Reservation> findByHotelIdAndUser(String id,User user);
}
