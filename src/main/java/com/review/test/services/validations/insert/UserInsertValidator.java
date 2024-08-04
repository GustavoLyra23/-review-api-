package com.review.test.services.validations.insert;

import com.review.test.dtos.FieldError;
import com.review.test.dtos.UserDto;
import com.review.test.repositories.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class UserInsertValidator implements ConstraintValidator<UserInsertValid, UserDto> {

    @Autowired
    private UserRepository userRepository;


    @Override
    public void initialize(UserInsertValid ann) {
    }

    @Override
    public boolean isValid(UserDto userDto, ConstraintValidatorContext context) {
        List<FieldError> fieldErrors = new ArrayList<>();


        var user = userRepository.findByUsername(userDto.getUsername());
        if (user.isPresent()) {
            fieldErrors.add(new FieldError("name", "already exists"));
        }

        fieldErrors.forEach(fieldError -> {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(fieldError.getMessage()).addPropertyNode(fieldError.getField())
                    .addConstraintViolation();
        });
        return fieldErrors.isEmpty();
    }
}

