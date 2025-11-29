package com.example.javafxapp.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("/com/example/javafxapp/LoginPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.setTitle("HotelNest");
        stage.setResizable(false);
        stage.getIcons().add(
                new Image(MainApp.class.getResource("/images/HotelNest.png").toExternalForm())
        );
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
