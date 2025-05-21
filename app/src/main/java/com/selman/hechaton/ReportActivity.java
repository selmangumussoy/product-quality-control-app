package com.selman.hechaton;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.selman.hechaton.models.Product;
import java.util.List;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import java.util.ArrayList;


public class ReportActivity extends AppCompatActivity {

    PieChart pieChart;
    BarChart barChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        pieChart = findViewById(R.id.pieChart);
        barChart = findViewById(R.id.barChart);

        List<Product> productList = ResultDisplayActivity.productListGlobal;

        int total = productList.size();
        int defectiveCount = 0;
        double totalDefectRate = 0.0;

        int colorIssues = 0;
        int stainIssues = 0;
        int cutIssues = 0;
        int structuralIssues = 0;

        for (Product p : productList) {
            totalDefectRate += p.getDefectRate();
            if (p.getDefectRate() >= ThresholdInputActivity.userThreshold) {
                defectiveCount++;

                if (p.isColorIssue()) colorIssues++;
                if (p.isStain()) stainIssues++;
                if (p.isCutIssue()) cutIssues++;
                if (p.isStructuralIssue()) structuralIssues++;
            }
        }

        int okCount = total - defectiveCount;

        // Pie Chart: Hatalı vs Hatasız
        ArrayList<PieEntry> pieEntries = new ArrayList<>();
        pieEntries.add(new PieEntry(okCount, "Hatasız"));
        pieEntries.add(new PieEntry(defectiveCount, "Hatalı"));

        PieDataSet pieDataSet = new PieDataSet(pieEntries, "Ürün Durumu");
        pieDataSet.setColors(new int[]{android.R.color.holo_green_light, android.R.color.holo_red_light}, this);
        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);

        Description pieDescription = new Description();
        pieDescription.setText("Genel Ürün Durumu");
        pieChart.setDescription(pieDescription);
        pieChart.invalidate();

        pieEntries.add(new PieEntry(okCount, "Hatasız"));
        pieEntries.add(new PieEntry(defectiveCount, "Hatalı"));
        // Bar Chart: Hata Türleri
        ArrayList<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(0, colorIssues));
        barEntries.add(new BarEntry(1, stainIssues));
        barEntries.add(new BarEntry(2, cutIssues));
        barEntries.add(new BarEntry(3, structuralIssues));

        BarDataSet barDataSet = new BarDataSet(barEntries, "Hata Türleri");
        barDataSet.setColors(new int[]{
                android.R.color.holo_orange_light,
                android.R.color.holo_blue_light,
                android.R.color.holo_purple,
                android.R.color.holo_red_dark
        }, this);

        BarData barData = new BarData(barDataSet);
        barChart.setData(barData);

        Description barDescription = new Description();
        barDescription.setText("Tespit Edilen Hata Türleri");
        barChart.setDescription(barDescription);
        barChart.getXAxis().setDrawLabels(false); // etiketler sabit kalmasın
        barChart.invalidate();
    }
}


