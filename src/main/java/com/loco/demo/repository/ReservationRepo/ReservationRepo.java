package com.loco.demo.repository.ReservationRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.loco.demo.entity.Reservation;

@Repository
public interface ReservationRepo extends JpaRepository<Reservation,String>{
}
