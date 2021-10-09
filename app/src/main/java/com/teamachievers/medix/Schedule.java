package com.teamachievers.medix;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.teamachievers.medix.Adapter.clinicsAdapter;
import com.teamachievers.medix.Adapter.scheduleAdapter;
import com.teamachievers.medix.Model.clinicsModel;

import java.util.ArrayList;
import java.util.List;


public class Schedule extends Fragment {


    private RecyclerView scheduleRV;
    private ArrayList<clinicsModel> scheduleArrayList;
    private scheduleAdapter scheduleRVAdapter;
    private FirebaseFirestore db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_schedule, container, false);

        scheduleRV = v.findViewById(R.id.rv_schedule);
        db = FirebaseFirestore.getInstance();

        scheduleArrayList = new ArrayList<>();
        scheduleRV.setHasFixedSize(true);
        scheduleRV.setLayoutManager(new GridLayoutManager(getActivity(),2));

        scheduleRVAdapter = new scheduleAdapter(scheduleArrayList, getActivity());

        scheduleRV.setAdapter(scheduleRVAdapter);

        db.collection("/Appointments/7999969395/Clinics").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if (!queryDocumentSnapshots.isEmpty()) {
                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                            for (DocumentSnapshot d : list) {
                                clinicsModel c = d.toObject(clinicsModel.class);

                                scheduleArrayList.add(c);
                            }
                            scheduleRVAdapter.notifyDataSetChanged();
                        } else {

                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity(), "Fail to get the data.", Toast.LENGTH_SHORT).show();
            }
        });

        return v;
    }
}
