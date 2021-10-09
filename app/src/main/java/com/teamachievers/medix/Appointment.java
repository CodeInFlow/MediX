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
    String cid,did,drname;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_appointment, container, false);


        cid=getArguments().getString("cid");
        did=getArguments().getString("did");
        drname=getArguments().getString("drname");

        tv = v.findViewById(R.id.tv1);
        b1 = v.findViewById(R.id.date1);
        b2 = v.findViewById(R.id.date1);
        b3 = v.findViewById(R.id.date1);
        b4 = v.findViewById(R.id.date1);

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
                    data.put("name", name.getText().toString());
                    data.put("number", phone.getText().toString());
                    data.put("age", age.getText().toString());
                    addDataToFirestore();
                }
            }
        });





        return v;
    }

    private void addDataToFirestore() {

        db = FirebaseFirestore.getInstance();
        String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();
        //DocumentReference dbCourses = db.collection("").document();
        CollectionReference collectionReference = db.collection("/Appointments/7999969395/Clinics/"+cid+"/Doctors/"+did+"/appointment/");

        collectionReference.add(data)
                .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {

                        Toast.makeText(getActivity(),"Submited",Toast.LENGTH_SHORT).show();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });

    }


}
