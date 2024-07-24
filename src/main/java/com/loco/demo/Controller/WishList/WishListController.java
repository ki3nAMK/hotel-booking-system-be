package com.loco.demo.Controller.WishList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loco.demo.entity.WishList;
import com.loco.demo.services.wishListSerivce.WishListService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/wishlist")
public class WishListController {
    private WishListService wishListService;

    @Autowired
    public WishListController(WishListService wishListService) {
        this.wishListService = wishListService;
    }

    @PostMapping("/{id}")
    public WishList saveWishList(@PathVariable String id){
        return wishListService.saveWishList(id);
    }
}
