package com.review.test.services;

import com.review.test.dtos.review.ReviewDtoRequest;
import com.review.test.dtos.review.ReviewDtoResponse;
import com.review.test.entities.Review;
import com.review.test.repositories.ReviewRepository;
import com.review.test.repositories.UserRepository;
import com.review.test.services.exceptions.DatabaseException;
import com.review.test.services.exceptions.ForbiddenException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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

    @Transactional(readOnly = true)
    public Page<ReviewDtoResponse> getAll(Pageable pageable) {
        return reviewRepository.findAll(pageable).map(ReviewDtoResponse::new);
    }

    @Transactional(readOnly = true)
    public List<ReviewDtoResponse> getReview(String user, JwtAuthenticationToken token) {
        if (!token.getTokenAttributes().containsValue(user) && !token.getTokenAttributes().containsValue("ROLE_ADMIN")) {
            throw new ForbiddenException("You are not authorized to view this review");
        }
        var userEntity = userRepository.findByUsername(user.trim()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        List<Review> reviews = reviewRepository.findReviewByUser(userEntity);
        return reviews.stream().map(ReviewDtoResponse::new).toList();
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void deleteById(Long id) {
        reviewRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("Review not found"));
        try {
            reviewRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Referential integrity failure");
        }
    }


    private void dtoToEntity(ReviewDtoRequest reviewDto, Review review) {
        review.setDescription(reviewDto.getDescription());
        review.setEstablishment(reviewDto.getEstablishment().trim());
        review.setRating(reviewDto.getRating());
    }

}
