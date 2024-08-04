package com.review.test.dtos.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.review.test.dtos.role.RoleDtoRequest;
import com.review.test.entities.Role;
import com.review.test.entities.User;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class UserUpdateDto {

    private String id;
    @Size(min = 1, max = 50, message = "username must have 1 or 50 characters max")
    private String username;
    @Size(min = 6, max = 20, message = "password must have 6 or 20 characters max")
    private String password;
    @NotNull
    @NotEmpty(message = "cannot be empy")
    private Set<RoleDtoRequest> roles = new HashSet<>();

    public UserUpdateDto() {
    }

    public UserUpdateDto(User user) {
        id = String.valueOf(user.getId());
        username = user.getUsername();
        password = user.getPassword();
        for (Role role : user.getRoles()) {
            roles.add(new RoleDtoRequest(role));
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

    public Set<RoleDtoRequest> getRoles() {
        return roles;
    }

    public boolean hasRole(Long roleId) {
        return roles.stream().anyMatch(role -> Objects.equals(role.getId(), roleId));
    }
}
