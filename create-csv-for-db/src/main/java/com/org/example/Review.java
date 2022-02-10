package com.org.example;

public class Review {

    private final String id;
    private final int score;
    private final String review;
    private final String time;

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
