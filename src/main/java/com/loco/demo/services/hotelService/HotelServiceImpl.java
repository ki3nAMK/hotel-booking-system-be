package com.loco.demo.services.hotelService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.loco.demo.entity.Hotel;
import com.loco.demo.entity.HotelDetail;
import com.loco.demo.repository.HotelRepo.HotelRepo;

@Service
public class HotelServiceImpl implements HotelService {
    private final HotelRepo hotelRepo;

    @Autowired
    public HotelServiceImpl(HotelRepo hotelRepo) {
        this.hotelRepo = hotelRepo;
    }

    @Override
    public List<Hotel> getListOfHotel(int page, int limit) {
        Pageable pageable = PageRequest.of(page, limit);
        Page<Hotel> hotelPage = hotelRepo.findAll(pageable);
        return hotelPage.getContent();
    }

    @Override
    public List<Hotel> getListByCriteria(int page, int limit, Integer type, String name, Integer minPrice,
            Integer maxPrice, Integer bed,
            Integer bathroom, Integer car, Integer pet, String amenity, String safety) {
        Pageable pageable = PageRequest.of(page, limit);
        return hotelRepo.findHotelsByCriteria(type, name, minPrice, maxPrice, bed,
                bathroom, car, pet, amenity, safety, pageable).getContent();
    }

    @Override
    public List<HotelDetail> getDetailByHotelSlug(String slug, int page, int limit) {
        Pageable pageable = PageRequest.of(page, limit);
        return hotelRepo.findHotelDetailByHotelSlug(slug, pageable).getContent();
    }

}
