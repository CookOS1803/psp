module com.cookos {
    requires transitive javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;

    opens com.cookos to javafx.fxml;
    opens com.cookos.controllers to javafx.fxml;
    exports com.cookos;
    exports com.cookos.controllers;
}
