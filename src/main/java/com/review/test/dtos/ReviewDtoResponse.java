package com.review.test.dtos;

import com.review.test.entities.Review;

import java.time.Instant;

public class ReviewDtoResponse {

    private Long id;
    private String establishment;
    private String author;
    private Integer rating;
    private Instant reviewDate;
    private String description;

    public ReviewDtoResponse() {
    }

    public ReviewDtoResponse(Long id, String establishment, String author, Integer rating, Instant reviewDate, String description) {
        this.id = id;
        this.establishment = establishment;
        this.author = author;
        this.rating = rating;
        this.reviewDate = reviewDate;
        this.description = description;
    }

    public ReviewDtoResponse(Review review) {
        id = review.getId();
        establishment = review.getEstablishment();
        author = review.getUser().getUsername();
        rating = review.getRating();
        reviewDate = review.getInstant();
        description = review.getDescription();
    }

    public String getEstablishment() {
        return establishment;
    }

    public void setEstablishment(String establishment) {
        this.establishment = establishment;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Instant getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(Instant reviewDate) {
        this.reviewDate = reviewDate;
    }

    public String getDescription() {
        return description;
    }

}
