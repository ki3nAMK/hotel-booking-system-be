package com.loco.demo.repository.WishList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.loco.demo.entity.WishList;

@Repository
public interface WishListRepo extends JpaRepository<WishList,String>{
}
