package com.review.test.dtos;

import com.review.test.entities.Role;

import java.util.Objects;

public class RoleDto {

    private Long id;
    private String authority;

    public RoleDto() {
    }

    public RoleDto(Role entity) {
        id = entity.getId();
        authority = entity.getAuthority();
    }

    public Long getId() {
        return id;
    }

    public String getAuthority() {
        return authority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleDto roleDto = (RoleDto) o;
        return Objects.equals(authority, roleDto.authority);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(authority);
    }
}
