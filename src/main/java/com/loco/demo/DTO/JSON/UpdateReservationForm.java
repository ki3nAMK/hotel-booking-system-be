package com.loco.demo.DTO.JSON;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class UpdateReservationForm {
    private Byte status;
    private String note;
}
