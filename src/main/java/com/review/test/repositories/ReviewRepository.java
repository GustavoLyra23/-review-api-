package com.review.test.repositories;

import com.review.test.entities.Review;
import com.review.test.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {


     List<Review> findReviewByUser(User user);




}
