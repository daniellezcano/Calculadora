package com.example.calculator;

import android.view.View;
import java.util.Stack;

public class Calculadora {

    public enum Operator {ADD, SUBTRACT, MULTIPLY, DIVIDE, BLANK}

    public static void main(String[] args) {
        //String expression = "2-6-7*8/2+5";
        //Calculadora calc = new Calculadora();
        //System.out.println(calc.compute(expression));

    }

    public double compute(String sequence) {
        Stack<Double> numberStack = new Stack<Double>();
        Stack<Operator> operatorStack = new Stack<Operator>();
        for (int i = 0; i < sequence.length(); i++) {
            try {
                double number = parseNumber(sequence, i);
                numberStack.push(number);
                i = coutLength(sequence,i);
                if (i >= sequence.length()) {
                    break;
                }
                Operator op = parseOperator(sequence, i);
                collapseTop(numberStack, operatorStack, op);
                operatorStack.push(op);
            } catch (NumberFormatException ex) {
                return Integer.MIN_VALUE;
            }
        }
        collapseTop(numberStack, operatorStack, Operator.BLANK);
        if (numberStack.size() == 1 && operatorStack.size() == 0) {
            return numberStack.pop();
        }
        return 0;
    }

    private void collapseTop(Stack<Double> numberStack, Stack<Operator> operatorStack, Operator futureTop) {
        while (numberStack.size() >= 2 && operatorStack.size() >= 1) {
            if (priorityOfOperator(futureTop) <= priorityOfOperator(operatorStack.peek())) {
                double second = numberStack.pop();
                double first = numberStack.pop();
                Operator op = operatorStack.pop();
                double result = applyOp(first, op, second);
                numberStack.push(result);
            } else {
                break;
            }
        }
    }

    private double applyOp(double left, Operator op, double right) {
        switch (op) {
            case ADD:
                return left + right;
            case SUBTRACT:
                return left - right;
            case MULTIPLY:
                return left * right;
            case DIVIDE:
                if(right == 0)
                    throw new RuntimeException();
                return left / right;
            default:
                return right;
        }
    }

    private int priorityOfOperator(Operator op) {
        switch (op) {
            case ADD:
                return 1;
            case SUBTRACT:
                return 1;
            case MULTIPLY:
                return 2;
            case DIVIDE:
                return 2;
            case BLANK:
                return 0;
        }
        return 0;
    }

    private double  parseNumber(String sequence, int offset) {
        StringBuilder sb = new StringBuilder();
        while (offset < sequence.length() && (Character.isDigit(sequence.charAt(offset)) || sequence.charAt(offset) == '.')) {
            sb.append(sequence.charAt(offset));
            offset++;
        }
        return Double.parseDouble(sb.toString());
    }

    private int coutLength(String sequence, int offset) {
        StringBuilder sb = new StringBuilder();
        while (offset < sequence.length() && (Character.isDigit(sequence.charAt(offset)) || sequence.charAt(offset) == '.')) {

            sb.append(sequence.charAt(offset));
            offset++;
        }
        return offset;
    }

    private Operator parseOperator(String sequence, int offset) {
        if (offset < sequence.length()) {
            char op = sequence.charAt(offset);
            switch (op) {
                case '+':
                    return Operator.ADD;
                case '-':
                    return Operator.SUBTRACT;
                case '*':
                    return Operator.MULTIPLY;
                case '/':
                    return Operator.DIVIDE;
            }
        }
        return Operator.BLANK;
    }

}

