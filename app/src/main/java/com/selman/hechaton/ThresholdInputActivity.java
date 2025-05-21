package com.selman.hechaton;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;

public class ThresholdInputActivity extends AppCompatActivity {
    EditText etThreshold;
    MaterialButton btnContinue;

    public static double userThreshold = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_threshold_input);

        etThreshold = findViewById(R.id.etThreshold);
        btnContinue = findViewById(R.id.btnContinue);

        btnContinue.setOnClickListener(v -> {
            try {
                String input = etThreshold.getText().toString();
                userThreshold = Double.parseDouble(input);
                Intent intent = new Intent(ThresholdInputActivity.this, ResultDisplayActivity.class);
                startActivity(intent);
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Geçerli bir sayı giriniz.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
