package com.thetrailblazers.solace;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class YogaAdapter extends RecyclerView.Adapter<YogaAdapter.YogaViewHolder> {
    public YogaAdapter(Context context, ArrayList<Yoga> yogaList) {
        this.context = context;
        this.yogaList = yogaList;
    }

    Context context;
    ArrayList<Yoga> yogaList;
    @NonNull
    @Override
    public YogaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.yogaitem, parent, false);
        return new YogaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull YogaViewHolder holder, int position) {

        Yoga yoga = yogaList.get(position);
        holder.name.setText(yoga.getName());
        holder.description.setText(yoga.getDescription());
    }

    @Override
    public int getItemCount() {
        return yogaList.size();
    }

    public static class YogaViewHolder extends RecyclerView.ViewHolder {

        TextView name, description;

        public YogaViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.yogaName);
            description = itemView.findViewById(R.id.yogaDescription);
        }
    }
}

