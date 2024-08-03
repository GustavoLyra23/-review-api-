package com.review.test.repositories;

import com.review.test.entities.User;
import com.review.test.projections.UserNameProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByUsername(String username);

    @Query("SELECT u.username AS username FROM User u")
    Page<UserNameProjection> findUsersProjection(Pageable pageable);

}
