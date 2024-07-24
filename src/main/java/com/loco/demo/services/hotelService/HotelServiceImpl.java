package com.loco.demo.services.hotelService;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.loco.demo.DTO.JSON.HotelDTO;
import com.loco.demo.DTO.JSON.ListResponse;
import com.loco.demo.entity.Hotel;
import com.loco.demo.repository.HotelRepo.HotelRepo;

@Service
public class HotelServiceImpl implements HotelService {
    private final HotelRepo hotelRepo;

    @Autowired
    public HotelServiceImpl(HotelRepo hotelRepo) {
        this.hotelRepo = hotelRepo;
    }

    @Override
    public ListResponse<HotelDTO> getListByCriteria(int page, int limit, Integer type, String name, Integer minPrice,
            Integer maxPrice, Integer bed,
            Integer bathroom, Integer car, Integer pet, String amenity, String safety) {
        Pageable pageable = PageRequest.of(page, limit);
        Page<Hotel> pageHotel = hotelRepo.findHotelsByCriteria(type, name, minPrice, maxPrice, bed,
                bathroom, car, pet, amenity, safety, pageable);
        return new ListResponse<HotelDTO>(
                pageHotel.getContent().stream().map((hotel) -> new HotelDTO(hotel)).collect(Collectors.toList()),
                pageHotel.getTotalElements());
    }

    @Override
    public Hotel getDetailByHotelSlug(String slug) {
        Hotel hotel = hotelRepo.findHotelDetailByHotelSlug(slug)
                .orElseThrow(() -> new UsernameNotFoundException("NO HOTEL FOUND IN DATABASE !!!"));
        return hotel;
    }

    @Override
    public Hotel getHotelById(String id) {
        return hotelRepo.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("NO HOTEL FOUND IN DATABASE !!!"));
    }
}
