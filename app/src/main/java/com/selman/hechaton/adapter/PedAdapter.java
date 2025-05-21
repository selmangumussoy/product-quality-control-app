package com.selman.hechaton.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.selman.hechaton.PedDetailActivity;
import com.selman.hechaton.R;
import com.selman.hechaton.model.Ped;
import java.util.List;

public class PedAdapter extends RecyclerView.Adapter<PedAdapter.PedViewHolder> {

    private Context context;
    private List<Ped> pedList;
    private double threshold;

    public PedAdapter(Context context, List<Ped> pedList, double threshold) {
        this.context = context;
        this.pedList = pedList;
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
        Ped ped = pedList.get(position);
        holder.pedId.setText(ped.id);
        holder.defectRate.setText("Defect: " + ped.defect_rate + "%");

        // Renk ayarı (threshold'a göre)
        if (ped.defect_rate > threshold) {
            holder.itemView.setBackgroundColor(context.getResources().getColor(android.R.color.holo_red_light));
        } else {
            holder.itemView.setBackgroundColor(context.getResources().getColor(android.R.color.holo_green_light));
        }

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, PedDetailActivity.class);
            intent.putExtra("ped", ped.id);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return pedList.size();
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

