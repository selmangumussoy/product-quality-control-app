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
        holder.defectRate.setText("Hata Oranı: %" + product.defect_rate);

        // Hata detaylarını metinleştir
        StringBuilder defects = new StringBuilder();
        if (product.color_defect) defects.append("Renk farkı, ");
        if (product.stain_present) defects.append("Leke, ");
        if (product.cut_defect) defects.append("Kesim hatası, ");
        if (product.structural_issue) defects.append("Yapısal bozukluk, ");

        String defectsText = defects.length() > 0
                ? "Hatalar: " + defects.substring(0, defects.length() - 2)  // sondaki virgülü sil
                : "Hatalar: yok";
        holder.defects.setText(defectsText);

        // Durum renklendirme
        boolean isDefective = product.defect_rate > threshold;
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
