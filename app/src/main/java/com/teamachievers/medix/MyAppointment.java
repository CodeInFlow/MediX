package com.teamachievers.medix;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;


public class MyAppointment extends Fragment {


    String cid,did,a,b,c,d,e,f,g;
    Long h,i,j,k;
    private FirebaseFirestore db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_my_appointment, container, false);

        cid=getArguments().getString("cid");
        did=getArguments().getString("did");


        db = FirebaseFirestore.getInstance();
        String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();


        DocumentReference documentReference = db.collection("Appointments").document("/"+currentuser+"/Clinics/"+cid+"/Doctors/"+did);
        DocumentReference documentReference1 = db.collection("queue").document("/0/clinics/"+cid+"/doctors/"+did);
        DocumentReference documentReference2 = db.collection("queue").document("/0/clinics/"+cid+"/doctors/"+did+".1");

        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document != null) {
                        a =document.getString("fullname");
                        b = document.getString("name");
                        c = document.getString("phone");
                        d = document.getString("desc");
                        e = document.getString("age");
                        f = document.getString("gender");
                        g = document.getString("date");
                        h = document.getLong("Total");




                    } else {
                        Log.d("LOGGER", "Error");
                    }
                } else {
                    Log.d("LOGGER", "get failed with ", task.getException());
                }
            }
        });

        documentReference1.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document != null) {
                        i =document.getLong("Total");

                    } else {
                        Log.d("LOGGER", "Error");
                    }
                } else {
                    Log.d("LOGGER", "get failed with ", task.getException());
                }
            }
        });

        documentReference2.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document != null) {
                        j =document.getLong("Current");

                        k = h-j;

                    } else {
                        Log.d("LOGGER", "Error");
                    }
                } else {
                    Log.d("LOGGER", "get failed with ", task.getException());
                }
            }
        });



        return v;
    }

}
