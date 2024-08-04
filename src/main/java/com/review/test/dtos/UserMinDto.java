package com.review.test.dtos;


import com.review.test.entities.Role;
import com.review.test.entities.User;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

public class UserMinDto {

    private String id;
    private String name;
    private Instant instant;
    private Set<RoleDto> roles = new HashSet<>();

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
        for (Role role : user.getRoles()) {
            roles.add(new RoleDto(role));
        }
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

    public Set<RoleDto> getRoles() {
        return roles;
    }
}
