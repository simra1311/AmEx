package com.example.android.amex;

/**
 * Created by Simra Afreen on 17-03-2018.
 */

public class UserInformation {

    private static String name;
    private static String email;
    private static String password;

    public static void setName(String name) {
        UserInformation.name = name;
    }

    public static void setEmail(String email) {
        UserInformation.email = email;
    }

    public static void setPassword(String password) {
        UserInformation.password = password;
    }

    public static String getName() {
        return name;
    }

    public static String getEmail() {
        return email;
    }

    public static String getPassword() {
        return password;
    }
}
