package com.example.mentalmathtrainer;

import java.util.List;
import java.util.Random;

public class Calculator {
    private static final String DEFAULT_PROMPT = "What is: ";
    private int maxDigits;
    private List<String> operators;
    private static Calculator INSTANCE;
    private Random random = new Random();


    private double answer;

    private Calculator(int maxDigits, List<String> operators) {
        this.maxDigits = maxDigits;
        this.operators = operators;
    }

    public static Calculator getInstance(int maxDigits, List<String> operators) {
        if (INSTANCE == null) {
            INSTANCE = new Calculator(maxDigits, operators);
        }
        return INSTANCE;
    }

    public String getQuestion() {
        StringBuilder questionBuilder = new StringBuilder();
        questionBuilder.append(DEFAULT_PROMPT);
        int digitOne = getDigit();
        int digitTwo = getDigit();
        int digitThree = getDigit();
        String operator = "/";

        this.answer = getAnswer(operator, digitOne, digitTwo, digitThree);

        questionBuilder.append(digitOne)
                .append(digitTwo)
                .append(" ")
                .append(operator)
                .append(" ")
                .append(digitThree);
        return questionBuilder.toString();
    }

    private double getAnswer(String operator, int ... digits) {
        double answer;
        switch(operator) {
            case "+" :
                answer = ((double) Integer.parseInt("" + digits[0] + digits[1]) +  (double) digits[2]);
                break;
            case "-" :
                answer = ((double) Integer.parseInt("" + digits[0] + digits[1]) -  (double) digits[2]);
                break;
            case "*" :
                answer = ((double) Integer.parseInt("" + digits[0] + digits[1]) *  (double) digits[2]);
                break;
            case "/" :
                answer = ((double) Integer.parseInt("" + digits[0] + digits[1]) /  (double) digits[2]);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + operator);
        }
        return answer;
    }

    private int getDigit() {
        return random.nextInt(9) + 1;
    }

    private String getOperator() {
        return operators.get(random.nextInt(4));
    }

    public boolean answerIsTrue(double givenAnswer) {
        return givenAnswer == answer;
    }
    public double getAnswer() {
        return answer;
    }
}
