package com.loco.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelRating {
    @Id
    @Column(name="id", length = 50)
    private String id;
    private Byte amenities;
    private Byte communication;
    private Byte valueForMoney;
    private Byte hygiene;
    private Byte locationOfProperty;
}
