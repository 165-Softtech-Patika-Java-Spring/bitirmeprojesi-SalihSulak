package com.softtech.marketapi.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
public class UserSaveRequestDto implements Serializable {
    @Pattern(regexp = "^[A-Za-z ğüışç]*$", message = "Invalid Name")
    private String name;
    @Pattern(regexp = "^[A-Za-z ğüışç]*$", message = "Invalid Surname")
    private String surname;
    @Pattern(regexp = "^[a-z_.0-9]*$", message = "Invalid Username")
    private String username;
    @Size(min = 8, max = 16)
    private String password;
}
