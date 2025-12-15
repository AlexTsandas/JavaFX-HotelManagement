package com.example.javafxapp.Controllers;

import com.example.javafxapp.Json_services.RoomService;
import com.example.javafxapp.Json_services.RoomService.Room;
import com.example.javafxapp.Session.UserSession;
import com.example.javafxapp.Navigation.Navigation;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import javafx.stage.Stage;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class UserBookController implements Initializable {


    private RoomService roomService = new RoomService();
    private List<Room> rooms;


    @FXML private ListView<String> availableRoomsList;
    @FXML private TextField roomIdField;
    @FXML private Label errorLabel;
    @FXML private Button backBtn;
    @FXML private Button myBookingsBtn;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rooms = roomService.loadRooms();
        loadAvailableRooms();
    }


    private void loadAvailableRooms() {
        availableRoomsList.getItems().clear();

        for (Room r : rooms) {
            if ("available".equalsIgnoreCase(r.status)) {
                availableRoomsList.getItems().add(
                        r.id + " | " + r.type + " | " + r.price + "€"
                );
            }
        }
    }


    @FXML
    private void onBookClick() {

        String roomId = roomIdField.getText().trim();
        String username = UserSession.getUsername();

        if (roomId.isEmpty()) {
            showError("Please enter room ID.");
            return;
        }

        Room target = null;
        for (Room r : rooms) {
            if (r.id.equals(roomId) && r.status.equals("available")) {
                target = r;
                break;
            }
        }

        if (target == null) {
            showError("Room not available.");
            return;
        }


        target.status = username;
        roomService.saveRooms(rooms);

        loadAvailableRooms();
        roomIdField.clear();
        errorLabel.setText("");
    }


    @FXML
    private void onBackClick() {
        Stage stage = (Stage) backBtn.getScene().getWindow();
        Navigation.loadPage(stage, "/com/example/javafxapp/LoginPage.fxml");
    }

    @FXML
    private void onMyBookingsClick() {
        Stage stage = (Stage) myBookingsBtn.getScene().getWindow();
        Navigation.loadPage(stage, "/com/example/javafxapp/UsersBooking.fxml");
    }


    private void showError(String msg) {
        errorLabel.setText(msg);
        errorLabel.setOpacity(1);
    }
}



