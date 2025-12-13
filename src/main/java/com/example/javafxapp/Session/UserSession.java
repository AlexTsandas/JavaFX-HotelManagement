package com.example.javafxapp.Session;

public class UserSession {

    private static String username;

    public static void login(String user) {
        username = user;
    }

    public static String getUsername() {
        return username;
    }
}


