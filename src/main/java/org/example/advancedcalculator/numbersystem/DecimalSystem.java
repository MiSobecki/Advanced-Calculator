package org.example.advancedcalculator.numbersystem;

import org.example.advancedcalculator.calculation.Operation;

import java.math.BigInteger;

public class DecimalSystem implements NumberSystem {

    @Override
    public String add(String a, String b) {
        BigInteger result = new BigInteger(a).add(new BigInteger(b));
        return result.toString();
    }

    @Override
    public String subtract(String a, String b) {
        BigInteger result = new BigInteger(a).subtract(new BigInteger(b));
        return result.toString();
    }

    @Override
    public String divide(String a, String b) throws ArithmeticException {
        BigInteger result = new BigInteger(a).divide(new BigInteger(b));
        return result.toString();
    }

    @Override
    public String modulo(String a, String b) throws ArithmeticException {
        BigInteger result = new BigInteger(a).mod(new BigInteger(b));
        return result.toString();
    }

    @Override
    public String multiply(String a, String b) {
        BigInteger result = new BigInteger(a).multiply(new BigInteger(b));
        return result.toString();
    }

    @Override
    public String changeSign(String a) {
        BigInteger result = new BigInteger("0").subtract(new BigInteger(a));
        return result.toString();
    }

    @Override
    public String factorial(String a) throws ArithmeticException {
        BigInteger result = factorial(new BigInteger(a));
        return result.toString();
    }

    @Override
    public String newtonSymbol(String a, String b) throws ArithmeticException {
        BigInteger result = newton(new BigInteger(a), new BigInteger(b));
        return result.toString();
    }

    @Override
    public String power(String a, String b) {
        BigInteger result = new BigInteger(a).pow(Integer.parseInt(b));
        return result.toString();
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
        return String.valueOf(Integer.parseInt(a, 2));
    }

    @Override
    public String decimalToBinary(String a) {
        int n = Integer.parseInt(a);
        return Integer.toBinaryString(n);
    }

    @Override
    public String hexToDecimal(String a) {
        return String.valueOf(Integer.parseInt(a, 16));
    }

    @Override
    public String decimalToHex(String a) {
        int n = Integer.parseInt(a);
        return Integer.toHexString(n).toUpperCase();
    }

    private BigInteger factorial(BigInteger n) {
        BigInteger fact = new BigInteger("1");

        for (BigInteger i = new BigInteger("2"); i.compareTo(n) <= 0; i = i.add(new BigInteger("1"))) {
            fact = fact.multiply(i);
        }
        return fact;
    }

    private BigInteger newton(BigInteger n, BigInteger k) throws ArithmeticException {
        BigInteger result = new BigInteger("1");

        for (BigInteger i = new BigInteger("1"); i.compareTo(k) <= 0; i = i.add(new BigInteger("1"))) {
            result = result.multiply(n.subtract(i).add(new BigInteger("1"))).divide(i);
        }
        return result;
    }
}
