package com.example.keshav.util;

import java.time.Instant;
import java.util.*;
import java.util.logging.Logger;

public class UIDGenerator {

    private String type;

    private Integer lane;

    private Integer length = -1;

    private static final int LANE_NUMBER = 3;

    private static final int DEFAULT_LENGTH = 20;

    private static final long CUSTOM_EPOCH = 1420070400000L;

    private Random rnd = new Random();

    private UIDGenerator(String type, Integer lane) {
        if(type.isEmpty() || lane < 0 || lane > LANE_NUMBER ) {
            throw new IllegalArgumentException();
        }
        this.type = type;
        this.lane = lane;
    }

    private UIDGenerator(String type, Integer lane, Integer length) {

        if(type.isEmpty() || lane < 1 || lane > LANE_NUMBER || length < 1) {
            throw new IllegalArgumentException();
        }
        this.type = type;
        this.lane = lane;
        this.length = length;
    }


    public String nextUid() {
        String uid = null;
        if(length == -1) {
            uid = getRandomNumber(DEFAULT_LENGTH).toString();
        }
        else {
            uid = getRandomNumber(length).toString();
        }
        uid = lane + uid;
        uid = type.toUpperCase() + uid;
        return uid;
    }

    private StringBuilder getRandomNumber(int digCount) {

       long currentTimeStamp = timestamp();
       StringBuilder stringBuilderTime = new StringBuilder(Long.toString(currentTimeStamp));

       if(digCount < stringBuilderTime.length()) {
           stringBuilderTime = new StringBuilder(stringBuilderTime.substring(stringBuilderTime.length() - digCount).toString());
           stringBuilderTime = shuffle(stringBuilderTime);
       }
       else {
           stringBuilderTime = shuffle(stringBuilderTime);
           for(int i=0 ;  i < digCount - stringBuilderTime.length() ; i++) {
               stringBuilderTime.append((char)('0' + rnd.nextInt(10)));
           }
       }
       return stringBuilderTime;
    }

    private StringBuilder shuffle(StringBuilder input){
        List<Character> characters = new ArrayList<Character>();

        char[] charArray = new char[input.length()];
        input.getChars(0, input.length(), charArray, 0);

        for(char c:charArray){
            characters.add(c);
        }

        StringBuilder output = new StringBuilder(input.length());

        while(characters.size()!=0){
            int randPicker = (int)(Math.random()*characters.size());
            output.append(characters.remove(randPicker));
        }
        return output;
    }

    private static long timestamp() {
        return Instant.now().toEpochMilli() - CUSTOM_EPOCH;
        //Logger
    }

    public static void main(String [] args){

        UIDGenerator uidGenerator = new UIDGenerator("cart" , 2);
        UIDGenerator uidGenerator1 = new UIDGenerator("order" , 1 , 25);
        int i =0;
        while(i < 100){

            System.out.println(uidGenerator1.nextUid() + " " + (uidGenerator1.nextUid().length() - 6));
            i++;
        }
    }


}
