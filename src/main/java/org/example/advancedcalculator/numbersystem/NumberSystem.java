package org.example.advancedcalculator.numbersystem;

import org.example.advancedcalculator.calculation.Operation;

public interface NumberSystem {

    String add(String a, String b);
    String subtract(String a, String b);
    String divide(String a, String b) throws ArithmeticException;
    String modulo(String a, String b) throws ArithmeticException;
    String multiply(String a, String b);
    String changeSign(String a);
    String factorial(String a) throws ArithmeticException;
    String newtonSymbol(String a, String b) throws ArithmeticException;
    String power(String a, String b);
    String doTwoArgOperation(Operation operation, String a, String b);
    String doOneArgOperation(Operation operation, String a);
    String binaryToDecimal(String a);
    String decimalToBinary(String a);
    String hexToDecimal(String a);
    String decimalToHex(String a);
}
