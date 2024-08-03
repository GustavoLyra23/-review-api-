package com.review.test.controllers;

import com.review.test.dtos.UserDto;
import com.review.test.dtos.UserMinDto;
import com.review.test.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @PreAuthorize("hasRole('ROLE_CLIENT')")
    @GetMapping
    public String index() {
        return "Hello World";
    }


    @PostMapping
    public ResponseEntity<UserMinDto> save(@RequestBody UserDto user) {
        var userMinDto = userServiceImpl.crateUser(user);
        return ResponseEntity.ok(userMinDto);
    }


}
