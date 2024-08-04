package com.review.test.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginRequest(@NotBlank(message = "username can't be blank")
                           @Size(min = 1, max = 50)
                           String username,
                           @Size(min = 6, max = 20, message = "password must have 6 to 20 characters")
                           String password) {
}
