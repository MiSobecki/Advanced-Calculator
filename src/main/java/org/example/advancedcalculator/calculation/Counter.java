package org.example.advancedcalculator.calculation;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.example.advancedcalculator.controller.ResultPaneController;
import org.example.advancedcalculator.numbersystem.BinarySystem;
import org.example.advancedcalculator.numbersystem.DecimalSystem;
import org.example.advancedcalculator.numbersystem.HexSystem;
import org.example.advancedcalculator.numbersystem.NumberSystem;

public class Counter {

    private String arg1;
    private Phase phase;
    private NumberSystem numberSystem;
    private final TextField argumentField;
    private final Label lastResult;
    private final Label operationLabel;
    private boolean isOneArgOperation;
    private Operation operation;

    public Counter(ResultPaneController resultPaneController) {
        phase = Phase.FIRST;
        numberSystem = new DecimalSystem();
        this.argumentField = resultPaneController.getActualResultTextField();
        this.lastResult = resultPaneController.getLastResultLabel();
        this.operationLabel = resultPaneController.getOperationLabel();
        isOneArgOperation = false;
    }

    public void changePhaseToFirst() {
        this.phase = Phase.FIRST;
    }

    public void changePhaseToSecond() {
        this.phase = Phase.SECOND;
    }

    public void doStep() {
        if (phase == Phase.FIRST) {
            arg1 = argumentField.getText();
            if (isOneArgOperation) {
                String result = numberSystem.doOneArgOperation(operation, arg1);
                argumentField.setText(result);
                lastResult.setText(result);
                setOneArgOperationToNonActive();
            }
            else argumentField.setText("0");
        }
        else if (phase == Phase.SECOND) {
            String arg2 = argumentField.getText();
            String result = "";
            try {
                result = numberSystem.doTwoArgOperation(operation, arg1, arg2);
            }
            catch (Exception exception) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error dialog");
                alert.setHeaderText("Arithmetical error");
                alert.setContentText("You can't divide by 0!");
                alert.showAndWait();
            }
            argumentField.setText("0");
            lastResult.setText(result);
            arg1 = result;
        }
    }

    public void reset() {
        phase = Phase.FIRST;
        argumentField.setText("0");
        lastResult.setText("0");
        operationLabel.setText("");
        isOneArgOperation = false;
    }

    public void changeSystemToDecimal() {
        if (this.numberSystem instanceof BinarySystem) {
            String n = argumentField.getText();
            argumentField.setText(numberSystem.binaryToDecimal(n));
            String r = lastResult.getText();
            lastResult.setText(numberSystem.binaryToDecimal(r));
            numberSystem = new DecimalSystem();
        }
        else if (this.numberSystem instanceof HexSystem) {
            String n = argumentField.getText();
            argumentField.setText(numberSystem.hexToDecimal(n));
            String r = lastResult.getText();
            lastResult.setText(numberSystem.hexToDecimal(r));
            numberSystem = new DecimalSystem();
        }
    }

    public void changeSystemToHex() {
        if (this.numberSystem instanceof BinarySystem) {
            String n = argumentField.getText();
            argumentField.setText(numberSystem.decimalToHex(numberSystem.binaryToDecimal(n)));
            String r = lastResult.getText();
            lastResult.setText(numberSystem.decimalToHex(numberSystem.binaryToDecimal(r)));
            numberSystem = new HexSystem();
        }
        else if (this.numberSystem instanceof DecimalSystem) {
            String n = argumentField.getText();
            argumentField.setText(numberSystem.decimalToHex(n));
            String r = lastResult.getText();
            lastResult.setText(numberSystem.decimalToHex(r));
            numberSystem = new HexSystem();
        }
    }

    public void changeSystemToBinary() {
        if (this.numberSystem instanceof DecimalSystem) {
            String n = argumentField.getText();
            argumentField.setText(numberSystem.decimalToBinary(n));
            String r = lastResult.getText();
            lastResult.setText(numberSystem.decimalToBinary(r));
            numberSystem = new BinarySystem();
        }
        else if (this.numberSystem instanceof HexSystem) {
            String n = argumentField.getText();
            argumentField.setText(numberSystem.decimalToBinary(numberSystem.hexToDecimal(n)));
            String r = lastResult.getText();
            lastResult.setText(numberSystem.decimalToBinary(numberSystem.hexToDecimal(r)));
            numberSystem = new BinarySystem();
        }
    }

    public void setOperationLabel(Button btn) {
        operationLabel.setText(btn.getText());
    }

    public void setOneArgOperationToActive() {
        this.isOneArgOperation = true;
    }

    public void setOneArgOperationToNonActive() {
        this.isOneArgOperation = false;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }
}
