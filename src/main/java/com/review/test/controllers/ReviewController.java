package com.review.test.controllers;

import com.review.test.dtos.ReviewDtoRequest;
import com.review.test.dtos.ReviewDtoResponse;
import com.review.test.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;


    @PostMapping
    @PreAuthorize("hasAnyAuthority('SCOPE_ROLE_ADMIN','SCOPE_ROLE_CLIENT')")
    public ResponseEntity<ReviewDtoResponse> addReview(@RequestBody ReviewDtoRequest reviewDtoRequest,
                                                       JwtAuthenticationToken jwt) {

        ReviewDtoResponse reviewDtoResponse = reviewService.create(reviewDtoRequest, jwt);
        return ResponseEntity.ok(reviewDtoResponse);
    }


}
