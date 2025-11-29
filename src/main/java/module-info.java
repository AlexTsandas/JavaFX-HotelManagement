module com.example.javafxapp {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;

    opens com.example.javafxapp.application to javafx.fxml;
    exports com.example.javafxapp.application;

    opens com.example.javafxapp.Controllers to javafx.fxml;
    exports com.example.javafxapp.Controllers;
}