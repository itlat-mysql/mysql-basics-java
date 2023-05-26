package com.itlat.mysql.basics.util;

public class NumberValidator {
    public static boolean canBeRepresentedAsInt(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
