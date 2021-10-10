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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.teamachievers.medix.Adapter.doctorsAdapter;
import com.teamachievers.medix.Adapter.scheduleDAdapter;
import com.teamachievers.medix.Model.doctorsModel;

import java.util.ArrayList;
import java.util.List;


public class Schedule1_D extends Fragment {


    private RecyclerView scheduleDRV;
    private ArrayList<doctorsModel> scheduleDArrayList;
    private scheduleDAdapter scheduleDRVAdapter;
    private FirebaseFirestore db;
    String cid = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.frag_schedule1__d, container, false);

        cid=getArguments().getString("cid");


        scheduleDRV = v.findViewById(R.id.rv_schedule1_D);
        db = FirebaseFirestore.getInstance();

        scheduleDArrayList = new ArrayList<>();
        scheduleDRV.setHasFixedSize(true);
        scheduleDRV.setLayoutManager(new GridLayoutManager(getActivity(),2));

        scheduleDRVAdapter = new scheduleDAdapter(scheduleDArrayList, getActivity());

        scheduleDRV.setAdapter(scheduleDRVAdapter);
        String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();

        db.collection("/Appointments/5tarKbV9M8afjQ7iEaSrTfxqjgC2/Clinics/"+cid+"/Doctors/").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if (!queryDocumentSnapshots.isEmpty()) {
                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                            for (DocumentSnapshot d : list) {
                                doctorsModel c = d.toObject(doctorsModel.class);

                                scheduleDArrayList.add(c);
                            }
                            scheduleDRVAdapter.notifyDataSetChanged();
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
