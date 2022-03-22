package com.softtech.marketapi.dto.response;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Pattern;

@Setter
@Getter
public class UserSaveResponseDto {
    @Pattern(regexp = "^[A-Za-z ğüışç]*$", message = "Invalid Name")
    private String name;
    @Pattern(regexp = "^[A-Za-z ğüışç]*$", message = "Invalid Surname")
    private String surname;
    @Pattern(regexp = "^[a-z_.0-9]*$", message = "Invalid Username")
    private String username;
}
