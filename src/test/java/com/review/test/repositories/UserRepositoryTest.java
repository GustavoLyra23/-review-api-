package com.review.test.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;


    private long existingId;
    private long nonExistingId;
    private long dependentId;


    @BeforeEach
    void setUp() {
        existingId = 1L;
        nonExistingId = 0L;
        dependentId = 2L;
    }
}
