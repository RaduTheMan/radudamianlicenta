package com.damian.gemixqueapi.service.custom_classes;

public class Fraction implements Comparable<Fraction> {
    private double numerator;
    private double denominator;
    private final double baseValue;
    private double fractionValue;

    public Fraction(double baseValue, double numerator, double denominator) {
        this.baseValue = baseValue;
        this.numerator = numerator;
        this.denominator = denominator;
        this.fractionValue = numerator / denominator;
    }

    @Override
    public String toString() {
        return "Fraction{" +
                "value=" + this.getValue() +
                '}';
    }

    public double getNumerator() {
        return numerator;
    }

    public void setNumerator(double numerator) {
        this.numerator = numerator;
        this.fractionValue = numerator / denominator;
    }

    public double getDenominator() { return denominator; }

    public void setDenominator(double denominator) {
        this.denominator = denominator;
        this.fractionValue = numerator / denominator;
    }

    public double getValue() {
        return this.baseValue + this.fractionValue;
    }

    @Override
    public int compareTo(Fraction o) {
        return Double.compare(this.getValue(), o.getValue());
    }
}
