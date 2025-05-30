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

        holder.id.setText("Ürün ID: " + product.id);
        holder.defectRate.setText("Hata Oranı: %" + product.defectRate);

        StringBuilder defects = new StringBuilder();
        if (product.isColorIssue) defects.append("Renk farkı, ");
        if (product.isStain) defects.append("Leke, ");
        if (product.isCutIssue) defects.append("Kesim hatası, ");
        if (product.isStructuralIssue) defects.append("Yapısal bozukluk, ");

        holder.defects.setText(defects.length() > 0
                ? "Hatalar: " + defects.substring(0, defects.length() - 2)
                : "Hatalar: yok");

        boolean isDefective = product.defectRate > threshold;
        int bgColor = context.getResources().getColor(
                isDefective ? android.R.color.holo_red_light : android.R.color.holo_green_light
        );
        holder.status.setText(isDefective ? "HATALI" : "HATASIZ");
        holder.itemView.setBackgroundColor(bgColor);
    }


    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView id, defectRate, defects, status;

        public ViewHolder(View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.product_id);
            defectRate = itemView.findViewById(R.id.product_defect);
            defects = itemView.findViewById(R.id.product_defects);
            status = itemView.findViewById(R.id.product_status);
        }
    }
}
