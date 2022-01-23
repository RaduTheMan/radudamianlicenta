package com.org.example;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        try {
            FileInputStream fileNameReviews = new FileInputStream("reviews.csv");
            InputStreamReader input = new InputStreamReader(fileNameReviews);
            CSVParser csvParser = CSVFormat.EXCEL.withFirstRecordAsHeader().parse(input);
            FileWriter out = new FileWriter("cleaned_reviews.csv");
            for (CSVRecord record : csvParser) {
                String content = record.get("content");
                if (content.startsWith("\"")){
                    content = content.substring(1);
                }
                if(content.endsWith("\"")){
                    content = content.substring(0, content.length() - 1);
                }
                System.out.println(content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
