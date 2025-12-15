package com.example.javafxapp.Controllers;

import com.example.javafxapp.Navigation.Navigation;
import com.example.javafxapp.Session.UserSession;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class UserController implements Initializable {

    @FXML
    private Label welcomeLabel;

    @FXML
    private Button backBtn;

    @FXML
    private Button myBookingsBtn;

    @FXML
    private Button bookRoomBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String username = UserSession.getUsername();
        welcomeLabel.setText("Welcome " + username);
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

    @FXML
    private void onMyBookingsClick() {
        Stage stage = (Stage) myBookingsBtn.getScene().getWindow();
        Navigation.loadPage(stage, "/com/example/javafxapp/UsersBooking.fxml");
    }
}

