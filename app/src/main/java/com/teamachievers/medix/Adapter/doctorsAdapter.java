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
import com.teamachievers.medix.Model.doctorsModel;
import com.teamachievers.medix.R;

import java.util.ArrayList;

public class doctorsAdapter extends RecyclerView.Adapter<doctorsAdapter.ViewHolder> {

    private ArrayList<doctorsModel> doctorsArrayList;
    private Context context;

    public doctorsAdapter(ArrayList<doctorsModel> doctorsArrayList, Context context) {
        this.doctorsArrayList = doctorsArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public doctorsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.card_doctor, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull doctorsAdapter.ViewHolder holder, int position) {
        doctorsModel model = doctorsArrayList.get(position);

        holder.doctorName.setText(model.getDoctorName());
        Picasso.get().load(model.getDoctorImage()).into(holder.doctorImage);

    }

    @Override
    public int getItemCount() {
        return doctorsArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView doctorName;
        ImageView doctorImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            doctorName = itemView.findViewById(R.id.doctor_name);
            doctorImage = itemView.findViewById(R.id.doctor_image);

        }
    }
}
