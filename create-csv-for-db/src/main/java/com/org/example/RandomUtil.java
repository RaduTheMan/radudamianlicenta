package com.org.example;

import java.util.List;
import java.util.Random;

public class RandomUtil {

    public static <T> List<T> pickNRandomUsers(List<T> users, int n, Random r) {
        int length = users.size();
        for(int i = length - 1; i>= length - n; i--){
            var a = users.get(i);
            int randomIndex = r.nextInt(i+1);
            var b = users.get(randomIndex);
            users.set(i, b);
            users.set(randomIndex, a);
        }
        return users.subList(length - n, length);
    }
}
