package com.selman.hechaton;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.selman.hechaton.models.Product;
import java.util.List;

public class ReportActivity extends AppCompatActivity {

    TextView tvSummary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        tvSummary = findViewById(R.id.tvExportStatus); // XML'deki textview ile eşleşmeli

        // Simülasyon amaçlı; normalde bu verileri intent veya global listeden almalısın
        List<Product> productList = ResultDisplayActivity.productListGlobal; // <-- buradan alabilirsin

        int total = productList.size();
        int defectiveCount = 0;
        double totalDefectRate = 0.0;

        int colorIssues = 0;
        int stainIssues = 0;
        int cutIssues = 0;
        int structuralIssues = 0;

        for (Product p : productList) {
            totalDefectRate += p.getDefectRate();
            if (p.getDefectRate() > 0) {
                defectiveCount++;

                if (p.isColorIssue()) colorIssues++;
                if (p.isStain()) stainIssues++;
                if (p.isCutIssue()) cutIssues++;
                if (p.isStructuralIssue()) structuralIssues++;
            }
        }

        int okCount = total - defectiveCount;
        double avgDefectRate = total > 0 ? totalDefectRate / total : 0;

        String report = "📊 Ürün Analiz Raporu\n\n" +
                "Toplam ürün sayısı: " + total + "\n" +
                "✅ Hatasız ürün sayısı: " + okCount + "\n" +
                "❌ Hatalı ürün sayısı: " + defectiveCount + "\n\n" +
                "Ortalama hata oranı: %" + String.format("%.2f", avgDefectRate) + "\n\n" +
                "En sık karşılaşılan hatalar:\n" +
                "- Renk farkı: " + colorIssues + " ürün\n" +
                "- Leke: " + stainIssues + " ürün\n" +
                "- Kesim hatası: " + cutIssues + " ürün\n" +
                "- Yapısal bozukluk: " + structuralIssues + " ürün";

        tvSummary.setText(report);
    }
}

