package com.example.shijianshenapp.utils;

public class DoubleToString {
    public static String doubleParseString(double d) {
        int i = (int) d;
        String result;
        if (d > i) {
            result = String.valueOf(d);
        } else {
            result = String.valueOf(i);
        }
        return result;
    }
}
