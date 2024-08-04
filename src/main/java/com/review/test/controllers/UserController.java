package com.review.test.controllers;

import com.review.test.dtos.user.UserDto;
import com.review.test.dtos.user.UserMinDto;
import com.review.test.dtos.user.UserNameDto;
import com.review.test.dtos.user.UserUpdateDto;
import com.review.test.services.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @PostMapping
    public ResponseEntity<UserMinDto> save(@Valid @RequestBody UserDto user) {
        var userMinDto = userServiceImpl.crateUser(user);
        return ResponseEntity.ok(userMinDto);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('SCOPE_ROLE_ADMIN')")
    public ResponseEntity<Page<UserNameDto>> getUsers(Pageable pageable) {
        var users = userServiceImpl.getAllUsers(pageable);
        return ResponseEntity.ok(users);
    }

    @GetMapping(value = "/{name}")
    public ResponseEntity<UserMinDto> getUser(@PathVariable("name") String name, JwtAuthenticationToken token) {
        UserMinDto user = userServiceImpl.findByName(name, token);
        return ResponseEntity.ok(user);
    }

    @PutMapping(value = "/{name}")
    public ResponseEntity<UserUpdateDto> updateUser(@PathVariable("name") String name, @Valid @RequestBody UserUpdateDto userUpdateDto, JwtAuthenticationToken token) {
        UserUpdateDto updateDto = userServiceImpl.update(name, userUpdateDto, token);
        return ResponseEntity.ok(updateDto);
    }


}
