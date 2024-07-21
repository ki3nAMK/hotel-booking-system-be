package com.loco.demo.Controller.Authen;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.loco.demo.entity.Hotel;
import com.loco.demo.entity.HotelDetail;
import com.loco.demo.services.hotelService.HotelService;

@RequestMapping("/hotel")
@RestController
@CrossOrigin("*")
public class HotelController {
    private HotelService hotelService;

    @Autowired
    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping
    public List<Hotel> getListOfHotel(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int limit) {
        return hotelService.getListOfHotel(page, limit);
    }

    @GetMapping("/criteria")
    public List<Hotel> getListByCriteria(@RequestParam(required = false) Integer type,
            @RequestParam(required = false) String name, @RequestParam(required = false) Integer minPrice,
            @RequestParam(required = false) Integer maxPrice, @RequestParam(required = false) Integer bed,
            @RequestParam(required = false) Integer bathroom, @RequestParam(required = false) Integer car,
            @RequestParam(required = false) Integer pet,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int limit,
            @RequestParam(required = false) String amenity,
            @RequestParam(required = false) String safety) {
        return hotelService.getListByCriteria(page, limit, type, name, minPrice, maxPrice, bed, bathroom, car, pet,
                amenity, safety);
    }

    @GetMapping("/detail")
    public List<HotelDetail> getDetailListByHoelSlug(@RequestParam(required = false) String slug,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int limit) {
        return hotelService.getDetailByHotelSlug(slug, page, limit);
    }
}
