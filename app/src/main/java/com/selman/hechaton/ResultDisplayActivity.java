package com.selman.hechaton;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.selman.hechaton.adapter.ProductAdapter;
import com.selman.hechaton.models.Product;

import java.util.ArrayList;
import java.util.List;

public class ResultDisplayActivity extends AppCompatActivity {

    public static List<Product> productListGlobal;
    RecyclerView rvProductList;
    List<Product> productList = new ArrayList<>();
    ProductAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_display);

        rvProductList = findViewById(R.id.rvProductList);
        rvProductList.setLayoutManager(new LinearLayoutManager(this));

        // Ürünleri oluştur (dummy veri)
        productList.add(new Product("1", 3.4, true, true, false, false));
        productList.add(new Product("2", 0.5, true, false, false, false));
        productList.add(new Product("3", 5.1, true, false, true, true));

        productListGlobal = productList;
        // %0 kontrolü özel
        if (ThresholdInputActivity.userThreshold == 0.0) {
            boolean hasZeroDefect = false;
            for (Product p : productList) {
                if (p.defectRate == 0.0) {
                    hasZeroDefect = true;
                    break;
                }
            }
            if (!hasZeroDefect) {
                Toast.makeText(this, "Yüzde 0 hatalı ürün bulunmamaktadır", Toast.LENGTH_LONG).show();
            }
        }

        // Tüm ürünler gösterilecek
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
