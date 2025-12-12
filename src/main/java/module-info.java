module com.example.javafxapp {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires com.google.gson;

    opens com.example.javafxapp.application to javafx.fxml;
    exports com.example.javafxapp.application;

    opens com.example.javafxapp.Controllers to javafx.fxml;
    exports com.example.javafxapp.Controllers;
    opens com.example.javafxapp.Json_services to com.google.gson;

}