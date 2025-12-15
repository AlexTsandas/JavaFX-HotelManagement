package com.example.javafxapp.Controllers;

import com.example.javafxapp.Navigation.Navigation;


import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.control.Button;

public class AboutController {

    @FXML
    private Button backBtn;

    @FXML
    private void onBackClick() {
        Stage stage = (Stage) backBtn.getScene().getWindow();
        Navigation.loadPage(stage, "/com/example/javafxapp/LoginPage.fxml");
    }
}
