package com.review.test.controllers;

import com.review.test.dtos.review.ReviewDtoRequest;
import com.review.test.dtos.review.ReviewDtoResponse;
import com.review.test.services.ReviewService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;


    @PostMapping
    public ResponseEntity<ReviewDtoResponse> addReview(@Valid @RequestBody ReviewDtoRequest reviewDtoRequest, JwtAuthenticationToken jwt) {

        ReviewDtoResponse reviewDtoResponse = reviewService.create(reviewDtoRequest, jwt);
        return ResponseEntity.ok(reviewDtoResponse);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('SCOPE_ROLE_ADMIN')")
    public ResponseEntity<Page<ReviewDtoResponse>> getAllReviews(Pageable pageable) {
        var reviews = reviewService.getAll(pageable);
        return ResponseEntity.ok(reviews);
    }

    @GetMapping(value = "/{user}")
    public ResponseEntity<List<ReviewDtoResponse>> getReviewFromUser(@PathVariable String user, JwtAuthenticationToken token) {
        var reviewDtp = reviewService.getReview(user, token);
        return ResponseEntity.ok(reviewDtp);
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ROLE_ADMIN')")
    public ResponseEntity<Void> deleteReview(@PathVariable("id") Long id) {
        reviewService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
