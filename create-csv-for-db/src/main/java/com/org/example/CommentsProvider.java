package com.org.example;

import com.github.javafaker.Faker;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class CommentsProvider {

    CommentsProvider(List<User> users, List<Review> reviews) throws IOException {
        FileWriter out = new FileWriter("comments.csv");

        FileInputStream fileNameInput = new FileInputStream("comments_mocked.csv");
        InputStreamReader input = new InputStreamReader(fileNameInput);
        CSVParser csvParser = CSVFormat.EXCEL.withFirstRecordAsHeader().parse(input);
        var commentsIterator = csvParser.iterator();

        Faker faker = new Faker();

        try (CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT.withHeader("id_user", "content", "time", "id_review"))) {
            for (var user : users) {
                int nrCommented = ThreadLocalRandom.current().nextInt(0, 3);
                var commentedReviews = RandomUtil.pickNRandomUsers(reviews, nrCommented, ThreadLocalRandom.current());
                for(var commentedReview: commentedReviews){
                    String comment = commentsIterator.next().get("content");
                    Date reviewDate = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy").parse(commentedReview.getTime());
                    Date now = java.sql.Timestamp.valueOf(LocalDateTime.now());
                    String time = faker.date().between(reviewDate, now).toString();
                    printer.printRecord(user.getId(), comment, time, commentedReview.getId());
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
