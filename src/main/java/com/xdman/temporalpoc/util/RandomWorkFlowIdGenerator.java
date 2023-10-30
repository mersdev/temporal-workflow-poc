package com.xdman.temporalpoc.util;

import java.util.Random;

public class RandomWorkFlowIdGenerator {
    public static String generateRandomWorkFlowId(){
        Random random = new Random();
        String randomString = "HelloWorld";
        for (int i = 0; i < 5; i++) {
            randomString += random.nextInt();
        }
        return randomString;
    }
}
