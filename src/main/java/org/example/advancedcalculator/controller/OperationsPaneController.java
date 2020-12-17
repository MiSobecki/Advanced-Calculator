package org.example.advancedcalculator.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import org.example.advancedcalculator.calculation.Counter;
import org.example.advancedcalculator.calculation.Operation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OperationsPaneController {

    @FXML
    private GridPane operationsPaneGridPane;

    @FXML
    private Button addButton;

    @FXML
    private Button divideButton;

    @FXML
    private Button minusButton;

    @FXML
    private Button modButton;

    @FXML
    private Button multiButton;

    @FXML
    private Button changeSignButton;

    @FXML
    private Button factorialButton;

    @FXML
    private Button newtonButton;

    @FXML
    private Button powButton;

    public List<Button> listOfOperations;

    public void initialize() {
        listOfOperations = new ArrayList<>();
        listOfOperations.addAll(Arrays.asList(addButton, minusButton, divideButton, multiButton, modButton,
                newtonButton, powButton, factorialButton, changeSignButton));
    }

    private void stepSecondPhaseCounter(Counter counter, Button btn) {
        counter.doStep();
        counter.changePhaseToSecond();
        counter.setOperationLabel(btn);
    }

    private void stepFirstPhaseCounter(Counter counter, Button btn) {
        counter.changePhaseToFirst();
        counter.setOneArgOperationToActive();
        counter.doStep();
        counter.setOperationLabel(btn);
    }

    public void setUpButtons(Counter counter) {
        addButton.addEventFilter(ActionEvent.ACTION, event -> {
            counter.setOperation(Operation.ADD);
            stepSecondPhaseCounter(counter, addButton);
        });

        minusButton.addEventFilter(ActionEvent.ACTION, event -> {
            counter.setOperation(Operation.SUBTRACT);
            stepSecondPhaseCounter(counter, minusButton);
        });

        divideButton.addEventFilter(ActionEvent.ACTION, event -> {
            counter.setOperation(Operation.DIVIDE);
            stepSecondPhaseCounter(counter, divideButton);
        });

        multiButton.addEventFilter(ActionEvent.ACTION, event -> {
            counter.setOperation(Operation.MULTIPLY);
            stepSecondPhaseCounter(counter, multiButton);
        });

        newtonButton.addEventFilter(ActionEvent.ACTION, event -> {
            counter.setOperation(Operation.NEWTON);
            stepSecondPhaseCounter(counter, newtonButton);
        });

        powButton.addEventFilter(ActionEvent.ACTION, event -> {
            counter.setOperation(Operation.POWER);
            stepSecondPhaseCounter(counter, powButton);
        });

        modButton.addEventFilter(ActionEvent.ACTION, event -> {
            counter.setOperation(Operation.MODULO);
            stepSecondPhaseCounter(counter, modButton);
        });

        changeSignButton.addEventFilter(ActionEvent.ACTION, event -> {
            counter.setOperation(Operation.CHANGE_SIGN);
            stepFirstPhaseCounter(counter, changeSignButton);

        });

        factorialButton.addEventFilter(ActionEvent.ACTION, event -> {
            counter.setOperation(Operation.FACTORIAL);
            stepFirstPhaseCounter(counter, factorialButton);
        });
    }
}
