package com.thetrailblazers.solace;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TherapistAdapter extends RecyclerView.Adapter<TherapistAdapter.TherapistViewHolder> {
    public TherapistAdapter(Context context, ArrayList<Therapist> therapistList) {
        this.context = context;
        this.therapistList = therapistList;
    }

    Context context;
    ArrayList<Therapist> therapistList;
    @NonNull
    @Override
    public TherapistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new TherapistViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TherapistViewHolder holder, int position) {

        Therapist therapist = therapistList.get(position);
        holder.fullName.setText(therapist.getFullName());
        holder.email.setText(therapist.getEmail());
        holder.specialty.setText(therapist.getSpecialty());
        holder.pronouns.setText(therapist.getPronouns());
        holder.phone.setText(therapist.getPhone());
    }

    @Override
    public int getItemCount() {
        return therapistList.size();
    }

    public static class TherapistViewHolder extends RecyclerView.ViewHolder {

        TextView fullName, email, specialty, pronouns, phone;

        public TherapistViewHolder(@NonNull View itemView) {
            super(itemView);
            fullName = itemView.findViewById(R.id.name);
            email = itemView.findViewById(R.id.email);
            specialty = itemView.findViewById(R.id.specialty);
            pronouns = itemView.findViewById(R.id.pronouns);
            phone = itemView.findViewById(R.id.phone);
        }
    }
}
