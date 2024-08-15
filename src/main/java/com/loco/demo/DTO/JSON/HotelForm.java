package com.loco.demo.DTO.JSON;

import java.util.List;

import com.loco.demo.entity.Furniture;
import com.loco.demo.entity.HotelDetail;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HotelForm {
    private String name;
    private Integer capacity;
    private Integer type;
    private Integer price;
    private Integer minPrice;
    private Integer maxPrice;
    private Byte minPeriod;
    private Byte mediumPeriod;
    private Byte maxPeriod;
    private String description;
    private List<String> amenities;
    private List<String> safety;
    private Byte status;
    private String location;
    private List<Double> coordinate;
    private Furniture furniture;
    private String thumbnail;
    private List<String> imgList;
    private HotelDetail detail;
}
