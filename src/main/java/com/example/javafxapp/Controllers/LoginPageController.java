package com.example.javafxapp.Controllers;

import com.example.javafxapp.Navigation.Navigation;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

public class LoginPageController {
    @FXML
    private ComboBox roleComboBox;
    @FXML
    private ImageView leftImage;

    @FXML
    private Label aboutLabel; // το Label που πατάμε για AboutPage

    private final String[] photos = {
            "/images/photo1.jpg",
            "/images/photo2.jpg",
            "/images/photo3.jpg",
            "/images/photo4.jpg"
    };

    private int index = 0;

    @FXML
    public void initialize() {
        startSlideshow();

        // Προσθήκη click listener στο About Label
        aboutLabel.setOnMouseClicked(event -> openAboutPage());
        roleComboBox.getItems().addAll("User", "Admin");
    }

    private void startSlideshow() {
        // Αρχική εικόνα
        leftImage.setImage(
                new Image(getClass().getResource(photos[index]).toExternalForm())
        );

        FadeTransition fadeOut = new FadeTransition(Duration.seconds(1), leftImage);
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);

        PauseTransition still =
                new PauseTransition(Duration.seconds(6.5));

        PauseTransition changeImage = new PauseTransition(Duration.seconds(0.1));
        changeImage.setOnFinished(e -> {
            index = (index + 1) % photos.length;

            leftImage.setImage(
                    new Image(getClass().getResource(photos[index]).toExternalForm())
            );
        });

        FadeTransition fadeIn = new FadeTransition(Duration.seconds(1), leftImage);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);

        SequentialTransition slideshow =
                new SequentialTransition(still, fadeOut, changeImage, fadeIn);

        slideshow.setCycleCount(SequentialTransition.INDEFINITE);
        slideshow.play();
    }

    // Μέθοδος για να ανοίξει το AboutPage
    private void openAboutPage() {
        Stage stage = (Stage) aboutLabel.getScene().getWindow();
        Navigation.loadPage(stage, "/com/example/javafxapp/AboutPage.fxml");
    }

    @FXML
    private void onLoginClick() {
        String role = (String) roleComboBox.getValue();

        if (role == null) {
            System.out.println("Please select a role.");
            return;
        }

        Stage stage = (Stage) roleComboBox.getScene().getWindow();

        switch (role) {
            case "User" -> Navigation.loadPage(stage, "/com/example/javafxapp/UserLogin.fxml");
            case "Admin" -> Navigation.loadPage(stage, "/com/example/javafxapp/AdminPage.fxml");
        }
    }

}




