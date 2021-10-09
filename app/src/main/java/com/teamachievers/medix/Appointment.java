package com.teamachievers.medix;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.SimpleTimeZone;


public class Appointment extends Fragment {


    TextView tv;
    Button b1,b2,b3,b4;
    private FirebaseFirestore db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_appointment, container, false);


        tv = v.findViewById(R.id.tv1);
        b1 = v.findViewById(R.id.date1);

        Calendar calendar = Calendar. getInstance();
        SimpleDateFormat monthFormat = new SimpleDateFormat("MMM, yyyy");
        String month = monthFormat.format(calendar.getTime());
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd");
        String date = dateFormat.format(calendar.getTime());

        tv.setText(month);
        b1.setText(Integer.valueOf(date)+1);



        return v;
    }

    private void addDataToFirestore() {

        db = FirebaseFirestore.getInstance();
        String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();
        //DocumentReference dbCourses = db.collection("").document();
        CollectionReference collectionReference = db.collection("/Appointments/7999969395/100001/");

        dbCourses.set(data)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {



                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });

    }


}
