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
public class Furniture {
    @Id
    @Column(name="id", length = 50)
    private String id;
    @Column(name="bed")
    private Integer bed;
    @Column(name="bath")
    private Integer bath;
    @Column(name="vehicle")
    private Integer vehicle;
    @Column(name="car")
    private Integer car;
    @Column(name="pet")
    private Integer pet;
}
