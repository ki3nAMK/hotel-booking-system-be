package com.loco.demo.DTO.JSON;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LocationForm {
    private String location;
    private double xCoordinate;
    private double yCoordinate;
}
