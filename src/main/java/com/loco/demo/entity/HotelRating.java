package com.loco.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class HotelRating {
    @Id
    @Column(name="id", length = 50)
    private String id;
    @Column(name="amenities")
    private Byte amenities;
    @Column(name="communication")
    private Byte communication;
    @Column(name="value_for_money")
    private Byte valueForMoney;
    @Column(name="hygiene")
    private Byte hygiene;
    @Column(name="location_of_property")
    private Byte locationOfProperty;
}
