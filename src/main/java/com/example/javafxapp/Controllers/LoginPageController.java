package com.example.javafxapp.Controllers;
import com.example.javafxapp.Session.UserSession;
import com.example.javafxapp.Json_services.LoginService;
import com.example.javafxapp.Navigation.Navigation;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.List;

public class LoginPageController {
    @FXML
    private VBox formVBox; // το VBox που περιέχει loginButton και errorLabel
    @FXML
    private Label errorLabel; // το error label που θα εμφανίζεται κάτω από το button
    @FXML
    private Button loginButton; // το login button
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
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
        roleComboBox.getItems().setAll("User", "Admin");

        usernameField.setText("");
        passwordField.setText("");
        errorLabel.setOpacity(0);

        // ΜΗΝ αφήνεις παλιό spacing ή animation state
        errorLabel.setTranslateY(0);

        startSlideshow();

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
        Stage stage = (Stage) aboutLabel.getScene().getWindow();
        Navigation.loadPage(stage, "/com/example/javafxapp/AboutPage.fxml");
    }

    private boolean swapped = false; // για να μην γίνει το swap πολλαπλές φορές




    private void showError(String msg) {
        errorLabel.setText(msg);
        errorLabel.setOpacity(1);
    }


    @FXML
    private void onLoginClick() {

        String selectedRole = (String) roleComboBox.getValue();

        if (selectedRole == null) {
            showError("Please select a role.");
            return;
        }

        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();

        LoginService service = new LoginService();
        List<LoginService.User> users = service.loadUsers();

        if (users == null) {
            showError("Internal error: Could not read users.json");
            return;
        }

        LoginService.User matchedUser = null;

        for (LoginService.User u : users) {
            if (u.username.equals(username) &&
                    u.password.equals(password) &&
                    u.role.equals(selectedRole)) {

                matchedUser = u;
                break;
            }
        }

        if (matchedUser == null) {
            showError("Sorry, your password was incorrect.\nPlease double-check your password.");
            return;
        }

        // ✅ ΕΔΩ ΑΚΡΙΒΩΣ ΑΠΟΘΗΚΕΥΟΥΜΕ ΤΟ LOGGED-IN USER
        UserSession.login(matchedUser.username);

        Stage stage = (Stage) roleComboBox.getScene().getWindow();

        // ---------------------------
        //  * ADMIN LOGIN *
        // ---------------------------
        if (matchedUser.role.equals("Admin")) {
            Navigation.loadPage(stage, "/com/example/javafxapp/AdminLogin.fxml");
            return;
        }

        // ---------------------------
        //  * USER LOGIN *
        // ---------------------------
        Navigation.loadPage(stage, "/com/example/javafxapp/UserLogin.fxml");
    }




}




