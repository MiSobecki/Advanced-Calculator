module AdvancedCalculator {
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.controls;

    exports org.example.advancedcalculator.main;
    opens org.example.advancedcalculator.controller to javafx.fxml;
}