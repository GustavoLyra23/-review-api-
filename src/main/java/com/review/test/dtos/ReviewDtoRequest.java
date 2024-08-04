package com.review.test.dtos;

import jakarta.validation.constraints.*;

public class ReviewDtoRequest {

    @NotBlank
    @Size(min = 1, max = 50)
    private String establishment;

    @Positive(message = "The number must be positive")
    @Min(value = 1, message = "the minium value must be 1")
    @Max(value = 10, message = "the max value must be 10")
    private Integer rating;

    @NotBlank
    @Size(min = 1, max = 150)
    private String description;

    public ReviewDtoRequest() {
    }

    public ReviewDtoRequest(String establishment, Integer rating, String description) {
        this.establishment = establishment;
        this.rating = rating;
        this.description = description;
    }

    public String getEstablishment() {
        return establishment;
    }

    public void setEstablishment(String establishment) {
        this.establishment = establishment;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
