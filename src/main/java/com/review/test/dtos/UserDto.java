package com.review.test.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserDto {

    @NotBlank(message = "username can't be blank")
    private String username;
    @Size(min = 6, max = 20, message = "password must have 6 to 20 characters")
    private String password;

    public UserDto() {
    }

    public UserDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
