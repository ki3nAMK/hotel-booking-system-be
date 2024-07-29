package com.loco.demo.services.reservationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loco.demo.AuthenModel.User;
import com.loco.demo.DTO.JSON.ReservationRequest;
import com.loco.demo.entity.Hotel;
import com.loco.demo.entity.Reservation;
import com.loco.demo.repository.ReservationRepo.ReservationRepo;
import com.loco.demo.services.hotelService.HotelService;
import com.loco.demo.services.userService.UserService;

@Service
public class ReservationServiceImpl implements ReservationService{
    private ReservationRepo reservationRepo;
    private UserService userService;
    private HotelService hotelService;

    @Autowired
    public ReservationServiceImpl(ReservationRepo reservationRepo, UserService userService, HotelService hotelService) {
        this.reservationRepo = reservationRepo;
        this.userService = userService;
        this.hotelService = hotelService;
    }

    @Override
    public Reservation makeReservation(String id, ReservationRequest reservationRequest) {
        User myUser=userService.getMyInfo();
        Hotel reserveHotel=hotelService.getHotelById(id);

        Reservation reservation=new Reservation();
        reservation.setCheckIn(reservationRequest.getCheckIn());
        reservation.setCheckOut(reservationRequest.getCheckOut());
        reservation.setGuest(reservationRequest.getGuest());
        reservation.setUser(myUser);
        reservation.setPrice(reserveHotel.getPrice());
        reservation.setIsPast(false);
        reservation.setStatus((byte)0);
        reservation.setOwner(reserveHotel.getOriginalUser());
        reservation.setHotel(reserveHotel);
        
        return reservationRepo.save(reservation);
    }
}
