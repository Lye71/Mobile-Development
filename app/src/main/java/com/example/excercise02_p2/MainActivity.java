package com.example.excercise02_p2;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.excercise02_p2.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnSubmit.setOnClickListener(v -> validateLogin());
    }

    private void validateLogin() {
        String idInput = binding.etStudentId.getText().toString().trim();
        String passwordInput = binding.etPassword.getText().toString().trim();


        if (idInput.length() >= 2) {
            String lastTwoDigits = idInput.substring(idInput.length() - 2);

            String correctPassword = "white" + lastTwoDigits;

            if (passwordInput.equals(correctPassword)) {
                binding.tvResult.setText("Access Granted");
            } else {
                binding.tvResult.setText("Access Denied");
            }
        } else {
            binding.tvResult.setText("ID too short");
        }
    }
}