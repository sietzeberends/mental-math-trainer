package com.example.mentalmathtrainer;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

import static java.util.Arrays.asList;

public class MainActivity extends AppCompatActivity {

    public static final int MAX_DIGITS = 2;
    public static final List<String> OPERATORS = asList("+","-","*","/");
    private Calculator calculator;
    TextView question;
    TextInputEditText answer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calculator = Calculator.getInstance(MAX_DIGITS, OPERATORS);
        question = findViewById(R.id.question);
        answer = findViewById(R.id.answer);
        Button answerButton = findViewById(R.id.answerButton);
        findViewById(R.id.playbutton).setOnClickListener(view -> {
            ((Button) view).setText("Next Question");
            setAnswer();
            answer.setVisibility(View.VISIBLE);
            answerButton.setVisibility(View.VISIBLE);
        });

        answerButton.setOnClickListener(view -> {
            double givenAnswer;
            ((InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE))
            .hideSoftInputFromWindow(view.getWindowToken(),
                    InputMethodManager.RESULT_UNCHANGED_SHOWN);
            if(!answer.getText().toString().isEmpty()) {
                givenAnswer = Double.parseDouble(answer.getText().toString());
            } else {
                Toast.makeText(this, "Enter a value", Toast.LENGTH_SHORT).show();
                return;
            }

            if (calculator.answerIsTrue(givenAnswer)) {
                Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
                setAnswer();
            } else {
                Toast.makeText(this, "Wrong, the answer was " + calculator.getAnswer(), Toast.LENGTH_LONG).show();
            }
            answer.setText("");
        });
    }

    public void setAnswer() {
        String questionText = calculator.getQuestion();
        while (String.valueOf(calculator.getAnswer()).length() > 5) {
            questionText = calculator.getQuestion();
        }
        question.setText(questionText);
    }
}
