package com.loco.demo.services.reservationService;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.loco.demo.AuthenModel.Role;
import com.loco.demo.AuthenModel.User;
import com.loco.demo.DTO.JSON.ListResponse;
import com.loco.demo.DTO.JSON.ReservationRequest;
import com.loco.demo.DTO.JSON.UpdateCancelRservationForm;
import com.loco.demo.DTO.JSON.UpdateReservationForm;
import com.loco.demo.entity.Hotel;
import com.loco.demo.entity.Reservation;
import com.loco.demo.repository.AuthenRepo.RoleAuthenRepo;
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
        private RoleAuthenRepo roleAuthenRepo;

        @Autowired
        public ReservationServiceImpl(ReservationRepo reservationRepo, UserService userService,
                        HotelService hotelService,
                        NotificationService notificationService, RoleAuthenRepo roleAuthenRepo) {
                this.reservationRepo = reservationRepo;
                this.userService = userService;
                this.hotelService = hotelService;
                this.notificationService = notificationService;
                this.roleAuthenRepo = roleAuthenRepo;
        }

        @Override
        public Reservation makeReservation(String id, ReservationRequest reservationRequest) {
                User myUser = userService.getMyInfo();
                Hotel reserveHotel = hotelService.getHotelById(id);
                Reservation existHotelReservation = reservationRepo.findByHotel(reserveHotel).orElse(null);
                if (existHotelReservation != null && existHotelReservation.getStatus() == 0) {
                        throw new RuntimeException(
                                "This hotel is in pending. Please wait until the owner completely processing.");
                }

                Reservation reservation = new Reservation();
                reservation.setCheckIn(reservationRequest.getCheckIn());
                reservation.setCheckOut(reservationRequest.getCheckOut());
                reservation.setGuest(reservationRequest.getGuest());
                reservation.setUser(myUser);
                reservation.setPrice(reserveHotel.getPrice());
                reservation.setIsPast(false);
                reservation.setStatus((byte) 0);
                reservation.setOwner(reserveHotel.OriginalUser());
                reservation.setHotel(reserveHotel);
                reservation.setId(UUID.randomUUID().toString());

                Date creatAt = new Date();

                notificationService.makeNotification(myUser,
                                "You have succesfully made a reservation for " + reserveHotel.getName() +
                                                "!\nPlease contact the owner if you have any questions.",
                                creatAt);
                notificationService.makeNotification(reserveHotel.OriginalUser(),
                                "You have a new reservation at " + reserveHotel.getName() +
                                                "!\nPlease check the guest profile and booking details to ensure everything is prepared for their arrival.",
                                creatAt);

                return reservationRepo.save(reservation);
        }

        @Override
        public ListResponse<Reservation> getListReservation(int page, int limit, boolean isUser, User user,
                        String type) {
                Pageable pageable = PageRequest.of(page, limit);
                Boolean choosen = null;
                Byte status = null;
                if (type != null && type.equals("upcoming"))
                        choosen = false;
                else if (type != null && type.equals("past"))
                        choosen = true;
                if (!isUser) {
                        if (type != null && type.equals("approve"))
                                status = (byte) 1;
                        else if (type != null && type.equals("reject"))
                                status = (byte) 2;
                        else if (type != null && type.equals("cancelled"))
                                status = (byte) 3;
                }
                Page<Reservation> pageReservation = isUser ? reservationRepo.findByUser(user, pageable, choosen)
                                : reservationRepo.findByOwner(user, pageable, status, choosen);
                return new ListResponse<Reservation>(pageReservation.getContent(), pageReservation.getTotalElements());
        }

        @Override
        public Reservation updateReservation(String id, UpdateReservationForm updateReservationForm) {
                User user = userService.getMyInfo();
                Role role = roleAuthenRepo.findByAuthority("SELLER").get();
                Reservation reservation = reservationRepo.findById(id).orElseThrow(
                                () -> new UsernameNotFoundException("NO RESERVATION FOUND IN DATABASE!!!"));
                if (user.getAuthorities().contains(role)) {
                        if (user.getUserId().equals(reservation.getOwner().getUserId())) {
                                Byte status = updateReservationForm.getStatus();
                                if (!(status == 1 || status == 2)) {
                                        throw new RuntimeException("This reservation can not be updated");
                                }
                                reservation.setStatus(status);
                                notificationService.makeNotification(reservation.OriginalUser(),
                                                "FROM SELLER " + reservation.getOwner().getName() + " : "
                                                                + updateReservationForm.getNote(),
                                                new Date());
                                return reservation;
                        } else
                                throw new RuntimeException("You have no ability to update this reservation");
                } else
                        throw new AccessDeniedException("Your role must be a salesperson to perform this function");
        }

        @Override
        public Reservation cancelReservation(String id, UpdateCancelRservationForm updateCancelRservationForm) {
                User user = userService.getMyInfo();
                Reservation reservation = reservationRepo.findById(id).orElseThrow(
                                () -> new UsernameNotFoundException("NO RESERVATION FOUND IN DATABASE!!!"));
                if (user.getUserId().equals(reservation.getUser().getUserId())) {
                        reservation.setStatus((byte) 3);
                        notificationService.makeNotification(reservation.OriginalOwner(),
                                        "FROM USER " + reservation.getOwner().getName() + " BOOKING "
                                                        + reservation.getHotel().getName() + ": " +
                                                        updateCancelRservationForm.getNote(),
                                        new Date());
                        return reservation;
                } else
                        throw new RuntimeException("You have no ability to update this reservation");
        }
}
