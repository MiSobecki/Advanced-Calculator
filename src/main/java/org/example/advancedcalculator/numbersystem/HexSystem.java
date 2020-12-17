package org.example.advancedcalculator.numbersystem;

import org.example.advancedcalculator.calculation.Operation;

public class HexSystem implements NumberSystem{

    private final DecimalSystem decimalSystem;

    public HexSystem() {
        this.decimalSystem = new DecimalSystem();
    }

    @Override
    public String add(String a, String b) {
        a = hexToDecimal(a);
        b = hexToDecimal(b);
        String result = decimalSystem.add(a, b);
        return decimalToHex(result);
    }

    @Override
    public String subtract(String a, String b) {
        a = hexToDecimal(a);
        b = hexToDecimal(b);
        String result = decimalSystem.subtract(a, b);
        return decimalToHex(result);
    }

    @Override
    public String divide(String a, String b) throws ArithmeticException {
        a = hexToDecimal(a);
        b = hexToDecimal(b);
        String result = decimalSystem.divide(a, b);
        return decimalToHex(result);
    }

    @Override
    public String modulo(String a, String b) throws ArithmeticException {
        a = hexToDecimal(a);
        b = hexToDecimal(b);
        String result = decimalSystem.modulo(a, b);
        return decimalToHex(result);
    }

    @Override
    public String multiply(String a, String b) {
        a = hexToDecimal(a);
        b = hexToDecimal(b);
        String result = decimalSystem.multiply(a, b);
        return decimalToHex(result);
    }

    @Override
    public String changeSign(String a) {
        a = hexToDecimal(a);
        String result = decimalSystem.changeSign(a);
        return decimalToHex(result);
    }

    @Override
    public String factorial(String a) throws ArithmeticException {
        a = hexToDecimal(a);
        String result = decimalSystem.factorial(a);
        return decimalToHex(result);
    }

    @Override
    public String newtonSymbol(String a, String b) throws ArithmeticException {
        a = hexToDecimal(a);
        b = hexToDecimal(b);
        String result = decimalSystem.newtonSymbol(a, b);
        return decimalToHex(result);
    }

    @Override
    public String power(String a, String b) {
        a = hexToDecimal(a);
        b = hexToDecimal(b);
        String result = decimalSystem.power(a, b);
        return decimalToHex(result);
    }

    @Override
    public String doTwoArgOperation(Operation operation, String a, String b) {
        return switch (operation) {
            case ADD -> add(a, b);
            case POWER -> power(a, b);
            case DIVIDE -> divide(a, b);
            case MODULO -> modulo(a, b);
            case NEWTON -> newtonSymbol(a, b);
            case MULTIPLY -> multiply(a, b);
            case SUBTRACT -> subtract(a, b);
            default -> "0";
        };
    }

    @Override
    public String doOneArgOperation(Operation operation, String a) {
        if (operation == Operation.CHANGE_SIGN) return changeSign(a);
        else if (operation == Operation.FACTORIAL) return  factorial(a);
        return "0";
    }

    @Override
    public String binaryToDecimal(String a) {
        return decimalSystem.binaryToDecimal(a);
    }

    @Override
    public String decimalToBinary(String a) {
        return decimalSystem.decimalToBinary(a);
    }

    @Override
    public String hexToDecimal(String a) {
        return decimalSystem.hexToDecimal(a);
    }

    @Override
    public String decimalToHex(String a) {
        return decimalSystem.decimalToHex(a);
    }
}
