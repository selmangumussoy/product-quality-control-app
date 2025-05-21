package com.selman.hechaton;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.selman.hechaton.adapter.ProductAdapter;
import com.selman.hechaton.models.Product;

import java.util.ArrayList;
import java.util.List;

public class ResultDisplayActivity extends AppCompatActivity {
    RecyclerView rvProductList;
    List<Product> productList = new ArrayList<>();
    ProductAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_display);

        rvProductList = findViewById(R.id.rvProductList);
        rvProductList.setLayoutManager(new LinearLayoutManager(this));

        // Geçici veriler (sunucu yerine)
        productList.add(new Product(1, new String[]{"leke"}, 3.4));
        productList.add(new Product(2, new String[]{}, 0.5));
        productList.add(new Product(3, new String[]{"kesim hatası"}, 5.1));

        adapter = new ProductAdapter(this, productList, ThresholdInputActivity.userThreshold);
        rvProductList.setAdapter(adapter);

        Toast.makeText(this, "Toplam " + productList.size() + " ürün listelendi.", Toast.LENGTH_SHORT).show();
    }
}
