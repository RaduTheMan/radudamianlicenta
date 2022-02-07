package com.org.example;

import java.io.IOException;

public class Main {

    public static void main(String[] args){
        var generator = new UsersGenerator();
        var users = generator.generate();
        UsersProvider usersProvider = new UsersProvider(users);
        try {
            var followsProvider = new FollowsProvider(users);
            var messagesProvider = new MessagesProvider(users);
        } catch (IOException e) {
            e.printStackTrace();
        }

       GameProvider gameProvider = new GameProvider("/igdb-api-config.json");
        try {
            PlaysProvider playsProvider = new PlaysProvider(users, gameProvider.getGamePrototypes());
            ReviewProvider reviewProvider = new ReviewProvider(users);
            LikesProvider likesProvider = new LikesProvider(users, reviewProvider.getAllReviews());
            CommentsProvider commentsProvider = new CommentsProvider(users, reviewProvider.getAllReviews());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}


