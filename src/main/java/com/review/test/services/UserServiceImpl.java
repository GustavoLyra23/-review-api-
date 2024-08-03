package com.review.test.services;

import com.review.test.dtos.LoginRequest;
import com.review.test.dtos.UserDto;
import com.review.test.dtos.UserMinDto;
import com.review.test.entities.User;
import com.review.test.repositories.RoleRepository;
import com.review.test.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
        userEntity.addRole(roleRepository.getReferenceById(1L));
        userEntity = userRepository.save(userEntity);
        return new UserMinDto(userEntity);
    }

    public boolean isLoginCorrect(LoginRequest loginRequest, User user) {
        return encoder.matches(loginRequest.password(), user.getPassword());
    }


}
