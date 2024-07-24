package com.loco.demo.services.wishListSerivce;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.loco.demo.AuthenModel.User;
import com.loco.demo.entity.Hotel;
import com.loco.demo.entity.WishList;
import com.loco.demo.repository.WishList.WishListRepo;
import com.loco.demo.services.authenService.AuthenticationService;
import com.loco.demo.services.hotelService.HotelService;

@Service
public class WishListServiceImpl implements WishListService {
    private WishListRepo wishListRepo;
    private AuthenticationService authenticationService;
    private HotelService hotelService;

    @Autowired
    public WishListServiceImpl(WishListRepo wishListRepo, AuthenticationService authenticationService,
            HotelService hotelService) {
        this.wishListRepo = wishListRepo;
        this.authenticationService = authenticationService;
        this.hotelService = hotelService;
    }

    @Override
    public WishList saveWishList(String id) {
        Hotel hotel = hotelService.getHotelById(id);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String usernameFromToken = authentication.getName();
        User getUserFromDB = authenticationService.getDataFromUserNameService(usernameFromToken);
        WishList wishList = new WishList(UUID.randomUUID().toString(), getUserFromDB, hotel,
                new Date(System.currentTimeMillis()));
        wishListRepo.save(wishList);
        return wishList;
    }
}
