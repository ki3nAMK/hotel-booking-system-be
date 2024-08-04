package com.loco.demo.Controller.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.loco.demo.AuthenModel.User;
import com.loco.demo.DTO.JSON.ListResponse;
import com.loco.demo.DTO.JSON.ReservationRequest;
import com.loco.demo.DTO.JSON.UpdateCancelRservationForm;
import com.loco.demo.entity.Reservation;
import com.loco.demo.services.reservationService.ReservationService;
import com.loco.demo.services.userService.UserService;

@RequestMapping("/api/v1/client")
@RestController
@CrossOrigin("*")
public class UserReservationController {
    private UserService userService;
    private ReservationService reservationService;

    @Autowired
    public UserReservationController(UserService userService, ReservationService reservationService) {
        this.userService = userService;
        this.reservationService = reservationService;
    }

    @PostMapping("/reservation/{id}")
    public Reservation makeReservation(@PathVariable String id, @RequestBody ReservationRequest request) {
        return reservationService.makeReservation(id, request);
    }

    @GetMapping("/reservation")
    public ListResponse<Reservation> getListReservation(@RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int limit, @RequestParam(required = false) String type) {
        User user=userService.getMyInfo();
        return reservationService.getListReservation(page-1,limit,true,user,type);
    }

    @PutMapping("/cancel/reservation/{id}")
    public Reservation cancelReservation(@PathVariable String id, @RequestBody UpdateCancelRservationForm updateCancelRservationForm){
        return reservationService.cancelReservation(id,updateCancelRservationForm);
    }
}
