package com.teamachievers.medix.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.teamachievers.medix.Doctors;
import com.teamachievers.medix.Model.clinicsModel;
import com.teamachievers.medix.R;
import com.teamachievers.medix.Schedule1_D;

import java.util.ArrayList;

public class scheduleAdapter extends RecyclerView.Adapter<scheduleAdapter.ViewHolder> {

    private ArrayList<clinicsModel> scheduleArrayList;
    private Context context;

    public scheduleAdapter(ArrayList<clinicsModel> scheduleArrayList, Context context) {
        this.scheduleArrayList = scheduleArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public scheduleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.card_clinic, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull scheduleAdapter.ViewHolder holder, int position) {
        clinicsModel model = scheduleArrayList.get(position);

        holder.clinicName.setText(model.getClinicName());
        Picasso.get().load(model.getClinicImage()).into(holder.clinicImage);
        holder.clinicImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new Schedule1_D();
                Bundle bundle = new Bundle();
                FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                bundle.putString("cid", String.valueOf(position));
                fragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.frameContainer2, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });



}

    @Override
    public int getItemCount() {
        return scheduleArrayList.size();
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
