package com.damian.gemixqueapi.service.custom_classes;

public class UserWithWeight implements Comparable<UserWithWeight> {

    private double weight;
    private double averageScore;
    private String id;

    public UserWithWeight(double weight, double averageScore, String id) {
        this.weight = weight;
        this.averageScore = averageScore;
        this.id = id;
    }

    public UserWithWeight(double weight, String id) {
        this.weight = weight;
        this.id = id;
    }

    @Override
    public int compareTo(UserWithWeight o) {
        return (-1) * Double.compare(weight, o.weight);
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(double averageScore) {
        this.averageScore = averageScore;
    }

    @Override
    public String toString() {
        return "UserWithWeight{" +
                "weight=" + weight +
                ", id='" + id + '\'' +
                '}';
    }
}
