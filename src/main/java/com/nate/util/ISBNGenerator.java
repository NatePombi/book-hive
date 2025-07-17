package com.nate.util;

import java.security.SecureRandom;

public class ISBNGenerator {


    public static String generator(){
        String characters = "0123456789";

        SecureRandom secureRandom = new SecureRandom();

        StringBuilder builder = new StringBuilder(12);

        for(int i = 0; i<builder.capacity();i++){

            builder.append(characters.charAt(secureRandom.nextInt(characters.length())));
        }

        return  builder.toString();
    }
}
