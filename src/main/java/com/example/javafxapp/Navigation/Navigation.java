package com.example.javafxapp.Navigation;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Navigation {

    private static final double WIDTH = 900;
    private static final double HEIGHT = 700;

    public static void loadPage(Stage stage, String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(Navigation.class.getResource(fxmlPath));
            Parent root = loader.load();

            Scene scene = new Scene(root, WIDTH, HEIGHT);
            stage.setScene(scene);

            // 🔒 FIXED WINDOW (μόνο αυτό προσθέσαμε)
            stage.setResizable(false);
            stage.setMinWidth(WIDTH);
            stage.setMinHeight(HEIGHT);
            stage.setMaxWidth(WIDTH);
            stage.setMaxHeight(HEIGHT);

            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



