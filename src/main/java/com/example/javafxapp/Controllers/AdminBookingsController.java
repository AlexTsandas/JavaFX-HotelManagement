package com.example.javafxapp.Controllers;

import com.example.javafxapp.Json_services.RoomService;
import com.example.javafxapp.Json_services.RoomService.Room;
import com.example.javafxapp.Navigation.Navigation;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AdminBookingsController implements Initializable {

    private RoomService roomService = new RoomService();
    private List<Room> rooms;

    @FXML private ListView<String> roomsList;
    @FXML private ComboBox<String> filterBox;

    @FXML private TextField roomIdField;
    @FXML private TextField usernameField;

    @FXML private Button backBtn;
    @FXML private Button roomsBtn;
    @FXML private Button bookingsBtn;

    // ---------------- NAVIGATION ----------------

    @FXML
    private void onBackClick() {
        Stage stage = (Stage) backBtn.getScene().getWindow();
        Navigation.loadPage(stage, "/com/example/javafxapp/LoginPage.fxml");
    }

    @FXML
    private void onRoomsClick() {
        Stage stage = (Stage) roomsBtn.getScene().getWindow();
        Navigation.loadPage(stage, "/com/example/javafxapp/AdminRooms.fxml");
    }

    @FXML
    private void onBookingsClick() {
        Stage stage = (Stage) bookingsBtn.getScene().getWindow();
        Navigation.loadPage(stage, "/com/example/javafxapp/AdminBookings.fxml");
    }

    // ---------------- INITIALIZATION ----------------
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        rooms = roomService.loadRooms();

        filterBox.getItems().addAll("All Rooms", "Available Rooms", "Booked Rooms");
        filterBox.getSelectionModel().select("All Rooms");

        loadRooms("All Rooms");

        filterBox.setOnAction(e -> loadRooms(filterBox.getValue()));
    }

    // ---------------- LOAD ROOMS BY FILTER ----------------
    private void loadRooms(String filter) {
        roomsList.getItems().clear();

        for (Room r : rooms) {
            if (filter.equals("Available Rooms") && !r.status.equalsIgnoreCase("available")) continue;
            if (filter.equals("Booked Rooms") && r.status.equalsIgnoreCase("available")) continue;

            roomsList.getItems().add(
                    r.id + " | " + r.type + " | " + r.price + "€ | Status: " + r.status
            );
        }
    }

    // ---------------- BOOK ROOM ----------------
    @FXML
    private void onBookClick() {
        String id = roomIdField.getText().trim();
        String user = usernameField.getText().trim();

        if (id.isEmpty() || user.isEmpty()) {
            showError("Please fill Room ID and Username.");
            return;
        }

        Room target = findRoom(id);
        if (target == null) {
            showError("Room not found.");
            return;
        }

        if (!target.status.equalsIgnoreCase("available")) {
            showError("Room is already booked.");
            return;
        }

        target.status = user;
        roomService.saveRooms(rooms);
        loadRooms(filterBox.getValue());
    }

    // ---------------- UNBOOK ROOM ----------------
    @FXML
    private void onUnbookClick() {
        String id = roomIdField.getText().trim();

        if (id.isEmpty()) {
            showError("Please enter Room ID.");
            return;
        }

        Room target = findRoom(id);
        if (target == null) {
            showError("Room not found.");
            return;
        }

        if (target.status.equalsIgnoreCase("available")) {
            showError("Room is not booked.");
            return;
        }

        target.status = "available";
        roomService.saveRooms(rooms);
        loadRooms(filterBox.getValue());
    }

    // ---------------- FIND ROOM BY ID ----------------
    private Room findRoom(String id) {
        for (Room r : rooms) {
            if (r.id.equalsIgnoreCase(id)) return r;
        }
        return null;
    }

    // ---------------- ERROR POPUP ----------------
    private void showError(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}

