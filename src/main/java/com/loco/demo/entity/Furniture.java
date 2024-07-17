package com.loco.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Furniture {
    @Id
    @Column(name="id", length = 50)
    private String id;
    private Integer bed;
    private Integer bath;
    private Integer vehicle;
    private Integer car;
    private Integer pet;
}
