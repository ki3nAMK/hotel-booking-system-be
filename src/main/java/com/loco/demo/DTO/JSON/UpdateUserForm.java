package com.loco.demo.DTO.JSON;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UpdateUserForm {
    private String name;
    private Date birthday;
    private Byte gender;
    private String email;
    private String phone;
    private LocationForm locationForm;
}
