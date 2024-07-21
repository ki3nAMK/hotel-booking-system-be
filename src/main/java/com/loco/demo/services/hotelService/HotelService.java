package com.loco.demo.services.hotelService;

import java.util.List;

import com.loco.demo.entity.Hotel;
import com.loco.demo.entity.HotelDetail;

public interface HotelService {
    public List<Hotel> getListOfHotel(int page, int limit);

    public List<Hotel> getListByCriteria(int page, int limit, Integer type, String name, Integer minPrice,
            Integer maxPrice, Integer bed,
            Integer bathroom, Integer car, Integer pet, String amenity, String safety);
    public List<HotelDetail> getDetailByHotelSlug(String slug, int page, int limit);
}
