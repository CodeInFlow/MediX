package com.teamachievers.medix.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.teamachievers.medix.Model.clinicsModel;
import com.teamachievers.medix.R;

import java.util.ArrayList;

public class clinicsAdapter extends RecyclerView.Adapter<clinicsAdapter.ViewHolder> {

    private ArrayList<clinicsModel> clinicsArrayList;
    private Context context;

    public clinicsAdapter(ArrayList<clinicsModel> clinicsArrayList, Context context) {
        this.clinicsArrayList = clinicsArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public clinicsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.card_clinic, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull clinicsAdapter.ViewHolder holder, int position) {
        clinicsModel model = clinicsArrayList.get(position);

        holder.clinicName.setText(model.getClinicName());
        Picasso.get().load(model.getClinicImage()).into(holder.clinicImage);

    }

    @Override
    public int getItemCount() {
        return clinicsArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView clinicName;
        ImageView clinicImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            clinicName = itemView.findViewById(R.id.clinic_name);
            clinicImage = itemView.findViewById(R.id.clinic_image);

        }
    }
}
