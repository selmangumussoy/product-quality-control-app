package com.selman.hechaton;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.button.MaterialButton;
import com.selman.hechaton.models.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportActivity extends AppCompatActivity {
    PieChart pieChart, pieChartDepartment, pieChartDaily, pieChartWeekly;
    MaterialButton btnOpenDetailedReport;
    TextView tvDailyInfo, tvWeeklyInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        pieChart = findViewById(R.id.pieChart);
        pieChartDepartment = findViewById(R.id.pieChartDepartment);
        pieChartDaily = findViewById(R.id.pieChartDaily);
        pieChartWeekly = findViewById(R.id.pieChartWeekly);
        tvDailyInfo = findViewById(R.id.tvDailyInfo);
        tvWeeklyInfo = findViewById(R.id.tvWeeklyInfo);
        btnOpenDetailedReport = findViewById(R.id.btnOpenDetailedReport);

        btnOpenDetailedReport.setOnClickListener(v -> {
            Intent intent = new Intent(ReportActivity.this, YeniRaporActivity.class);
            startActivity(intent);
        });

        List<Product> productList = ResultDisplayActivity.productListGlobal;

        int total = productList.size();
        int defectiveCount = 0;
        double totalDefectRate = 0.0;

        int colorIssues = 0;
        int stainIssues = 0;
        int cutIssues = 0;
        int structuralIssues = 0;

        Map<String, Integer> departmentErrors = new HashMap<>();

        for (Product p : productList) {
            totalDefectRate += p.getDefectRate();
            if (p.getDefectRate() >= ThresholdInputActivity.userThreshold) {
                defectiveCount++;

                if (p.isColorIssue()) {
                    colorIssues++;
                    departmentErrors.put("Boya", departmentErrors.getOrDefault("Boya", 0) + 1);
                }
                if (p.isStain()) {
                    stainIssues++;
                    departmentErrors.put("Paketleme", departmentErrors.getOrDefault("Paketleme", 0) + 1);
                }
                if (p.isCutIssue()) {
                    cutIssues++;
                    departmentErrors.put("Kesim", departmentErrors.getOrDefault("Kesim", 0) + 1);
                }
                if (p.isStructuralIssue()) {
                    structuralIssues++;
                    departmentErrors.put("Kalite", departmentErrors.getOrDefault("Kalite", 0) + 1);
                }
            }
        }

        int okCount = total - defectiveCount;

        // Genel Ürün Durumu
        ArrayList<PieEntry> pieEntries = new ArrayList<>();
        pieEntries.add(new PieEntry(okCount, "Hatasız"));
        pieEntries.add(new PieEntry(defectiveCount, "Hatalı"));

        PieDataSet pieDataSet = new PieDataSet(pieEntries, "Ürün Durumu");
        pieDataSet.setColors(new int[]{android.R.color.holo_green_light, android.R.color.holo_red_light}, this);
        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.setUsePercentValues(true);
        pieChart.setCenterText("Genel Ürün Durumu");
        Description pieDescription = new Description();
        pieDescription.setText("");
        pieChart.setDescription(pieDescription);
        pieChart.invalidate();

        // Departman Bazlı Hatalar
        ArrayList<PieEntry> departmentPieEntries = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : departmentErrors.entrySet()) {
            departmentPieEntries.add(new PieEntry(entry.getValue(), entry.getKey()));
        }

        PieDataSet depDataSet = new PieDataSet(departmentPieEntries, "Departman Hataları");
        depDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        depDataSet.setValueTextSize(12f);
        PieData depPieData = new PieData(depDataSet);
        pieChartDepartment.setData(depPieData);
        pieChartDepartment.setUsePercentValues(true);
        pieChartDepartment.setCenterText("Departman Bazlı Hatalar");
        Description depDesc = new Description();
        depDesc.setText("");
        pieChartDepartment.setDescription(depDesc);
        pieChartDepartment.invalidate();

        // Günlük Rapor
        ArrayList<PieEntry> daily = new ArrayList<>();
        daily.add(new PieEntry(5.8f, "Yırtık Hatalı"));
        daily.add(new PieEntry(94.2f, "Diğer"));
        PieDataSet dailySet = new PieDataSet(daily, "Günlük Hatalar");
        dailySet.setColors(getColor(R.color.red_light), getColor(R.color.green_light));
        PieData dailyData = new PieData(dailySet);
        pieChartDaily.setData(dailyData);
        pieChartDaily.setUsePercentValues(true);
        pieChartDaily.setCenterText("Günlük Rapor");
        pieChartDaily.setDescription(null);
        pieChartDaily.invalidate();

        // Haftalık Rapor
        ArrayList<PieEntry> weekly = new ArrayList<>();
        weekly.add(new PieEntry(3, "Yırtık Hatalı"));
        weekly.add(new PieEntry(97, "Diğer"));
        PieDataSet weeklySet = new PieDataSet(weekly, "Haftalık Hatalar");
        weeklySet.setColors(getColor(R.color.orange), getColor(R.color.blue));
        PieData weeklyData = new PieData(weeklySet);
        pieChartWeekly.setData(weeklyData);
        pieChartWeekly.setUsePercentValues(true);
        pieChartWeekly.setCenterText("Haftalık Rapor");
        pieChartWeekly.setDescription(null);
        pieChartWeekly.invalidate();

        // Açıklamalar
        tvDailyInfo.setText("Bugünkü yırtık hata oranı (%5.8), haftalık ortalamanın (%2.1) çok üzerinde.\n" +
                "Bu artış özellikle öğleden sonraki üretimlerde yoğunlaşıyor.\n" +
                "Gerekirse, 13:00 sonrası üretilen ürünler kalite kontrol sürecinden geçirilmeli.\n" +
                "Aynı ürünlerde hem asimetri hem katlanma hataları tespit edildi.\n" +
                "Bu durum, taşıma sistemi ile pres makineleri arasında senkronizasyon sorunu olabileceğini gösteriyor.\n" +
                "Üretim hattı 2 ile ilgili zamanlama parametreleri yeniden gözden geçirilmeli.\n" +
                "Gerekiyorsa presleme süresi artırılabilir.");

        tvWeeklyInfo.setText("Makine M-07 tarafından işlenen ürünlerde 3. kez yırtık hatası tespit edildi.\n" +
                "Önceki bakım raporlarında aynı makinede benzer sorunlar raporlanmıştı.\n" +
                "Önlem alınmazsa, bu arıza kronik hale gelebilir.\n" +
                "Makine önleyici bakıma alınmalı.");
    }

}