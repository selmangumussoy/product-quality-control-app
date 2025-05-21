package com.selman.hechaton;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.selman.hechaton.model.Ped;
import com.selman.hechaton.util.JSONUtils;
import java.util.List;

public class PedDetailActivity extends AppCompatActivity {

    TextView pedIdText, defectRateText, colorDefectText, stainPresentText, cutDefectText, structuralIssueText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ped_detail);

        pedIdText = findViewById(R.id.ped_id_text);
        defectRateText = findViewById(R.id.defect_rate_text);
        colorDefectText = findViewById(R.id.color_defect_text);
        stainPresentText = findViewById(R.id.stain_present_text);
        cutDefectText = findViewById(R.id.cut_defect_text);
        structuralIssueText = findViewById(R.id.structural_issue_text);

        // Ped ID’yi al
        String pedId = getIntent().getStringExtra("ped");

        // JSON’dan tüm pedleri yükle ve bu ID’ye ait olanı bul
        List<Ped> pedList = JSONUtils.loadPedData(this);
        Ped selected = null;
        for (Ped p : pedList) {
            if (p.id.equals(pedId)) {
                selected = p;
                break;
            }
        }

        if (selected != null) {
            pedIdText.setText("Ped ID: " + selected.id);
            defectRateText.setText("Defect Rate: " + selected.defect_rate + "%");

            colorDefectText.setText("Color Defect: " + (selected.color_defect ? "✔️" : "❌"));
            stainPresentText.setText("Stain Present: " + (selected.stain_present ? "✔️" : "❌"));
            cutDefectText.setText("Cut Defect: " + (selected.cut_defect ? "✔️" : "❌"));
            structuralIssueText.setText("Structural Issue: " + (selected.structural_issue ? "✔️" : "❌"));
        }
    }
}

