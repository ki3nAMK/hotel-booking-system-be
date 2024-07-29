package com.loco.demo.services.reservationService;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loco.demo.AuthenModel.User;
import com.loco.demo.DTO.JSON.ReservationRequest;
import com.loco.demo.entity.Hotel;
import com.loco.demo.entity.Reservation;
import com.loco.demo.repository.ReservationRepo.ReservationRepo;
import com.loco.demo.services.hotelService.HotelService;
import com.loco.demo.services.notificationService.NotificationService;
import com.loco.demo.services.userService.UserService;

@Service
public class ReservationServiceImpl implements ReservationService {
    private ReservationRepo reservationRepo;
    private UserService userService;
    private HotelService hotelService;
    private NotificationService notificationService;

    @Autowired
    public ReservationServiceImpl(ReservationRepo reservationRepo, UserService userService, HotelService hotelService,
            NotificationService notificationService) {
        this.reservationRepo = reservationRepo;
        this.userService = userService;
        this.hotelService = hotelService;
        this.notificationService = notificationService;
    }

    @Override
    public Reservation makeReservation(String id, ReservationRequest reservationRequest) {
        User myUser = userService.getMyInfo();
        Hotel reserveHotel = hotelService.getHotelById(id);

        Reservation reservation = new Reservation();
        reservation.setCheckIn(reservationRequest.getCheckIn());
        reservation.setCheckOut(reservationRequest.getCheckOut());
        reservation.setGuest(reservationRequest.getGuest());
        reservation.setUser(myUser);
        reservation.setPrice(reserveHotel.getPrice());
        reservation.setIsPast(false);
        reservation.setStatus((byte) 0);
        reservation.setOwner(reserveHotel.getOriginalUser());
        reservation.setHotel(reserveHotel);
        reservation.setId(UUID.randomUUID().toString());

        Date creatAt = new Date();

        notificationService.makeNotification(myUser,
                "You have succesfully made a reservation for " + reserveHotel.getName() +
                 "!\nPlease contact the owner if you have any questions.",
                creatAt);
        notificationService.makeNotification(reserveHotel.getOriginalUser(),
                "You have a new reservation at " + reserveHotel.getName() +
                 "!\nPlease check the guest profile and booking details to ensure everything is prepared for their arrival.",
                creatAt);

        return reservationRepo.save(reservation);
    }
}
