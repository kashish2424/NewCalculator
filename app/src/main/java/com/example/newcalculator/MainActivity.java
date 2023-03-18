package com.example.newcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MainActivity extends AppCompatActivity {

    TextView inputText, outputText;

    private String input, output, newOutput;

    private Button button0, button1, button2, button3, button4, button5, button6, button7, button8, button9, buttonAdd, buttonMultiply, buttonSubs, buttonDivision, buttonPoint, buttonPercentage, buttonPower, buttonEqual, buttonClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputText = findViewById(R.id.input_text);
        outputText = findViewById(R.id.output_text);

        button0 = findViewById(R.id.btn0);
        button1 = findViewById(R.id.btn1);
        button2 = findViewById(R.id.btn2);
        button3 = findViewById(R.id.btn3);
        button4 = findViewById(R.id.btn4);
        button5 = findViewById(R.id.btn5);
        button6 = findViewById(R.id.btn6);
        button7 = findViewById(R.id.btn7);
        button8 = findViewById(R.id.btn8);
        button9 = findViewById(R.id.btn9);
        buttonAdd = findViewById(R.id.addition_btn);
        buttonSubs = findViewById(R.id.substraction_btn);
        buttonMultiply = findViewById(R.id.multiply_btn);
        buttonDivision = findViewById(R.id.division_btn);
        buttonEqual = findViewById(R.id.equal_btn);
        buttonPercentage = findViewById(R.id.percent_btn);
        buttonClear = findViewById(R.id.clr_btn);
        buttonPoint = findViewById(R.id.point_btn);
        buttonPower = findViewById(R.id.power_btn);

    }

    public void onButtonClicked(View view) {
        Button button = (Button) view;
        String data = button.getText().toString();
        switch (data) {
            case "C":
                input = null;
                output = null;
                newOutput = null;
                outputText.setText("");
                break;
            case "^":
                solve();
                input += "^";
                break;
            case "*":
                solve();
                input += "*";
                break;
            case "=":
                solve();
                break;
            case "%":
                input += "%";
                double d = Double.parseDouble(inputText.getText().toString()) / 100;
                outputText.setText(String.valueOf(d));
                break;
            default:
                if (input == null) {
                    input = "";
                }
                if (data.equals("+") || data.equals("/") || data.equals("-")) {
                    solve();
                }
                input += data;
                break;
        }
        inputText.setText(input);
    }

    private void solve() {
        if(output != null){
            input = cutDecimal(output);
            output = null;
        }

        if(input.contains("+")) {
            String[] numbers = input.split("\\+");
            if(numbers.length >= 2) {
                try{
                    BigDecimal result = new BigDecimal("0");
                    for(int i = 0; i < numbers.length; i++) {
                        BigDecimal num = new BigDecimal(numbers[i]);
                        result = result.add(num);
                    }
                    output = result.toString();
                    newOutput = cutDecimal(output);
                    outputText.setText(newOutput);
                } catch(Exception e) {
                    outputText.setError(e.getMessage().toString());
                }
            }
        }

        if(input.contains("*")) {
            String[] numbers = input.split("\\*");
            if(numbers.length >= 2) {
                try{
                    BigDecimal result = new BigDecimal("1");
                    for(int i = 0; i < numbers.length; i++) {
                        BigDecimal num = new BigDecimal(numbers[i]);
                        result = result.multiply(num);
                    }
                    output = result.toString();
                    newOutput = cutDecimal(output);
                    outputText.setText(newOutput);
                } catch(Exception e) {
                    outputText.setError(e.getMessage().toString());
                }
            }
        }

        if(input.contains("/")) {
            String[] numbers = input.split("/");
            if(numbers.length >= 2) {
                try{
                    BigDecimal result = new BigDecimal(numbers[0]);
                    for(int i = 1; i < numbers.length; i++) {
                        BigDecimal num = new BigDecimal(numbers[i]);
                        result = result.divide(num, 10, RoundingMode.HALF_UP);
                    }
                    output = result.toString();
                    newOutput = cutDecimal(output);
                    outputText.setText(newOutput);
                } catch(Exception e) {
                    outputText.setError(e.getMessage().toString());
                }
            }
        }

        if(input.contains("^")) {
            String[] numbers = input.split("\\^");
            if(numbers.length == 2) {
                try{
                    BigDecimal num1 = new BigDecimal(numbers[0]);
                    BigDecimal num2 = new BigDecimal(numbers[1]);
                    BigDecimal result = num1.pow(num2.intValue());
                    output = result.toString();
                    newOutput = cutDecimal(output);
                    outputText.setText(newOutput);
                } catch(Exception e) {
                    outputText.setError(e.getMessage().toString());
                }
            }
        }

        if(input.contains("-")) {
            String[] numbers = input.split("-");
            if(numbers.length >= 2) {
                try{
                    BigDecimal result = new BigDecimal(numbers[0]);
                    for(int i = 1; i < numbers.length; i++) {
                        BigDecimal num = new BigDecimal(numbers[i]);
                        result = result.subtract(num);
                    }
                    output = result.toString();
                    newOutput = cutDecimal(output);
                    outputText.setText(newOutput);
                } catch(Exception e) {
                    outputText.setError(e.getMessage().toString());
                }
            }
        }
    }


    private String cutDecimal(String number){
        String[] n = number.split("\\.");
        if(n.length > 1 && n[1].equals("0")){
            return n[0];
        } else {
            return number;
        }
    }

}
