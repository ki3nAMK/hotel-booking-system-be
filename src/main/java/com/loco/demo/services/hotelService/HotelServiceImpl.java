package com.loco.demo.services.hotelService;

import java.util.Date;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.loco.demo.AuthenModel.Role;
import com.loco.demo.AuthenModel.User;
import com.loco.demo.DTO.JSON.HotelDTO;
import com.loco.demo.DTO.JSON.HotelForm;
import com.loco.demo.DTO.JSON.ListResponse;
import com.loco.demo.entity.Furniture;
import com.loco.demo.entity.Hotel;
import com.loco.demo.entity.HotelDetail;
import com.loco.demo.entity.Location;
import com.loco.demo.repository.AuthenRepo.RoleAuthenRepo;
import com.loco.demo.repository.HotelRepo.HotelRepo;
import com.loco.demo.services.userService.UserService;

@Service
public class HotelServiceImpl implements HotelService {
    private final HotelRepo hotelRepo;
    private UserService userService;
    private RoleAuthenRepo roleAuthenRepo;

    @Autowired
    public HotelServiceImpl(HotelRepo hotelRepo, UserService userService, RoleAuthenRepo roleAuthenRepo) {
        this.hotelRepo = hotelRepo;
        this.userService = userService;
        this.roleAuthenRepo = roleAuthenRepo;
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

    @Override
    public ListResponse<Hotel> getMyListHotel(int page, int limit) {
        User myUser = userService.getMyInfo();
        Role roleSeller = roleAuthenRepo.findByAuthority("SELLER").get();
        if (myUser.getAuthorities().contains(roleSeller)) {
            Pageable pageable = PageRequest.of(page, limit);
            Page<Hotel> pageHotel = hotelRepo.findBySeller(myUser, pageable);
            return new ListResponse<Hotel>(
                    pageHotel.getContent(),
                    pageHotel.getTotalElements());
        }
        throw new AccessDeniedException("You must have role seller to access");
    }

    @Override
    public Hotel addHotel(HotelForm hotelForm) {
        User myUser = userService.getMyInfo();
        Role roleSeller = roleAuthenRepo.findByAuthority("SELLER").get();
        if (myUser.getAuthorities().contains(roleSeller)) {
            return formToHotel(hotelForm, myUser, new Hotel());
        }
        throw new AccessDeniedException("You must have role seller to access");
    }

    @Override
    public Hotel updateHotel(String id, HotelForm hotelForm) {
        User myUser = userService.getMyInfo();
        Role roleSeller = roleAuthenRepo.findByAuthority("SELLER").get();
        if (myUser.getAuthorities().contains(roleSeller)) {
            Hotel myhotel = hotelRepo.findById(id)
                    .orElseThrow(() -> new RuntimeException("NOT FOUND HOTEL IN DATABASE!!!"));
            if (myUser.getUserId().equals(myhotel.getSeller().getUserId())) {
                return formToHotel(hotelForm, myUser, myhotel);
            }
            throw new RuntimeException("You can't update hotel not your own");
        }
        throw new AccessDeniedException("You must have role seller to access");
    }

    private Hotel formToHotel(HotelForm hotelForm, User myUser, Hotel hotel) {
        if (hotel.getId() == null) {
            hotel.setId(UUID.randomUUID().toString());
            hotel.setCreateAt(new Date());
            hotel.setSeller(myUser);
        }
        hotel.setThumbnail(hotelForm.getThumbnail());
        hotel.setName(hotelForm.getName());
        hotel.setLocation(hotelForm.getLocation());
        hotel.setPrice(hotelForm.getPrice());
        hotel.setMinPrice(hotelForm.getMinPrice());
        hotel.setMaxPrice(hotelForm.getMaxPrice());
        hotel.setMinPeriod(hotelForm.getMinPeriod());
        hotel.setMaxPeriod(hotelForm.getMaxPeriod());
        hotel.setMediumPeriod(hotel.getMaxPeriod());
        Furniture myFurniture = hotel.getFurniture();
        Furniture furniture = hotelForm.getFurniture();
        if (myFurniture == null) {
            furniture.setId(UUID.randomUUID().toString());
        } else {
            furniture.setId(myFurniture.getId());
        }
        hotel.setFurniture(furniture);
        String imList = String.join("*", hotelForm.getImgList());
        hotel.setImgList(imList);
        String amenities = String.join("*", hotelForm.getAmenities());
        hotel.setAmenities(amenities);
        String safety = String.join("*", hotelForm.getSafety());
        hotel.setSafety(safety);
        HotelDetail myHotelDetail = hotel.getDetail();
        HotelDetail hotelDetail = hotelForm.getDetail();
        if (myHotelDetail == null) {
            hotelDetail.setId(UUID.randomUUID().toString());
        } else {
            hotelDetail.setId(myHotelDetail.getId());
        }
        hotel.setDetail(hotelDetail);
        hotel.setCapacity(hotelForm.getCapacity());
        hotel.setType(hotelForm.getType());
        hotel.setStatus(hotelForm.getStatus());
        hotel.setDescription(hotelForm.getDescription());
        Location myLocation = hotel.getLocationId();
        Location location = new Location(null, hotelForm.getCoordinate().get(0),
                hotelForm.getCoordinate().get(1));
        if (myLocation == null) {
            location.setId(UUID.randomUUID().toString());
        } else {
            location.setId(myLocation.getId());
        }
        hotel.setLocationId(location);
        String slug = hotelForm.getName().replace(" ", "-");
        slug = slug.toLowerCase();
        hotel.setSlug(slug);
        hotelRepo.save(hotel);
        return hotel;
    }

    @Override
    public void deleteHotel(String id) {
        User myUser = userService.getMyInfo();
        Role roleSeller = roleAuthenRepo.findByAuthority("SELLER").get();
        if (myUser.getAuthorities().contains(roleSeller)) {
            Hotel hotel = hotelRepo.findById(id).orElseThrow(()->new RuntimeException("NOT FOUND HOTEL IN DATABASE!!!"));
            if(myUser.getUserId().equals(hotel.getSeller().getUserId())){
                hotelRepo.deleteById(id);            
            }
            else{
                throw new RuntimeException("You can't delete hotel not your own");
            }
        } else
            throw new AccessDeniedException("You must have role SELLER to access");
    }
}
