package com.selman.hechaton.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.selman.hechaton.R;
import com.selman.hechaton.models.Product;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private final Context context;
    private final List<Product> productList;
    private final double threshold;

    public ProductAdapter(Context context, List<Product> productList, double threshold) {
        this.context = context;
        this.productList = productList;
        this.threshold = threshold;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.id.setText(product.id);
        holder.defectRate.setText("Defect: " + product.defect_rate + "%");

        int color = product.defect_rate > threshold ? android.R.color.holo_red_light : android.R.color.holo_green_light;
        holder.itemView.setBackgroundColor(context.getResources().getColor(color));
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView id, defectRate;
        public ViewHolder(View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.product_id);
            defectRate = itemView.findViewById(R.id.product_defect);
        }
    }
}
