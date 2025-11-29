package com.example.javafxapp.Controllers;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

public class LoginPageController {

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
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/javafxapp/AboutPage.fxml"));
            Stage stage = (Stage) aboutLabel.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}




