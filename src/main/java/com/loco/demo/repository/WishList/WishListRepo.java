package com.loco.demo.repository.WishList;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.loco.demo.AuthenModel.User;
import com.loco.demo.entity.WishList;

@Repository
public interface WishListRepo extends JpaRepository<WishList,String>{
    public Optional<WishList> findByHotelId(String id);

    public boolean existsByHotelId(String id);

    public Page<WishList> findByUser(User user, Pageable pageable);
}
