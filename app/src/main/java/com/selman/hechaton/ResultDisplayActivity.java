package com.selman.hechaton;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.selman.hechaton.adapter.ProductAdapter;
import com.selman.hechaton.models.Product;

import java.util.ArrayList;
import java.util.List;

public class ResultDisplayActivity extends AppCompatActivity {

    RecyclerView rvProductList;
    TextView tvStats;
    List<Product> productList;
    ProductAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_display);

        // Bağlantılar
        tvStats = findViewById(R.id.tvStats);
        rvProductList = findViewById(R.id.rvProductList);
        rvProductList.setLayoutManager(new LinearLayoutManager(this));

        // Ürün listesi
        productList = new ArrayList<>();
        productList.add(new Product("1", 3.4, true, true, false, false));
        productList.add(new Product("2", 0.5, false, false, false, false));
        productList.add(new Product("3", 5.1, false, false, true, true));

        // İstatistikleri hesapla
        int hatali = 0, hatasiz = 0;
        for (Product p : productList) {
            if (p.defect_rate > ThresholdInputActivity.userThreshold) {
                hatali++;
            } else {
                hatasiz++;
            }
        }

        String statsText = "Toplam Ürün: " + productList.size() +
                "\nHatalı: " + hatali +
                "\nHatasız: " + hatasiz;

        tvStats.setText(statsText);

        // RecyclerView bağla
        adapter = new ProductAdapter(this, productList, ThresholdInputActivity.userThreshold);
        rvProductList.setAdapter(adapter);
    }
}
