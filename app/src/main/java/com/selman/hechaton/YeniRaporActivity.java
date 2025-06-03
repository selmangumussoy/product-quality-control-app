package com.selman.hechaton;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

public class YeniRaporActivity extends AppCompatActivity {

    BarChart chart1, chart2, chart3, chart4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yeni_rapor);

        chart1 = findViewById(R.id.chart1);
        chart2 = findViewById(R.id.chart2);
        chart3 = findViewById(R.id.chart3);
        chart4 = findViewById(R.id.chart4);

        // Renkli grafik çağrıları
        setupBarChart(chart1,
                new float[]{20f, 30f, 15f},
                "Yırtık / Leke / Katlanma",
                "Zaman: 31.05.2025 – 15:15",
                new int[]{R.color.red_light, R.color.blue, R.color.green_light});

        setupBarChart(chart2,
                new float[]{5f, 10f, 20f},
                "Yırtık Artışı",
                "Zaman: 31.05.2025 – 13:48",
                new int[]{R.color.red_light, R.color.red_light, R.color.red_light});

        setupBarChart(chart3,
                new float[]{12f, 25f, 10f},
                "Leke Artışı",
                "Zaman: 31.05.2025 – 11:20",
                new int[]{R.color.blue, R.color.blue, R.color.blue});

        setupBarChart(chart4,
                new float[]{10f, 14f, 11f},
                "Katlanma Sorunu",
                "Zaman: 31.05.2025 – 16:40",
                new int[]{R.color.green_light, R.color.green_light, R.color.green_light});
    }

    private void setupBarChart(BarChart chart, float[] values, String label, String desc, int[] colors) {
        ArrayList<BarEntry> entries = new ArrayList<>();
        for (int i = 0; i < values.length; i++) {
            entries.add(new BarEntry(i, values[i]));
        }

        BarDataSet dataSet = new BarDataSet(entries, label);
        dataSet.setColors(colors, this);
        BarData data = new BarData(dataSet);
        data.setBarWidth(0.9f);

        chart.setData(data);
        Description description = new Description();
        description.setText(desc);
        chart.setDescription(description);
        chart.setFitBars(true);
        chart.invalidate();
    }
}
