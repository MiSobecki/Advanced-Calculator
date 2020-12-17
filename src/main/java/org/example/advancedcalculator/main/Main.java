package org.example.advancedcalculator.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public static VBox mainPane;

    @Override
    public void start(Stage stage) throws Exception {
        Main.mainPane = FXMLLoader.load(getClass().getResource("/fxml/mainPane.fxml"));
        Scene scene = new Scene(Main.mainPane);
        stage.setScene(scene);
        stage.setTitle("Advanced Calculator");
        stage.show();
    }
}
