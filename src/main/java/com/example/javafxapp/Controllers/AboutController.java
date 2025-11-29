package com.example.javafxapp.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;

public class AboutController {

    @FXML
    private Button backBtn;

    @FXML
    private void onBackClick() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/javafxapp/LoginPage.fxml"));
            Stage stage = (Stage) backBtn.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
