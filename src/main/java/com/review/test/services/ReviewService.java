package com.review.test.services;

import com.review.test.dtos.review.ReviewDtoRequest;
import com.review.test.dtos.review.ReviewDtoResponse;
import com.review.test.entities.Review;
import com.review.test.repositories.ReviewRepository;
import com.review.test.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional(propagation = Propagation.REQUIRED)
    public ReviewDtoResponse create(ReviewDtoRequest reviewDtoRequest, JwtAuthenticationToken jwt) {
        try {
            var user = userRepository.getReferenceById(UUID.fromString(jwt.getName()));
            var review = new Review();

            dtoToEntity(reviewDtoRequest, review);
            review.setUser(user);
            review = reviewRepository.save(review);

            return new ReviewDtoResponse(review);

        } catch (EntityNotFoundException e) {
            throw new UsernameNotFoundException("User not found");
        }
    }


    private void dtoToEntity(ReviewDtoRequest reviewDto, Review review) {
        review.setDescription(reviewDto.getDescription());
        review.setEstablishment(reviewDto.getEstablishment());
        review.setRating(reviewDto.getRating());
    }


}
