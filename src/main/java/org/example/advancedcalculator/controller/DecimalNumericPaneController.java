package org.example.advancedcalculator.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import org.example.advancedcalculator.calculation.Counter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DecimalNumericPaneController {

    public List<Button> listOfDigitNumbers;

    @FXML
    private GridPane decimalNumericPaneGridPane;

    @FXML
    private Button sevenButton;

    @FXML
    private Button eightButton;

    @FXML
    private Button nineButton;

    @FXML
    private Button fourButton;

    @FXML
    private Button fiveButton;

    @FXML
    private Button sixButton;

    @FXML
    private Button oneButton;

    @FXML
    private Button twoButton;

    @FXML
    private Button threeButton;

    @FXML
    private Button zeroButton;

    @FXML
    private Button resultButton;

    public void initialize() {
        listOfDigitNumbers = new ArrayList<>();
        listOfDigitNumbers.addAll(Arrays.asList(zeroButton, oneButton, twoButton, threeButton, fourButton, fiveButton,
                sixButton, sevenButton, eightButton, nineButton));
    }

    public void showResult(Counter counter) {
        resultButton.addEventFilter(ActionEvent.ACTION, event -> {
            counter.doStep();
            counter.changePhaseToFirst();
            counter.setOperationLabel(resultButton);
        });
    }

    public void setUpDecimalButtons(ResultPaneController resultPaneController) {
        for (Button btn : listOfDigitNumbers) {
            btn.addEventFilter(ActionEvent.ACTION, event -> {
                String text = resultPaneController.getActualResultTextField().getText();
                if (text.equals("0")) text = btn.getText();
                else text = text + btn.getText();
                resultPaneController.getActualResultTextField().setText(text);
            });
        }
    }
}
