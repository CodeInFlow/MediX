package com.teamachievers.medix;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
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
import com.teamachievers.medix.Model.clinicsModel;

import java.util.ArrayList;
import java.util.List;


public class Clinics extends Fragment {

    private RecyclerView clinicsRV;
    private ArrayList<clinicsModel> clinicsArrayList;
    private clinicsAdapter clinicsRVAdapter;
    private FirebaseFirestore db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.frag_clinics, container, false);

//        String dept_link = getArguments().getString("clinic_type");


        clinicsRV = v.findViewById(R.id.rv_clinics);
        db = FirebaseFirestore.getInstance();

        clinicsArrayList = new ArrayList<>();
        clinicsRV.setHasFixedSize(true);
        clinicsRV.setLayoutManager(new LinearLayoutManager(getContext()));

        clinicsRVAdapter = new clinicsAdapter(clinicsArrayList, getActivity());

        clinicsRV.setAdapter(clinicsRVAdapter);

        db.collection("Clinics").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if (!queryDocumentSnapshots.isEmpty()) {
                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                            for (DocumentSnapshot d : list) {
                                clinicsModel c = d.toObject(clinicsModel.class);

                                clinicsArrayList.add(c);
                            }
                            clinicsRVAdapter.notifyDataSetChanged();
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
