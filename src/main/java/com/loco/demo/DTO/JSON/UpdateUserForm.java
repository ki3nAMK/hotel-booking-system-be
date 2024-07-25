package com.loco.demo.DTO.JSON;

import java.util.Date;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UpdateUserForm {
    private String name;
    @Past(message = "Birthday must be in the past")
    private Date birthday;
    private Byte gender;
    @Email(message = "Invalid email format")
    private String email;
    @Pattern(regexp = "^\\+?[0-9]*$", message = "Invalid phone number format")
    private String phone;
    private LocationForm locationForm;
}
