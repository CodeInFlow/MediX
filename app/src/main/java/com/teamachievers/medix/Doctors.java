package com.teamachievers.medix;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
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
import com.teamachievers.medix.Adapter.doctorsAdapter;
import com.teamachievers.medix.Model.clinicsModel;
import com.teamachievers.medix.Model.doctorsModel;

import java.util.ArrayList;
import java.util.List;


public class Doctors extends Fragment {



    private RecyclerView doctorsRV;
    private ArrayList<doctorsModel> doctorsArrayList;
    private doctorsAdapter doctorsRVAdapter;
    private FirebaseFirestore db;
    String cid = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_doctors, container, false);


            cid=getArguments().getString("cid");
            Log.i("Working", cid);


            doctorsRV = v.findViewById(R.id.rv_doctors);
            db = FirebaseFirestore.getInstance();

            doctorsArrayList = new ArrayList<>();
            doctorsRV.setHasFixedSize(true);
            doctorsRV.setLayoutManager(new GridLayoutManager(getActivity(),2));

            doctorsRVAdapter = new doctorsAdapter(doctorsArrayList, getActivity());

            doctorsRV.setAdapter(doctorsRVAdapter);

            db.collection("CT1/"+cid+"/Doctors").get()
                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            if (!queryDocumentSnapshots.isEmpty()) {
                                List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                                for (DocumentSnapshot d : list) {
                                    doctorsModel c = d.toObject(doctorsModel.class);

                                    doctorsArrayList.add(c);
                                }
                                doctorsRVAdapter.notifyDataSetChanged();
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
