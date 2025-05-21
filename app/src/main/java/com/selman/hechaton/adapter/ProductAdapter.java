package com.selman.hechaton.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.selman.hechaton.R;
import com.selman.hechaton.model.Product;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.PedViewHolder> {

    private Context context;
    private List<Product> productList;
    private double threshold;

    public ProductAdapter(Context context, List<Product> productList, double threshold) {
        this.context = context;
        this.productList = productList;
        this.threshold = threshold;
    }

    @NonNull
    @Override
    public PedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_ped, parent, false);
        return new PedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PedViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.pedId.setText(product.id);
        holder.defectRate.setText("Defect: " + product.defect_rate + "%");

        // Renk ayarı (threshold'a göre)
        if (product.defect_rate > threshold) {
            holder.itemView.setBackgroundColor(context.getResources().getColor(android.R.color.holo_red_light));
        } else {
            holder.itemView.setBackgroundColor(context.getResources().getColor(android.R.color.holo_green_light));
        }

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, PedDetailActivity.class);
            intent.putExtra("ped", product.id);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class PedViewHolder extends RecyclerView.ViewHolder {
        TextView pedId, defectRate;

        public PedViewHolder(@NonNull View itemView) {
            super(itemView);
            pedId = itemView.findViewById(R.id.ped_id_text);
            defectRate = itemView.findViewById(R.id.defect_rate_text);
        }
    }
}

