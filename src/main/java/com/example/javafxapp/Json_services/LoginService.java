package com.example.javafxapp.Json_services;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class LoginService {

    public static class User {
        public String username;
        public String password;
        public String role;
    }
    private final String PATH = "Data/users.json";
    public List<User> loadUsers() {
        try {
            File file = new File(PATH);
            if (!file.exists()) return new ArrayList<>();

            FileReader reader = new FileReader(file);
            Type roomListType = new TypeToken<List<User>>(){}.getType();

            Type userListType = new TypeToken<List<User>>() {}.getType();

            return new Gson().fromJson(reader, userListType);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}


