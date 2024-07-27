package com.loco.demo.Controller.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.loco.demo.DTO.JSON.ListResponse;
import com.loco.demo.DTO.JSON.UpdateUserForm;
import com.loco.demo.entity.WishList;
import com.loco.demo.services.userService.UserService;
import com.loco.demo.services.wishListSerivce.WishListService;
import com.loco.demo.utils.Converters.SecureUser;

import jakarta.validation.Valid;

@RequestMapping("/api/v1/client")
@RestController
@CrossOrigin("*")
public class UserController {
    private UserService userService;
    private WishListService wishListService;

    @Autowired
    public UserController(UserService userService, WishListService wishListService) {
        this.userService = userService;
        this.wishListService = wishListService;
    }

    @PutMapping("/{id}")
    public SecureUser updateUser(@PathVariable String id, @RequestBody @Valid UpdateUserForm updateUserForm) {
        return userService.updateUser(id, updateUserForm);
    }

    @PutMapping("/{id}/phone")
    public SecureUser updateUserPhone(@PathVariable String id, @RequestBody @Valid UpdateUserForm updateUserForm) {
        return userService.updateUserPhone(id, updateUserForm);
    }

    @PutMapping("/{id}/location")
    public SecureUser updateUserLocation(@PathVariable String id, @RequestBody @Valid UpdateUserForm updateUserForm) {
        return userService.updateUserLocation(id, updateUserForm);
    }

    @PutMapping("/{id}/email")
    public SecureUser updateUserEmail(@PathVariable String id, @RequestBody @Valid UpdateUserForm updateUserForm) {
        return userService.updateUserEmail(id, updateUserForm);
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

    @DeleteMapping("wishlist/{id}")
    public ResponseEntity<String> deleteWishList(@PathVariable String id){
        wishListService.deleteWishList(id);
        return ResponseEntity.ok().body("Xóa thành công");
    }
}
