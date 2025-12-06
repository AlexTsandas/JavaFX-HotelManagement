package com.example.javafxapp.Controllers;

import com.example.javafxapp.Navigation.Navigation;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class UserController {

    @FXML
    private Button backBtn;

    @FXML
    private void onBackClick() {
        Stage stage = (Stage) backBtn.getScene().getWindow();
        Navigation.loadPage(stage, "/com/example/javafxapp/LoginPage.fxml");
    }
}
