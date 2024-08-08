package com.loco.demo.services.wishListSerivce;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.loco.demo.AuthenModel.User;
import com.loco.demo.DTO.JSON.ListResponse;
import com.loco.demo.entity.Hotel;
import com.loco.demo.entity.WishList;
import com.loco.demo.repository.WishList.WishListRepo;
import com.loco.demo.services.authenService.AuthenticationService;
import com.loco.demo.services.hotelService.HotelService;
import com.loco.demo.services.notificationService.NotificationService;
import com.loco.demo.services.userService.UserService;

@Service
public class WishListServiceImpl implements WishListService {
    private WishListRepo wishListRepo;
    private AuthenticationService authenticationService;
    private HotelService hotelService;
    private UserService userService;
    private NotificationService notificationService;

    @Autowired
    public WishListServiceImpl(WishListRepo wishListRepo, AuthenticationService authenticationService,
            HotelService hotelService, UserService userService, NotificationService notificationService) {
        this.wishListRepo = wishListRepo;
        this.authenticationService = authenticationService;
        this.hotelService = hotelService;
        this.userService = userService;
        this.notificationService = notificationService;
    }

    @Override
    public WishList saveWishList(String id) {
        Hotel hotel = hotelService.getHotelById(id);
        System.out.println(wishListRepo.existsByHotelId(id));
        if (!wishListRepo.existsByHotelId(id)) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String usernameFromToken = authentication.getName();
            User getUserFromDB = authenticationService.getDataFromUserNameService(usernameFromToken);
            WishList wishList = new WishList(UUID.randomUUID().toString(), getUserFromDB, hotel,
                    new Date(System.currentTimeMillis()));
            wishListRepo.save(wishList);
            notificationService.makeNotification(getUserFromDB,
                    "Add " + hotel.getName() + " to wishlisst succesfully",
                    new Date());
            return wishList;
        } else
            throw new RuntimeException("This hotel already has wishlist");
    }

    @Override
    public ListResponse<WishList> getWishList(int page, int limit) {
        Pageable pageable = PageRequest.of(page, limit);
        Page<WishList> pageWishList = wishListRepo.findAll(pageable);
        return new ListResponse<WishList>(pageWishList.getContent(), pageWishList.getTotalElements());
    }

    @Override
    public void deleteWishList(String id) {
        WishList wishList = wishListRepo.findByHotelId(id)
                .orElseThrow(() -> new UsernameNotFoundException("This hotel doesn't have any wishlist"));
        if (userService.checkIdAndRole(wishList.getUser().getUserId())) {
            wishListRepo.deleteById(wishList.getId());
        } else
            throw new RuntimeException("Not your own wishlist!!");
    }
}
