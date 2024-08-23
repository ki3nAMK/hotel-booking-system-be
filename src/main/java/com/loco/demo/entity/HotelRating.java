package com.loco.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "hotel_rating")
public class HotelRating {
    @Id
    @Column(name = "id", length = 50)
    private String id;
    @Column(name = "amenities")
    @Min(value = 0, message = "Rating is not valid")
    @Max(value = 5, message = "Rating is not valid")
    private Byte amenities;
    @Column(name = "communication")
    @Min(value = 0, message = "Rating is not valid")
    @Max(value = 5, message = "Rating is not valid")
    private Byte communication;
    @Column(name = "value_for_money")
    @Min(value = 0, message = "Rating is not valid")
    @Max(value = 5, message = "Rating is not valid")
    private Byte valueForMoney;
    @Column(name = "hygiene")
    @Min(value = 0, message = "Rating is not valid")
    @Max(value = 5, message = "Rating is not valid")
    private Byte hygiene;
    @Column(name = "location_of_property")
    @Min(value = 0, message = "Rating is not valid")
    @Max(value = 5, message = "Rating is not valid")
    private Byte locationOfProperty;
}
