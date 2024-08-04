package com.review.test.dtos.role;

import com.review.test.entities.Role;
import com.review.test.services.validations.role.RoleInsertValid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

import java.util.Objects;

public class RoleDtoRequest {

    @NotEmpty(message = "id cant be empy")
    @Positive(message = "id must be positive")
    @RoleInsertValid
    private Long id;

    public RoleDtoRequest() {
    }

    public RoleDtoRequest(Role entity) {
        id = entity.getId();
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleDtoRequest roleDtoRequest = (RoleDtoRequest) o;
        return Objects.equals(id, roleDtoRequest.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
