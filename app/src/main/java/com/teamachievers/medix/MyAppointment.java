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


    String cid,did;
    private FirebaseFirestore db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_my_appointment, container, false);

        cid=getArguments().getString("cid");
        did=getArguments().getString("did");

        /*drName =v.findViewById(R.id.drName);
        drProf=v.findViewById(R.id.txt_prf);
        drDetail=v.findViewById(R.id.txt_dr_detail);
        drTime=v.findViewById(R.id.txt_time);
        nos_pt=v.findViewById(R.id.nos_Patients);
        nos_Exp=v.findViewById(R.id.Exp);*/

        db = FirebaseFirestore.getInstance();
        String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();


        DocumentReference documentReference = db.collection("Appointments").document("/"+currentuser+"/Clinics/"+cid+"/Doctors/"+did);
        DocumentReference documentReference1 = db.collection("queue").document("/0/clinics/"+cid+"/doctors/"+did);

        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document != null) {
                      /*  a =document.getString("img");
                        b = document.getString("name");
                        c = document.getString("prof");
                        d = document.getString("desc");
                        e = document.getString("time");
                        f = document.getString("nos_p");
                        g = document.getString("exp");*/


                       /* drProf.setText(c);
                        drDetail.setText(d);
                        drName.setText(b);
                        drTime.setText(e);
                        nos_pt.setText(f);
                        nos_Exp.setText(g);

                        Picasso.get().load(a).into(img_dr);
*/

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
