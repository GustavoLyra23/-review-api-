package com.review.test.dtos;

public class ReviewDtoRequest {

    private String establishment;
    private Integer rating;
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
