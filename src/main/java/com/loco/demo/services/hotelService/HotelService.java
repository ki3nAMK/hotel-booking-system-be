package com.loco.demo.services.hotelService;

import com.loco.demo.DTO.JSON.HotelResponse;
import com.loco.demo.entity.HotelDetail;

public interface HotelService {
    public HotelResponse getListByCriteria(int page, int limit, Integer type, String name, Integer minPrice,
            Integer maxPrice, Integer bed,
            Integer bathroom, Integer car, Integer pet, String amenity, String safety);
    public HotelDetail getDetailByHotelSlug(String slug);
}
