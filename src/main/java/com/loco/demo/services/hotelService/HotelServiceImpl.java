package com.loco.demo.services.hotelService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.loco.demo.DTO.JSON.HotelResponse;
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
    public HotelResponse getListByCriteria(int page, int limit, Integer type, String name, Integer minPrice,
            Integer maxPrice, Integer bed,
            Integer bathroom, Integer car, Integer pet, String amenity, String safety) {
        Pageable pageable = PageRequest.of(page, limit);
        Page<Hotel> pageHotel=hotelRepo.findHotelsByCriteria(type, name, minPrice, maxPrice, bed,
                bathroom, car, pet, amenity, safety, pageable);
        return new HotelResponse(pageHotel.getContent(), pageHotel.getTotalElements());
    }

    @Override
    public HotelDetail getDetailByHotelSlug(String slug) {
        HotelDetail hotelDetail=hotelRepo.findHotelDetailByHotelSlug(slug);
        return hotelDetail;
    }

}
