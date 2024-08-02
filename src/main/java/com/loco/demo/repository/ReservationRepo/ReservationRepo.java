package com.loco.demo.repository.ReservationRepo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.loco.demo.AuthenModel.User;
import com.loco.demo.entity.Reservation;

@Repository
public interface ReservationRepo extends JpaRepository<Reservation, String> {
    @Query("SELECT r FROM Reservation r WHERE r.user=:user AND (:type IS NULL OR r.isPast=:type)")
    public Page<Reservation> findByUser(@Param("user") User user, Pageable pageable, @Param("type") Boolean type);

    @Query("SELECT r FROM Reservation r WHERE r.owner=:owner AND " +
            "(:status IS NULL OR r.status = :status) AND " +
            "(:isPast IS NULL OR r.isPast = :isPast)")
    public Page<Reservation> findByOwner(@Param("owner") User owner, Pageable pageable, @Param("status") Byte status,
            @Param("isPast") Boolean isPast);
}
