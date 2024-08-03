package com.review.test.dtos;


import com.review.test.entities.User;

import java.time.Instant;

public class UserMinDto {

    private String id;
    private String name;
    private Instant instant;

    public UserMinDto() {
    }

    public UserMinDto(String id, String name) {
        this.id = id;
        this.name = name;
        this.instant = Instant.now();
    }

    public UserMinDto(User user) {
        id = String.valueOf(user.getId());
        name = user.getUsername();
        instant = Instant.now();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Instant getInstant() {
        return instant;
    }
}
