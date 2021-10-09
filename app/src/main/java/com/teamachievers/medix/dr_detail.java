package com.teamachievers.medix;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
    TextView drName ,drProf,drDetail,drTime,nos_pt,nos_Exp;
    String a,b,c,d,e,f,g,cid,did;
    Button submit;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=  inflater.inflate(R.layout.frag_dr_detail, container, false);

        cid=getArguments().getString("cid");
        did=getArguments().getString("did");

        img_dr =v.findViewById(R.id.img_dr);
        drName =v.findViewById(R.id.drName);
        drProf=v.findViewById(R.id.txt_prf);
        drDetail=v.findViewById(R.id.txt_dr_detail);
        drTime=v.findViewById(R.id.txt_time);
        nos_pt=v.findViewById(R.id.nos_Patients);
        nos_Exp=v.findViewById(R.id.Exp);

        submit = v.findViewById(R.id.btn_bookappointment);

        db = FirebaseFirestore.getInstance();
        getdata();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment fragment = new Appointment();
                Bundle bundle = new Bundle();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                bundle.putString("cid", cid);
                bundle.putString("cid", did);
                bundle.putString("drname",drName.toString());
                fragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.frameContainer2, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });


        return v;
    }

    private void getdata() {
        DocumentReference docRef = db.collection("CT1").document("/"+cid+"/Doctors/"+did);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document != null) {
                        a =document.getString("img");
                        b = document.getString("name");
                        c = document.getString("prof");
                        d = document.getString("desc");
                        e = document.getString("time");
                        f = document.getString("nos_p");
                        g = document.getString("exp");


                        drProf.setText(c);
                        drDetail.setText(d);
                        drName.setText(b);
                        drTime.setText(e);
                        nos_pt.setText(f);
                        nos_Exp.setText(g);

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