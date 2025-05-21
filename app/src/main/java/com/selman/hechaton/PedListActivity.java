package com.selman.hechaton;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.selman.hechaton.adapter.PedAdapter;
import com.selman.hechaton.model.Ped;
import com.selman.hechaton.util.JSONUtils;
import java.util.List;

public class PedListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Ped> pedList;
    double threshold = 2.0; // örnek eşik değeri

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ped_list);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        pedList = JSONUtils.loadPedData(this);
        PedAdapter adapter = new PedAdapter(this, pedList, threshold);
        recyclerView.setAdapter(adapter);
    }
}

