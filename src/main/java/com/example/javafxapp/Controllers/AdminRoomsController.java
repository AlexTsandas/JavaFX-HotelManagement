package com.example.javafxapp.Controllers;

import com.example.javafxapp.Json_services.RoomService;
import com.example.javafxapp.Json_services.RoomService.Room;
import com.example.javafxapp.Navigation.Navigation;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AdminRoomsController implements Initializable {

    // SERVICE
    private RoomService roomService = new RoomService();
    private List<Room> rooms;

    // FXML
    @FXML private ListView<String> roomsList;

    @FXML private TextField deleteRoomField;

    @FXML private TextField roomIdField;
    @FXML private TextField roomTypeField;
    @FXML private TextField roomPriceField;
    @FXML private TextField roomStatusField;

    @FXML private Button backBtn;
    @FXML private Button roomsBtn;
    @FXML private Button bookingsBtn;


    // NAVIGATION BUTTONS
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


    // INITIALIZE
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        rooms = roomService.loadRooms();
        loadRoomsIntoListView();
    }


    // LOAD ROOMS TO LISTVIEW
    private void loadRoomsIntoListView() {
        roomsList.getItems().clear();
        for (Room r : rooms) {
            roomsList.getItems().add(
                    r.id + " | " + r.type + " | " + r.price + "€ | " + r.status
            );
        }
    }


    // DELETE ROOM
    @FXML
    private void onDeleteRoomClick() {
        String id = deleteRoomField.getText().trim();

        Room target = null;
        for (Room r : rooms) {
            if (r.id.equals(id)) {
                target = r;
                break;
            }
        }

        if (target == null) {
            showError("Room ID not found.");
            return;
        }

        rooms.remove(target);
        roomService.saveRooms(rooms);
        loadRoomsIntoListView();
    }


    // ADD ROOM
    @FXML
    private void onAddRoomClick() {

        if (roomIdField.getText().isEmpty() ||
                roomTypeField.getText().isEmpty() ||
                roomPriceField.getText().isEmpty() ||
                roomStatusField.getText().isEmpty()) {

            showError("Please fill all fields.");
            return;
        }

        Room newRoom = new Room();
        newRoom.id = roomIdField.getText();
        newRoom.type = roomTypeField.getText();
        newRoom.price = Double.parseDouble(roomPriceField.getText());
        newRoom.status = roomStatusField.getText();

        rooms.add(newRoom);
        roomService.saveRooms(rooms);
        loadRoomsIntoListView();
    }


    // ERROR POPUP
    private void showError(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}


