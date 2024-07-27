package com.loco.demo.services.wishListSerivce;

import com.loco.demo.DTO.JSON.ListResponse;
import com.loco.demo.entity.WishList;

public interface WishListService {
    public WishList saveWishList(String id);

    public ListResponse<WishList> getWishList(int page, int limit);

    public void deleteWishList(String id);
}
