package com.org.example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CustomReleaseDate implements Comparable<CustomReleaseDate> {
    private final String platformName;
    private final String humanDate;

    public CustomReleaseDate(String platformName, String humanDate) {
        this.platformName = platformName;
        this.humanDate = humanDate;
    }

    @Override
    public String toString() {
        return "{" +
                "platformName='" + platformName + '\'' +
                ", humanDate='" + humanDate + '\'' +
                '}';
    }

    public String getHumanDate() {
        return humanDate;
    }

    public Date getDate() {
        Date date = null;
        try {
            date = new SimpleDateFormat("MMM dd, yyyy").parse(this.humanDate);
        } catch (ParseException e) {
            try {
                date = new SimpleDateFormat("MMM yyyy").parse(this.humanDate);
            } catch (ParseException ex) {
                Calendar c = Calendar.getInstance();
                c.setTime(new Date());
                c.add(Calendar.DATE, 3);
                date = c.getTime();
            }
        }
        return date;
    }


    @Override
    public int compareTo(CustomReleaseDate o) {
        return this.getDate().compareTo(o.getDate());
    }
}
