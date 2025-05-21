package com.selman.hechaton;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;

public class ReportActivity extends AppCompatActivity {
    MaterialButton btnExport;
    TextView tvExportStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        btnExport = findViewById(R.id.btnExportReport);
        tvExportStatus = findViewById(R.id.tvExportStatus);

        btnExport.setOnClickListener(v -> {
            // Sahte başarı mesajı
            tvExportStatus.setText("Rapor başarıyla dışa aktarıldı.");
        });
    }
}
