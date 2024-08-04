package com.loco.demo.Controller.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.loco.demo.DTO.JSON.ExceptionResponseHandler;
import com.loco.demo.DTO.JSON.ListResponse;
import com.loco.demo.DTO.Status.StatusResponseAPI;
import com.loco.demo.entity.WishList;
import com.loco.demo.services.wishListSerivce.WishListService;

@RequestMapping("/api/v1/client")
@RestController
@CrossOrigin("*")
public class UserWishlistController {
    private WishListService wishListService;

    @Autowired
    public UserWishlistController(WishListService wishListService) {
        this.wishListService = wishListService;
    }
    
    @PostMapping("/wishlist/{id}")
    public WishList saveWishList(@PathVariable String id) {
        return wishListService.saveWishList(id);
    }

    @GetMapping("/wishlist")
    public ListResponse<WishList> getWishList(@RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int limit) {
        return wishListService.getWishList(page - 1, limit);
    }

    @DeleteMapping("/wishlist/{id}")
    public ResponseEntity<ExceptionResponseHandler> deleteWishList(@PathVariable String id) {
        wishListService.deleteWishList(id);
        return ResponseEntity.ok()
                .body(new ExceptionResponseHandler(StatusResponseAPI.OK, "000", "Successful deletion", ""));
    }
    
}
