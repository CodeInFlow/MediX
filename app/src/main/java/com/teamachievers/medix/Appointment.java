package com.teamachievers.medix;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.SimpleTimeZone;


public class Appointment extends Fragment {


    TextView tv;
    Button b1,b2,b3,b4,setAppointment;
    EditText name,phone,age,desc;
    private FirebaseFirestore db;
    Map<String, Object> data = new HashMap<>();
    Map<String, Object> data1 = new HashMap<>();
    String cid,did,drname,drimg;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_appointment, container, false);


        cid=getArguments().getString("cid");
        did=getArguments().getString("did");
        drname=getArguments().getString("drname");
        drimg=getArguments().getString("drimg");

        tv = v.findViewById(R.id.tv1);
        b1 = v.findViewById(R.id.date1);
        b2 = v.findViewById(R.id.date2);
        b3 = v.findViewById(R.id.date3);
        b4 = v.findViewById(R.id.date4);

        name = v.findViewById(R.id.fullname);
        phone = v.findViewById(R.id.phoneno);
        age = v.findViewById(R.id.age);

        Calendar calendar = Calendar. getInstance();
        SimpleDateFormat monthFormat = new SimpleDateFormat("MMM, yyyy");
        String month = monthFormat.format(calendar.getTime());
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd");
        String date = dateFormat.format(calendar.getTime());

        tv.setText(month);
        b1.setText("11");
        b2.setText("12");
        b3.setText("13");
        b4.setText("14");

        setAppointment = v.findViewById(R.id.set_appointment);
        setAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (phone.getText().toString().length() != 10) {
                    phone.setError("Enter a valid mobile");
                    phone.requestFocus();
                }
                if (name.getText().toString().isEmpty()) {
                    name.setError("Enter a valid Name");
                    name.requestFocus();
                }
                if (age.getText().toString().isEmpty()) {
                    age.setError("Enter a proper age");
                    age.requestFocus();
                } else {
                    data.put("fullname", name.getText().toString());
                    data.put("number", phone.getText().toString());
                    data.put("age", age.getText().toString());
                    data.put("name", drname);
                    data.put("img", drimg);
                    addDataToFirestore();
                }
            }
        });





        return v;
    }

    private void addDataToFirestore() {

        db = FirebaseFirestore.getInstance();
        String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();

        DocumentReference documentReference = db.collection("Appointments").document("/"+currentuser+"/Clinics/"+cid+"/Doctors/"+did);
        DocumentReference documentReference1 = db.collection("queue").document("/0/clinics/"+cid+"/doctors/"+did);

        documentReference1.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document != null) {
                        Long total = document.getLong("Total");
                        data.put("mynum", total);
                        data1.put("Total",total+1);

                        documentReference1.set(data1)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {

                                        Toast.makeText(getActivity(), "Your Appointment Number is : "+total, Toast.LENGTH_SHORT).show();

                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {

                                    }
                                });


                        documentReference.set(data)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {

                                        Toast.makeText(getActivity(), "Submitted", Toast.LENGTH_SHORT).show();

                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {

                                    }
                                });


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
