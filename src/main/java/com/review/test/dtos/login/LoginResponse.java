package com.review.test.dtos.login;

public record LoginResponse(String token, Long expiresIn) {
}
