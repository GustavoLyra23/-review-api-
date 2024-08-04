package com.review.test.dtos.user;

import com.review.test.services.validations.insert.UserInsertValid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@UserInsertValid
public class UserDto {

    @NotBlank(message = "username can't be blank")
    @Size(min = 1, max = 50, message = "username must have 1 or 50 characters max")
    private String username;
    @Size(min = 6, max = 20, message = "password must have 6 or 20 characters max")
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
