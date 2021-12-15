package io.bramcode.movie.moviecategoryservices.model;

import java.math.BigDecimal;


public class UserRatingComment {
    private String userName;
    private BigDecimal userRating;
    private String userComment;

    public UserRatingComment(){
    }

    public UserRatingComment(String userName, BigDecimal userRating, String userComment) {
        this.userName = userName;
        this.userRating = userRating;
        this.userComment = userComment;
    }

    public String getUserName() {
        return userName;
    }

    public UserRatingComment setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public BigDecimal getUserRating() {
        return userRating;
    }

    public UserRatingComment setUserRating(BigDecimal userRating) {
        this.userRating = userRating;
        return this;
    }

    public String getUserComment() {
        return userComment;
    }

    public UserRatingComment setUserComment(String userComment) {
        this.userComment = userComment;
        return this;
    }

    //   List<Rating> userRating;
//
//public UserRating(){}
//
//public List<Rating> getUserRating() {
//    return userRating;
//}
//
//public void setUserRating(List<Rating> userRating) {
//    this.userRating = userRating;
//}


}
