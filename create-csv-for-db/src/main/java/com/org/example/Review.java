package com.org.example;

public class Review {

    private String id;
    private int score;
    private String review;
    private String time;

    public Review(String id, int score, String review, String time) {
        this.id = id;
        this.score = score;
        this.review = review;
        this.time = time;
    }

    @Override
    public String toString() {
        return id;
    }

    public String getId() {
        return id;
    }

    public int getScore() {
        return score;
    }

    public String getReview() {
        return review;
    }

    public String getTime() {
        return time;
    }



}
