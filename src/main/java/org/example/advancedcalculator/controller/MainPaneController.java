package org.example.advancedcalculator.controller;

import javafx.fxml.FXML;
import org.example.advancedcalculator.calculation.Counter;

public class MainPaneController {

    @FXML
    private ResultPaneController resultPaneController;

    @FXML
    private DecimalNumericPaneController decimalNumericPaneController;

    @FXML
    private OperationsPaneController operationsPaneController;

    public void initialize() {
        Counter counter = new Counter(resultPaneController);
        decimalNumericPaneController.setUpDecimalButtons(resultPaneController);
        decimalNumericPaneController.showResult(counter);
        operationsPaneController.setUpButtons(counter);
        resultPaneController.setUpResultButtons(counter);
    }
}
