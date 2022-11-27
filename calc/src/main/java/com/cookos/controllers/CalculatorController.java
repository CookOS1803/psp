package com.cookos.controllers;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class CalculatorController {

    private String firstToken = "";
    private String secondToken = "";
    private char sign = 0;
    @FXML private Text equation;    

    private void typeNumber(String number) {
        if (sign == 0) {
            firstToken = firstToken.concat(number);
            writeFirstToken();
        } else {
            secondToken = secondToken.concat(number);
            writeFullEquation();
        }
    }

    private void writeFullEquation() {
        equation.setText(firstToken + sign + secondToken);
    }

    private void writeFirstToken() {
        equation.setText(firstToken);
    }

    @FXML
    private void typeOne() {
        typeNumber("1");
    }

    @FXML
    private void typeTwo() {
        typeNumber("2");
    }
    
    @FXML
    private void typeThree() {
        typeNumber("3");
    }
    
    @FXML
    private void typeFour() {
        typeNumber("4");
    }
    
    @FXML
    private void typeFive() {
        typeNumber("5");
    }
    
    @FXML
    private void typeSix() {
        typeNumber("6");
    }
    
    @FXML
    private void typeSeven() {
        typeNumber("7");
    }
    
    @FXML
    private void typeEight() {
        typeNumber("8");
    }
    
    @FXML
    private void typeNine() {
        typeNumber("9");
    }    
    
    @FXML
    private void typeZero() {
        typeNumber("0");
    }
    
    @FXML
    private void putComma() {
        if (!secondToken.isEmpty()) {
            if (!secondToken.contains(".")) {
                secondToken = secondToken.concat(".");
                writeFullEquation();
            }
        } else if (sign == 0 && !firstToken.isEmpty() && !firstToken.contains(".")) {
            firstToken = firstToken.concat(".");
            writeFirstToken();
        }
    }
    
    @FXML
    private void solve() {
        if (!secondToken.isEmpty()) {
            
            solveEquation();

            if (firstToken.equals("NaN") || firstToken.equals("Infinity")) {
                equation.setText("Error");
                firstToken = "";
            } else {
                writeFirstToken();
            }
        }
    }

    private void solveEquation() {
        double firstValue = Double.valueOf(firstToken);
        double secondValue = Double.valueOf(secondToken);

        double answer = switch (sign) {
            case '+' -> firstValue + secondValue;
            case '-' -> firstValue - secondValue;
            case '*' -> firstValue * secondValue;
            case '/' -> firstValue / secondValue;
            default -> 0;
        };

        firstToken = String.valueOf(answer);
        secondToken = "";
        sign = 0;
    }
    
    private void putOperand(char operand) {
        if (firstToken.isEmpty()) {
            firstToken = "0";
        }

        if (!secondToken.isEmpty()) {
            solveEquation();
        }
        sign = operand;
        writeFullEquation();
    }

    @FXML
    private void putPlus() {
        putOperand('+');
    }
    
    @FXML
    private void putMinus() {
        putOperand('-');
    }
    
    @FXML
    private void putMultiply() {
        putOperand('*');
    }
    
    @FXML
    private void putDivision() {
        putOperand('/');
    }
        
    @FXML
    private void erase() {
        if (secondToken.isEmpty()) {
            if (firstToken.isEmpty())
                return;

            if (sign != 0) {
                sign = 0;
            } else {
                firstToken = firstToken.substring(0, firstToken.length() - 1);
            }
            writeFirstToken();
        } else {
            secondToken = secondToken.substring(0, secondToken.length() - 1);
            writeFullEquation();
        }
    }

    @FXML
    private void clearAll() {
        firstToken = "";
        secondToken = "";
        sign = 0;

        writeFirstToken();
    }

    @FXML
    private void clearSecond() {
        secondToken = "";

        writeFullEquation();
    }

    @FXML
    private void sqrt() {
        if (!secondToken.isEmpty()) {
            secondToken = String.valueOf(Math.sqrt(Double.valueOf(secondToken)));
            writeFullEquation();
        } else if (sign == 0 && !firstToken.isEmpty()) {

            double answer = Math.sqrt(Double.valueOf(firstToken));

            if (Double.isNaN(answer)) {
                equation.setText("Error");
                firstToken = "";
            } else {
                firstToken = String.valueOf(answer);
                writeFirstToken();
            }
        }
    }

    @FXML
    private void pow2() {
        if (!secondToken.isEmpty()) {
            double pow = Math.pow(Double.valueOf(secondToken), 2);

            if (Double.isInfinite(pow)) {
                equation.setText(firstToken + sign + "Infinity");
                secondToken = "0";
            } else {
                secondToken = String.valueOf(pow);
                writeFullEquation();
            }
        } else if (sign == 0 && !firstToken.isEmpty()) {
            double pow = Math.pow(Double.valueOf(firstToken), 2);

            if (Double.isInfinite(pow)) {
                equation.setText("Infinity");
                firstToken = "";
            } else {
                firstToken = String.valueOf(pow);
                writeFirstToken();
            }
        }
    }

    @FXML
    private void inverse() {
        if (!secondToken.isEmpty()) {
            double pow = 1 / Double.valueOf(secondToken);

            if (Double.isInfinite(pow)) {
                equation.setText(firstToken + sign + "Infinity");
                secondToken = "0";
            } else {
                secondToken = String.valueOf(pow);
                writeFullEquation();
            }
        } else if (sign == 0 && !firstToken.isEmpty()) {
            double pow = 1 / Double.valueOf(firstToken);

            if (Double.isInfinite(pow)) {
                equation.setText("Infinity");
                firstToken = "";
            } else {
                firstToken = String.valueOf(pow);
                writeFirstToken();
            }
        }
    }
}
