package com.loco.demo.Controller.Hotel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.loco.demo.DTO.JSON.HotelResponse;
import com.loco.demo.entity.HotelDetail;
import com.loco.demo.services.hotelService.HotelService;

@RequestMapping("/api/v1/hotel")
@RestController
@CrossOrigin("*")
public class HotelController {
    private HotelService hotelService;

    @Autowired
    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping()
    public HotelResponse getListByCriteria(@RequestParam(required = false) Integer type,
            @RequestParam(required = false) String name, @RequestParam(required = false) Integer minPrice,
            @RequestParam(required = false) Integer maxPrice, @RequestParam(required = false) Integer bed,
            @RequestParam(required = false) Integer bathroom, @RequestParam(required = false) Integer car,
            @RequestParam(required = false) Integer pet,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int limit,
            @RequestParam(required = false) String amenity,
            @RequestParam(required = false) String safety) {
        return hotelService.getListByCriteria(page-1, limit, type, name, minPrice, maxPrice, bed, bathroom, car, pet,
                amenity, safety);
    }

    @GetMapping("/{slug}")
    public HotelDetail getDetailListByHoelSlug(@PathVariable String slug){
        return hotelService.getDetailByHotelSlug(slug);
    }
}
