package com.loco.demo.repository.FurnitureRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.loco.demo.entity.Furniture;

@Repository
public interface FurnitureRepo extends JpaRepository<Furniture,String>{
}
