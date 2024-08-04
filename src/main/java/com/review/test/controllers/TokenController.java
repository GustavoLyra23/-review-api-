package com.review.test.controllers;

import com.review.test.dtos.LoginRequest;
import com.review.test.dtos.LoginResponse;
import com.review.test.services.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/tokens")
public class TokenController {

    @Autowired
    private TokenService service;


    @PostMapping("/login")
    public ResponseEntity<LoginResponse> postToken(@Valid @RequestBody LoginRequest loginRequest) {
        LoginResponse response = service.tokenCreation(loginRequest);
        return ResponseEntity.ok(response);
    }


}
