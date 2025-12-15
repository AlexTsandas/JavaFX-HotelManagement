package com.example.javafxapp.Controllers;

import com.example.javafxapp.Json_services.RoomService;
import com.example.javafxapp.Json_services.RoomService.Room;
import com.example.javafxapp.Navigation.Navigation;
import com.example.javafxapp.Session.UserSession;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class UsersBookingController implements Initializable {


    private final RoomService roomService = new RoomService();
    private List<Room> rooms;


    private final String username = UserSession.getUsername();


    @FXML
    private ListView<String> myBookingsList;
    @FXML
    private TextField roomIdField;
    @FXML
    private Label errorLabel;

    @FXML
    private Button backBtn;
    @FXML
    private Button bookRoomBtn;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        rooms = roomService.loadRooms();
        loadMyBookings();
    }


    private void loadMyBookings() {
        myBookingsList.getItems().clear();

        for (Room r : rooms) {
            if (username.equalsIgnoreCase(r.status)) {
                myBookingsList.getItems().add(
                        "Room " + r.id +
                                " | " + r.type +
                                " | " + r.price + "€"
                );
            }
        }

        if (myBookingsList.getItems().isEmpty()) {
            myBookingsList.getItems().add("No bookings yet.");
        }
    }


    @FXML
    private void onUnbookClick() {

        String roomId = roomIdField.getText().trim();

        if (roomId.isEmpty()) {
            showError("Please enter room ID.");
            return;
        }

        Room target = null;
        for (Room r : rooms) {
            if (r.id.equals(roomId) && username.equals(r.status)) {
                target = r;
                break;
            }
        }

        if (target == null) {
            showError("You have no booking with this room ID.");
            return;
        }


        target.status = "available";
        roomService.saveRooms(rooms);

        roomIdField.clear();
        errorLabel.setText("");
        loadMyBookings();
    }


    @FXML
    private void onBackClick() {
        Stage stage = (Stage) backBtn.getScene().getWindow();
        Navigation.loadPage(stage, "/com/example/javafxapp/LoginPage.fxml");
    }

    @FXML
    private void onBookRoomClick() {
        Stage stage = (Stage) bookRoomBtn.getScene().getWindow();
        Navigation.loadPage(stage, "/com/example/javafxapp/UserBookRoom.fxml");
    }

    private void showError(String msg) {
        errorLabel.setText(msg);
        errorLabel.setOpacity(1);
    }
}

