package com.loco.demo.services.hotelService;

import com.loco.demo.DTO.JSON.HotelDTO;
import com.loco.demo.DTO.JSON.HotelForm;
import com.loco.demo.DTO.JSON.ListResponse;

import com.loco.demo.entity.Hotel;

public interface HotelService {
    public ListResponse<HotelDTO> getListByCriteria(int page, int limit, Integer type, String name, Integer minPrice,
            Integer maxPrice, Integer bed,
            Integer bathroom, Integer car, Integer pet, String amenity, String safety);

    public Hotel getDetailByHotelSlug(String slug);

    public Hotel getHotelById(String id);

    public ListResponse<HotelDTO> getMyListHotel(int page,int limit);

    public Hotel addHotel(HotelForm hotelForm);

    public Hotel updateHotel(String id,HotelForm hotelForm);

    public void deleteHotel(String id);
}
