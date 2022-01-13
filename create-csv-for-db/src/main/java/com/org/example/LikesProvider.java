package com.org.example;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class LikesProvider {

    LikesProvider(List<User> users, List<Review> reviews) throws IOException {
        FileWriter out = new FileWriter("likes.csv");
        try (CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT.withHeader("id_user","id_reviews"))) {
            for (var user : users) {
                int nrLiked = ThreadLocalRandom.current().nextInt(0, 3);
                var likedReviews = RandomUtil.pickNRandomUsers(reviews, nrLiked, ThreadLocalRandom.current());
                if(likedReviews.size() > 0)
                    printer.printRecord(user.getId(), likedReviews);
            }
        }
    }
}
