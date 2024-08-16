package com.loco.demo.DTO.JSON;

import com.loco.demo.entity.HotelRating;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentForm {
    @Valid
    private HotelRating rating;
    private String content;
}
