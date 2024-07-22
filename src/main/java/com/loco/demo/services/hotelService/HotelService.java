package com.loco.demo.services.hotelService;

import com.loco.demo.DTO.JSON.ListResponse;

import com.loco.demo.entity.Hotel;
import com.loco.demo.entity.HotelDetail;

public interface HotelService {
    public ListResponse<Hotel> getListByCriteria(int page, int limit, Integer type, String name, Integer minPrice,
            Integer maxPrice, Integer bed,
            Integer bathroom, Integer car, Integer pet, String amenity, String safety);

    public HotelDetail getDetailByHotelSlug(String slug);

    public Hotel getHotelById(String id);
}
