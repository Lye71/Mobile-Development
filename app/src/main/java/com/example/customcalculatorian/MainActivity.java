package com.example.customcalculatorian;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private TextView display;
    private double firstOperand = 0;
    private String currentOperator = "";
    private boolean isNewOp = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.display);

        // make buttons into array
        int[] numberIds = {
                R.id.btn_0, R.id.btn_1, R.id.btn_2, R.id.btn_3, R.id.btn_4,
                R.id.btn_5, R.id.btn_6, R.id.btn_7, R.id.btn_8, R.id.btn_9
        };

        View.OnClickListener numListener = v -> {
            Button b = (Button) v;
            if (isNewOp) {
                display.setText(b.getText());
                isNewOp = false;
            } else {
                display.append(b.getText());
            }
        };


        for (int id : numberIds) {
            findViewById(id).setOnClickListener(numListener);
        }

        findViewById(R.id.btn_plus).setOnClickListener(v -> prepareOp("+"));
        findViewById(R.id.btn_minus).setOnClickListener(v -> prepareOp("-"));
        findViewById(R.id.btn_times).setOnClickListener(v -> prepareOp("*"));
        findViewById(R.id.btn_divide).setOnClickListener(v -> prepareOp("/"));

        // Equals Button
        findViewById(R.id.btn_equals).setOnClickListener(v -> {
            String displayText = display.getText().toString();
            if (displayText.isEmpty() || displayText.equals("Cannot divide by zero")) return;

            double secondOperand = Double.parseDouble(displayText);
            double result = 0;

            switch (currentOperator) {
                case "+": result = firstOperand + secondOperand; break;
                case "-": result = firstOperand - secondOperand; break;
                case "*": result = firstOperand * secondOperand; break;
                case "/":
                    if (secondOperand == 0) {
                        display.setText("Cannot divide by zero"); // Required for Milestone 2
                        isNewOp = true;
                        return;
                    }
                    result = firstOperand / secondOperand;
                    break;
            }
            display.setText(String.valueOf(result));
            isNewOp = true;
        });

        // Clear Button
        findViewById(R.id.btn_clear).setOnClickListener(v -> {
            display.setText("0");
            isNewOp = true;
        });

        // Padding
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void prepareOp(String op) {
        String displayText = display.getText().toString();
        if (displayText.isEmpty() || displayText.equals("Cannot divide by zero")) return;

        firstOperand = Double.parseDouble(displayText);
        currentOperator = op;
        isNewOp = true;
    }
}