package com.loco.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Location {
    @Id
    @Column(name="id",length=50)
    private String id;
    @Column(name="x_coordinate")
    private Double xCoordinate;
    @Column(name="y_coordinate")
    private Double yCoordinate;
}
