package com.review.test.services;


import com.review.test.dtos.LoginRequest;
import com.review.test.dtos.LoginResponse;
import com.review.test.entities.Role;
import com.review.test.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.stream.Collectors;

@Service
public class TokenService {

    @Autowired
    private JwtEncoder jwtEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserServiceImpl userService;

    @Transactional
    public LoginResponse verifyLogIn(LoginRequest loginRequest) {
        var entity = userRepository.findByUsername(loginRequest.username());

        if (entity.isEmpty() || !userService.isLoginCorrect(loginRequest, entity.get())) {
            throw new BadCredentialsException("user or password is invalid");
        }
        var now = Instant.now();
        var expiresIn = 300L;
        var scopes = entity.get().getRoles().stream().map(Role::getAuthority).collect(Collectors.joining(" "));

        var claims = JwtClaimsSet.builder()
                .issuer("mybackend")
                .claim("name", entity.get().getUsername())
                .claim("scope", scopes)
                .subject(entity.get().getId().toString())
                .expiresAt(now.plusSeconds(expiresIn))
                .issuedAt(now)
                .build();

        var jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        return new LoginResponse(jwtValue, expiresIn);
    }
}
