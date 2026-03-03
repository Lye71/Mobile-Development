package com.example.hand_on2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int mCounter = 0;
    private TextView counterDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // FIX: Linking the view to avoid NullPointerException
        counterDisplay = findViewById(R.id.counterDisplay);
        Button btnIncrement = findViewById(R.id.btnIncrement);

        counterDisplay.setText(String.valueOf(mCounter));

        btnIncrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCounter++;
                counterDisplay.setText(String.valueOf(mCounter));
            }
        });
    }

    // Saving the state before rotation
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("COUNT_KEY", mCounter);
    }

    // Restoring the state after rotation
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mCounter = savedInstanceState.getInt("COUNT_KEY");
        counterDisplay.setText(String.valueOf(mCounter));
    }
}