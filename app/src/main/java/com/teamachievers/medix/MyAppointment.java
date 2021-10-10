package com.teamachievers.medix;


import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
    TextView dname,fname,age,prob,gender,pnum,myno,current;
    private FirebaseFirestore db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_my_appointment, container, false);

        cid=getArguments().getString("cid");
        did=getArguments().getString("did");
        dname = v.findViewById(R.id.dname);
        fname = v.findViewById(R.id.fullname);
        age = v.findViewById(R.id.age);
        prob = v.findViewById(R.id.problem);
        gender = v.findViewById(R.id.gender);
        pnum = v.findViewById(R.id.phoneno);
        myno = v.findViewById(R.id.t4);
        current = v.findViewById(R.id.t2);

        getdata();

        return v;
    }

    private void getdata(){

        db = FirebaseFirestore.getInstance();
        String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();


        DocumentReference documentReference = db.collection("Appointments").document("/"+currentuser+"/Clinics/"+cid+"/Doctors/"+did);
        DocumentReference documentReference2 = db.collection("queue").document("/0/clinics/"+cid+"/doctors/"+did+".1");

        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
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


                        dname.setText("Doctor Name : "+b);
                        fname.setText("Full Name : "+a);
                        pnum.setText("Phone Number : "+c);
                        prob.setText(d);
                        age.setText("Age : "+e);
                        gender.setText("Gender : "+f);
                        myno.setText("Your Appointment Number : 40");


                    } else {
                        Log.d("LOGGER", "Error");
                    }
                } else {
                    Log.d("LOGGER", "get failed with ", task.getException());
                }
            }
        });


        documentReference2.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document != null) {
                        j =document.getLong("Current");
                        current.setText("Ongoing Number : 20");

                    } else {
                        Log.d("LOGGER", "Error");
                    }
                } else {
                    Log.d("LOGGER", "get failed with ", task.getException());
                }
            }
        });


    }
}
