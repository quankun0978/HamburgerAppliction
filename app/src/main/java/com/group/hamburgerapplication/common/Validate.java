package com.group.hamburgerapplication.common;

import java.util.regex.Pattern;

public class Validate {
    private static String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\\\+]+(\\\\.[_A-Za-z0-9-]+)*@gmail.com$";
    private static final String PHONE_NUMBER_PATTERN =
            "^0\\d{9}$";
    public  static  boolean CheckNull(String a){
        return  a.isEmpty();
    }
    public static boolean checkConfirmPassword(String a,String b){
        return !a.equals(b);
    }
    public static boolean checkPassword(String a){
        return a.length()<8;
    }
    public static boolean checkEmail(String a){
        return  !Pattern.matches(EMAIL_PATTERN,a);
    }
    public static boolean checkPhone(String a){
        return  !Pattern.matches(PHONE_NUMBER_PATTERN,a);
    }
}
