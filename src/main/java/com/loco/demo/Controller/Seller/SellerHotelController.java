package com.loco.demo.Controller.Seller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.loco.demo.DTO.JSON.ExceptionResponseHandler;
import com.loco.demo.DTO.JSON.HotelDTO;
import com.loco.demo.DTO.JSON.HotelForm;
import com.loco.demo.DTO.JSON.ListResponse;
import com.loco.demo.DTO.Status.StatusResponseAPI;
import com.loco.demo.entity.Hotel;
import com.loco.demo.services.hotelService.HotelService;

@RequestMapping("/api/v1/seller")
@RestController
@CrossOrigin("*")
public class SellerHotelController {
    private HotelService hotelService;

    @Autowired
    public SellerHotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping("/my-hotel")
    public ListResponse<HotelDTO> getMyListHotel(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int limit){
        return hotelService.getMyListHotel(page-1,limit);
    }

    @PostMapping("/my-hotel/add")
    public Hotel addHotel(@RequestBody HotelForm hotelForm){
        return hotelService.addHotel(hotelForm);
    }

    @PutMapping("/my-hotel/edit/{id}")
    public Hotel updateHotel(@PathVariable String id,@RequestBody HotelForm hotelForm){
        return hotelService.updateHotel(id,hotelForm);
    }

    @DeleteMapping("/hotel/{id}/delete")
    public ResponseEntity<ExceptionResponseHandler> deleteHotel(@PathVariable String id){
        hotelService.deleteHotel(id);
        return ResponseEntity.ok()
                .body(new ExceptionResponseHandler(StatusResponseAPI.OK, "000", "Successful deletion", ""));
    }
}
