package com.example.javafxapp.Controllers;

import com.example.javafxapp.Navigation.Navigation;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class AdminBookingsController {
    @FXML
    private Button backBtn;

    @FXML
    private void onBackClick() {
        Stage stage = (Stage) backBtn.getScene().getWindow();
        Navigation.loadPage(stage, "/com/example/javafxapp/LoginPage.fxml");
    }
    @FXML
    private Button roomsBtn;

    @FXML
    private void onRoomsClick() {
        Stage stage = (Stage) roomsBtn.getScene().getWindow();
        Navigation.loadPage(stage, "/com/example/javafxapp/AdminRooms.fxml");
    }
    @FXML
    private Button bookingsBtn;

    @FXML
    private void onBookingsClick() {
        Stage stage = (Stage) bookingsBtn.getScene().getWindow();
        Navigation.loadPage(stage, "/com/example/javafxapp/AdminBookings.fxml");
    }
}
