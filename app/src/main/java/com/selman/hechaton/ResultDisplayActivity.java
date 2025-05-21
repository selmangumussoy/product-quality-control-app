package com.selman.hechaton;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.google.android.material.button.MaterialButton;
import com.selman.hechaton.adapter.ProductAdapter;
import com.selman.hechaton.models.AppDatabase;
import com.selman.hechaton.models.Product;

import java.util.ArrayList;
import java.util.List;

public class ResultDisplayActivity extends AppCompatActivity {


    public static List<Product> productListGlobal;
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
        productList.add(new Product("1","", 3.4, true, true, false, false));
        productList.add(new Product("2","", 0.5, true, false, false, false));
        productList.add(new Product("3","", 5.1, true, false, true, true));
        productListGlobal = productList;

        // İstatistikleri hesapla
        int hatali = 0, hatasiz = 0;
        for (Product p : productList) {
            if (p.defectRate > ThresholdInputActivity.userThreshold) {
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

        Toast.makeText(this, "Toplam " + productList.size() + " ürün listelendi.", Toast.LENGTH_SHORT).show();

        MaterialButton btnOpenReport = findViewById(R.id.btnOpenReport);
        btnOpenReport.setOnClickListener(v -> {
            Intent intent = new Intent(ResultDisplayActivity.this, ReportActivity.class);
            startActivity(intent);
        });
    }
}
