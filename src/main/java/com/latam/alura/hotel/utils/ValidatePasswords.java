package com.latam.alura.hotel.utils;

import org.mindrot.jbcrypt.BCrypt;

public class ValidatePasswords {


    public static String encryptPassword(String password) {
        String gensalt = BCrypt.gensalt(10);
        return BCrypt.hashpw(password,gensalt);
    }

    public static boolean matchPassword(String password,String savePassword){
        return BCrypt.checkpw(password,savePassword);
    }
}
