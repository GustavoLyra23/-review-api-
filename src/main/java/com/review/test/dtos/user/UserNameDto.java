package com.review.test.dtos.user;

import com.review.test.projections.UserNameProjection;

public class UserNameDto {

    private String userName;

    public UserNameDto() {
    }

    public UserNameDto(UserNameProjection projection) {
        userName = projection.getUsername();
    }


    public String getUserName() {
        return userName;
    }

}
