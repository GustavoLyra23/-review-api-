package com.review.test.repositories;

import com.review.test.entities.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;
import java.util.UUID;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;


    private UUID existingId;
    private UUID nonExistingId;
    private UUID dependentId;


    @BeforeEach
    void setUp() {
        existingId = UUID.fromString("085ffe33-0412-4c04-a14d-78a8f619eb26");
        nonExistingId = UUID.fromString("25d1b16e-6752-4a61-9f69-25d519097e95");
        dependentId = UUID.fromString("cbefd72f-7d52-4b23-9297-76e2291eaacc");
    }

//
//    @Test
//    public void deleteShouldDeleteUserWhenIdExists() {
//        userRepository.deleteById(existingId);
//        Optional<User> userOptional = userRepository.findById(existingId);
//        Assertions.assertFalse(userOptional.isPresent());
//    }


}
