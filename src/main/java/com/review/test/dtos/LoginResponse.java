package com.review.test.dtos;

public record LoginResponse(String token, Long expiresIn) {
}
