package com.org.example;

import java.io.IOException;

public class Main {

    public static void main(String[] args){
        var generator = new UsersGenerator();
        var users = generator.generate();
        UsersProvider usersProvider = new UsersProvider(users);
        try {
            usersProvider.generateUsersFiles();
            FollowsProvider.generateFollowsFile(users);
            var messagesProvider = new MessagesProvider(users);
            messagesProvider.generateMessagesFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

       GameProvider gameProvider = new GameProvider("/igdb-api-config.json");
        try {
            PlaysProvider.generatePlaysFile(users, gameProvider.getGamePrototypes());
            ReviewProvider reviewProvider = new ReviewProvider(users);
            LikesProvider.generateLikesFile(users, reviewProvider.getAllReviews());
            CommentsProvider.generateCommentsFile(users, reviewProvider.getAllReviews());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}


