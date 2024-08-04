package com.review.test.services.validations.role;

import com.review.test.dtos.erro.FieldError;
import com.review.test.dtos.role.RoleDtoRequest;
import com.review.test.repositories.RoleRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class RoleInsertValidator implements ConstraintValidator<RoleInsertValid, RoleDtoRequest> {

    @Autowired
    private RoleRepository roleRepository;


    @Override
    public void initialize(RoleInsertValid ann) {
    }

    @Override
    public boolean isValid(RoleDtoRequest roleDtoRequest, ConstraintValidatorContext context) {
        List<FieldError> fieldErrors = new ArrayList<>();

        var role = roleRepository.findById(roleDtoRequest.getId());
        if (role.isEmpty()) {
            fieldErrors.add(new FieldError("role", "invalid role"));
        }
        fieldErrors.forEach(fieldError -> {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(fieldError.getMessage()).addPropertyNode(fieldError.getField())
                    .addConstraintViolation();
        });
        return fieldErrors.isEmpty();
    }
}

