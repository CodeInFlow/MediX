package com.teamachievers.medix;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;


public class dr_detail extends Fragment {

    private FirebaseFirestore db;
    ImageView img_dr;
    TextView drName;
    String a,b,refno;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=  inflater.inflate(R.layout.frag_dr_detail, container, false);
        refno =getArguments().get("/CT1/0/Doctors/0").toString();

        img_dr =v.findViewById(R.id.img_dr);
        drName =v.findViewById(R.id.drName);

        db = FirebaseFirestore.getInstance();
        getdata();

        return v;
    }

    private void getdata() {
        DocumentReference docRef = db.collection("DetailedArticle").document(refno);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document != null) {
                        a =document.getString("drImg");
                        b = document.getString("drName");
                        drName.setText(b);

                        Picasso.get().load(a).into(img_dr);


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