package com.review.test.services;

import com.review.test.dtos.LoginRequest;
import com.review.test.dtos.UserDto;
import com.review.test.dtos.UserMinDto;
import com.review.test.dtos.UserNameDto;
import com.review.test.entities.User;
import com.review.test.projections.UserNameProjection;
import com.review.test.repositories.RoleRepository;
import com.review.test.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
public class UserServiceImpl {

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;


    @Transactional(propagation = Propagation.REQUIRED)
    public UserMinDto crateUser(UserDto user) {
        User userEntity = new User();
        userEntity.setUsername(user.getUsername());
        userEntity.setPassword(encoder.encode(user.getPassword()));
        userEntity.addRole(roleRepository.getReferenceById(2L));
        userEntity = userRepository.save(userEntity);
        return new UserMinDto(userEntity);
    }

    public boolean isLoginCorrect(LoginRequest loginRequest, User user) {
        return encoder.matches(loginRequest.password(), user.getPassword());
    }

    @Transactional(readOnly = true)
    public Page<UserNameDto> getAllUsers(Pageable pageable) {
        Page<UserNameProjection> projections = userRepository.findUsersProjection(pageable);
        return projections.map(UserNameDto::new);
    }

    public UserMinDto findByName(String name, JwtAuthenticationToken token) {
        if (!token.getTokenAttributes().containsValue(name) && !token.getTokenAttributes().containsValue("ROLE_ADMIN")) {
            throw new RuntimeException("Invalid user");
        }
        var user = userRepository.findByUsername(name);
        return new UserMinDto(user.orElseThrow(() -> new NoSuchElementException("User not found")));
    }
}
