package com.review.test.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.review.test.entities.Role;
import com.review.test.entities.User;

import java.util.HashSet;
import java.util.Set;

public class UserUpdateDto {

    private String id;
    private String username;
    private String password;
    private Set<RoleDto> roles = new HashSet<>();

    public UserUpdateDto() {
    }

    public UserUpdateDto(User user) {
        id = String.valueOf(user.getId());
        username = user.getUsername();
        password = user.getPassword();
        for (Role role : user.getRoles()) {
            roles.add(new RoleDto(role));
        }
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    public Set<RoleDto> getRoles() {
        return roles;
    }
}
