package com.review.test.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "tb_review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String establishment;
    private String description;
    private Integer rating;
    @CreationTimestamp
    private Instant instant;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    public Review() {
    }

    public Review(Long id, String establishment, String description, Integer rating, User user) {
        this.id = id;
        this.establishment = establishment;
        this.description = description;
        this.rating = rating;
        this.user = user;
    }

    public String getEstablishment() {
        return establishment;
    }

    public void setEstablishment(String establishment) {
        this.establishment = establishment;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return Objects.equals(id, review.id) && Objects.equals(establishment, review.establishment) && Objects.equals(description, review.description) && Objects.equals(rating, review.rating);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, establishment, description, rating);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
