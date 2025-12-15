package com.example.javafxapp.Json_services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class RoomService {

    public static class Room {
        public String id;
        public String type;
        public double price;
        public String status;
    }

    private final String PATH = "Data/rooms.json";


    public List<Room> loadRooms() {
        try {
            File file = new File(PATH);
            if (!file.exists()) return new ArrayList<>();

            FileReader reader = new FileReader(file);
            Type roomListType = new TypeToken<List<Room>>(){}.getType();

            return new Gson().fromJson(reader, roomListType);

        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }



    public void saveRooms(List<Room> rooms) {
        try {
            FileWriter writer = new FileWriter(PATH);
            new Gson().toJson(rooms, writer);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

