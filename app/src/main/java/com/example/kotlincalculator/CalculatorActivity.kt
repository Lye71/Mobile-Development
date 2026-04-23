package com.example.kotlincalculator

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CalculatorActivity : AppCompatActivity() {

    // Variables to hold our calculator state
    private var currentInput = ""
    private var firstOperand = 0.0
    private var currentOperator = ""

    private lateinit var tvDisplay: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)

        // 1. Setup Display Reference
        tvDisplay = findViewById(R.id.tv_display)

        // 2. Setup Number Buttons (0-9)
        // We group their IDs in a list so we can apply the exact same logic to all of them easily
        val numberIds = listOf(
            R.id.btn_0, R.id.btn_1, R.id.btn_2, R.id.btn_3, R.id.btn_4,
            R.id.btn_5, R.id.btn_6, R.id.btn_7, R.id.btn_8, R.id.btn_9
        )

        for (id in numberIds) {
            findViewById<Button>(id).setOnClickListener { view ->
                val button = view as Button
                currentInput += button.text.toString() // Append the number pressed
                tvDisplay.text = currentInput          // Update the screen
            }
        }

        // 3. Setup Operator Buttons (+, -, *, /)
        val btnAdd = findViewById<Button>(R.id.btn_add)
        val btnSubtract = findViewById<Button>(R.id.btn_subtract)
        val btnMultiply = findViewById<Button>(R.id.btn_multiply)
        val btnDivide = findViewById<Button>(R.id.btn_divide)

        // Create a reusable function for what happens when an operator is pressed
        val operatorListener = { view: android.view.View ->
            val button = view as Button
            firstOperand = currentInput.toDoubleOrNull() ?: 0.0 // Save the first number safely
            currentOperator = button.text.toString()            // Save the operator (+, -, *, /)
            currentInput = ""                                   // Clear the input for the next number
        }

        btnAdd.setOnClickListener(operatorListener)
        btnSubtract.setOnClickListener(operatorListener)
        btnMultiply.setOnClickListener(operatorListener)
        btnDivide.setOnClickListener(operatorListener)

        // 4. Setup Equals Button
        val btnEquals = findViewById<Button>(R.id.btn_equals)
        btnEquals.setOnClickListener {
            val secondOperand = currentInput.toDoubleOrNull() ?: 0.0

            // Call our math function below
            val result = calculateResult(firstOperand, secondOperand, currentOperator)

            // Clean up the formatting (removes the ".0" if it's a whole number)
            val formattedResult = if (result % 1.0 == 0.0) {
                result.toInt().toString()
            } else {
                result.toString()
            }

            tvDisplay.text = formattedResult
            currentInput = formattedResult // Save the result as the new input so you can keep doing math on it
        }

        // 5. Setup Clear Button (C)
        val btnClear = findViewById<Button>(R.id.btn_clear)
        btnClear.setOnClickListener {
            currentInput = ""
            firstOperand = 0.0
            currentOperator = ""
            tvDisplay.text = "0"
        }
    }

    // Our Control Flow function using 'when' to do the actual math
    private fun calculateResult(first: Double, second: Double, operator: String): Double {
        return when (operator) {
            "+" -> first + second
            "-" -> first - second
            "*" -> first * second
            "/" -> if (second != 0.0) first / second else 0.0 // Prevent dividing by zero crash!
            else -> 0.0
        }
    }
}