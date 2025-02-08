package main.java.zadaciZaPrvKolokvium;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class zadaca4 {

    public static int evaluateExpression(String expression) {
        Stack<Integer> number = new Stack<>();
        int sum = 0, length = expression.length();
        char operator = '+';
        for (int i = 0; i < length; i++) {
            char character = expression.charAt(i);
            if (Character.isDigit(character)) {
                sum = sum * 10 + (Character.getNumericValue(character));
            }
            if (!Character.isDigit(character) || i == length - 1) {
                if (operator == '+') {
                    number.push(sum);
                } else if (operator == '-') {
                    number.push(-sum);
                } else if (operator == '*') {
                    number.push(number.pop() * sum);
                } else if (operator == '/') {
                    number.push(number.pop() / sum);
                }
                sum = 0;
                operator = character;
            }
        }
        int totalSum = 0;
        while (!number.isEmpty()) {
            totalSum += number.pop();
        }
        return totalSum;

    }

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(evaluateExpression(input.readLine()));
    }

}