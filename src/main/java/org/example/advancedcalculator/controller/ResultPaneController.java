package org.example.advancedcalculator.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.example.advancedcalculator.calculation.Counter;
import org.example.advancedcalculator.main.Main;

import java.io.IOException;

public class ResultPaneController {

    @FXML
    private HBox resultPaneHBox;

    @FXML
    private VBox resultsVBox;

    @FXML
    private Label lastResultLabel;

    @FXML
    private TextField actualResultTextField;

    @FXML
    private VBox numberSystemVBox;

    @FXML
    private RadioButton binaryRadioButton;

    @FXML
    private RadioButton decimalRadioButton;

    @FXML
    private RadioButton hexRadioButton;

    @FXML
    private VBox resetVBox;

    @FXML
    private Button resetButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Label operationLabel;

    private ToggleGroup systemGroup;

    public void initialize() {
        systemGroup = new ToggleGroup();
        binaryRadioButton.setToggleGroup(systemGroup);
        decimalRadioButton.setToggleGroup(systemGroup);
        hexRadioButton.setToggleGroup(systemGroup);
        decimalRadioButton.setSelected(true);
    }

    public TextField getActualResultTextField() {
        return this.actualResultTextField;
    }

    public Label getLastResultLabel() {
        return this.lastResultLabel;
    }

    private void reset(Counter counter) {
        resetButton.addEventFilter(ActionEvent.ACTION, event -> counter.reset());
    }

    private void delete() {
        deleteButton.addEventFilter(ActionEvent.ACTION, event -> {
            StringBuilder actualText = new StringBuilder(actualResultTextField.getText());
            actualText.delete(actualText.length() - 1, actualText.length());
            actualResultTextField.setText(actualText.toString());
            if (actualResultTextField.getText().equals("")) actualResultTextField.setText("0");
        });
    }

    private void changeSystem(String file, Counter counter) {
        HBox h = (HBox) Main.mainPane.getChildren().get(1);
        Main.mainPane.getChildren().remove(1);
        h.getChildren().remove(0);
        GridPane g;
        try {
            g = FXMLLoader.load(getClass().getResource(file));
            for (Node btn : g.getChildren()) {
                Button btn1 = (Button) btn;
                if (!btn1.getText().equals("=")) {
                    btn1.addEventFilter(ActionEvent.ACTION, event -> {
                        String text = getActualResultTextField().getText();
                        if (text.equals("0")) text = btn1.getText();
                        else text = text + btn1.getText();
                        getActualResultTextField().setText(text);
                    });
                }
                else {
                    btn1.addEventFilter(ActionEvent.ACTION, event -> {
                        counter.doStep();
                        counter.changePhaseToFirst();
                        counter.setOperationLabel(btn1);
                    });
                }
            }

            g.setPrefHeight(400);
            g.setPrefWidth(300);
            h.getChildren().add(0, g);
            Main.mainPane.getChildren().add(1, h);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void changeSystemToBinary(Counter counter) {
        changeSystem("/fxml/binaryNumericPane.fxml", counter);
    }

    private void changeSystemToDecimal(Counter counter) {
        changeSystem("/fxml/decimalNumericPane.fxml", counter);
    }

    private void changeSystemToHex(Counter counter) {
        changeSystem("/fxml/hexNumericPane.fxml", counter);
    }

    private void setUpToggleGroup(Counter counter) {
        systemGroup.selectedToggleProperty().addListener((observableValue, toggle, t1) -> {
            if (systemGroup.getSelectedToggle() != null) {
                if (systemGroup.getSelectedToggle() == binaryRadioButton) {
                    changeSystemToBinary(counter);
                    counter.changeSystemToBinary();
                }
                else if (systemGroup.getSelectedToggle() == decimalRadioButton) {
                    changeSystemToDecimal(counter);
                    counter.changeSystemToDecimal();
                }
                else if (systemGroup.getSelectedToggle() == hexRadioButton) {
                    changeSystemToHex(counter);
                    counter.changeSystemToHex();
                }
            }
        });
    }

    public void setUpResultButtons(Counter counter) {
        reset(counter);
        delete();
        setUpToggleGroup(counter);
        actualResultTextField.setFocusTraversable(false);
        actualResultTextField.setMouseTransparent(true);
    }

    public Label getOperationLabel() {
        return operationLabel;
    }
}
