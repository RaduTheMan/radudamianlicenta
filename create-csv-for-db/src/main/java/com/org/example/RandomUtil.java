package com.org.example;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RandomUtil {

    public static List<User> pickNRandomUsers(List<User> users, int n, Random r) {
        int length = users.size();
        for(int i = length - 1; i>= length - n; --i){
            Collections.swap(users, i, r.nextInt(i+1));
        }
        return users.subList(length - n, length);
    }
}
