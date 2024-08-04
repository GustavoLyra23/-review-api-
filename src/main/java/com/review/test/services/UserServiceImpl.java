package com.review.test.services;

import com.review.test.dtos.*;
import com.review.test.entities.Role;
import com.review.test.entities.User;
import com.review.test.projections.UserNameProjection;
import com.review.test.repositories.RoleRepository;
import com.review.test.repositories.UserRepository;
import com.review.test.services.exceptions.ForbiddenException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
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
        userEntity.addRole(roleRepository.getReferenceById(2L));
        userEntity = userRepository.save(userEntity);
        return new UserMinDto(userEntity);
    }


    @Transactional(readOnly = true)
    public Page<UserNameDto> getAllUsers(Pageable pageable) {
        Page<UserNameProjection> projections = userRepository.findUsersProjection(pageable);
        return projections.map(UserNameDto::new);
    }

    @Transactional(readOnly = true)
    public UserMinDto findByName(String name, JwtAuthenticationToken token) {
        if (!token.getTokenAttributes().containsValue(name) && !token.getTokenAttributes().containsValue("ROLE_ADMIN")) {
            throw new ForbiddenException("Acess denied");
        }
        var user = userRepository.findByUsername(name);
        return new UserMinDto(user.orElseThrow(() -> new UsernameNotFoundException("User not found")));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public UserUpdateDto update(String name, UserUpdateDto userUpdateDto, JwtAuthenticationToken token) {
        if (!token.getTokenAttributes().containsValue(name) && !token.getTokenAttributes().containsValue("ROLE_ADMIN")) {
            throw new ForbiddenException("Acess denied");
        }
        var user = userRepository.findByUsername(name);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }

        var userEntity = user.get();
        entityToDtoUpdate(userEntity, userUpdateDto);
        userEntity = userRepository.save(userEntity);
        return new UserUpdateDto(userEntity);
    }


    public boolean isLoginCorrect(LoginRequest loginRequest, User user) {
        return encoder.matches(loginRequest.password(), user.getPassword());
    }


    private void entityToDtoUpdate(User user, UserUpdateDto dto) {
        try {
            user.setUsername(dto.getUsername());
            user.getRoles().clear();
            dto.getRoles().forEach(role -> {
                Role authority = roleRepository.getReferenceById(role.getId());
                user.addRole(authority);
            });
        } catch (EntityNotFoundException e) {
            throw new UsernameNotFoundException("Role not found");
        }
    }


}
