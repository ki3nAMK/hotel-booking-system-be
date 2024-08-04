package com.loco.demo.Controller.Seller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.loco.demo.AuthenModel.User;
import com.loco.demo.DTO.JSON.ListResponse;
import com.loco.demo.DTO.JSON.UpdateReservationForm;
import com.loco.demo.entity.Reservation;
import com.loco.demo.services.reservationService.ReservationService;
import com.loco.demo.services.userService.UserService;

@RequestMapping("/api/v1/seller")
@RestController
@CrossOrigin("*")
public class SellerController {
    private ReservationService reservationService;
    private UserService userService;

    @Autowired
    public SellerController(ReservationService reservationService, UserService userService) {
        this.reservationService = reservationService;
        this.userService = userService;
    }

    @GetMapping("/reservation")
    public ListResponse<Reservation> getListReservation(@RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int limit, @RequestParam(required = false) String type) {
        User owner = userService.getMyInfo();
        return reservationService.getListReservation(page - 1, limit, false, owner, type);
    }

    @PutMapping("/reservation/{id}")
    public Reservation updateReservation(@PathVariable String id,
            @RequestBody UpdateReservationForm updateReservationForm) {
        return reservationService.updateReservation(id, updateReservationForm);
    }
}
