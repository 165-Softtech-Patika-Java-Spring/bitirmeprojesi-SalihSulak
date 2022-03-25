package com.softtech.marketapi.dto.response;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.validation.constraints.Pattern;
import java.util.UUID;

@Getter
@Setter
public class UserUpdateResponseDto {

    @NonNull
    private UUID id;

    @Pattern(regexp = "^[A-Za-z ğüışç]*$", message = "Invalid Name")
    private String name;

    @Pattern(regexp = "^[A-Za-z ğüışç]*$", message = "Invalid Surname")
    private String surname;

    @Pattern(regexp = "^[a-z_.0-9]*$", message = "Invalid Username")
    private String username;
}
