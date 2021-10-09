package com.teamachievers.medix;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Locale;


public class Home extends Fragment implements View.OnClickListener {

TextView t_Surgeon,t_Psychiatrist,t_Neurologist,t_Dentist,t_General,t_Cardiologist;
ImageView i_Surgeon,i_Psychiatrist,i_Neurologist,i_Dentist,i_General,i_Cardiologist;

    Bundle bundle = new Bundle();

    private FirebaseFirestore db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.frag_home, container, false);

        v.findViewById(R.id.card_General).setOnClickListener(this);
        t_General = v.findViewById(R.id.card_General).findViewById(R.id.cate_name);
        t_General.setText("General");
        i_General =v.findViewById(R.id.card_General).findViewById(R.id.cate_img);
        i_General.setImageResource(R.drawable.general);

        v.findViewById(R.id.card_Surgeon).setOnClickListener(this);
        t_Surgeon = v.findViewById(R.id.card_Surgeon).findViewById(R.id.cate_name);
        t_Surgeon.setText("Surgeon");
        i_Surgeon =v.findViewById(R.id.card_Surgeon).findViewById(R.id.cate_img);
        i_Surgeon.setImageResource(R.drawable.surgeon);

        v.findViewById(R.id.card_Neurologist).setOnClickListener(this);
        t_Neurologist = v.findViewById(R.id.card_Neurologist).findViewById(R.id.cate_name);
        t_Neurologist.setText("Neurologist");
        i_Neurologist =v.findViewById(R.id.card_Neurologist).findViewById(R.id.cate_img);
        i_Neurologist.setImageResource(R.drawable.neurologist);

        v.findViewById(R.id.card_Psychiatrist).setOnClickListener(this);
        t_Psychiatrist = v.findViewById(R.id.card_Psychiatrist).findViewById(R.id.cate_name);
        t_Psychiatrist.setText("Psychiatrist");
        i_Psychiatrist =v.findViewById(R.id.card_Psychiatrist).findViewById(R.id.cate_img);
        i_Psychiatrist.setImageResource(R.drawable.psychiatrist);

        v.findViewById(R.id.card_Dentist).setOnClickListener(this);
        t_Dentist = v.findViewById(R.id.card_Dentist).findViewById(R.id.cate_name);
        t_Dentist.setText("Dentist");
        i_Dentist =v.findViewById(R.id.card_Dentist).findViewById(R.id.cate_img);
        i_Dentist.setImageResource(R.drawable.dentist);

        v.findViewById(R.id.card_Cardiologist).setOnClickListener(this);
        t_Cardiologist = v.findViewById(R.id.card_Cardiologist).findViewById(R.id.cate_name);
        t_Cardiologist.setText("Cardiologist");
        i_Cardiologist =v.findViewById(R.id.card_Cardiologist).findViewById(R.id.cate_img);
        i_Cardiologist.setImageResource(R.drawable.cardiologist);

        return v;
    }


    @Override
    public void onClick(View v) {
        Fragment fragment = new Clinics();
        switch (v.getId()){
            case R.id.card_General:
                bundle.putString("clinic_type", "CT1");
                break;
            case R.id.card_Dentist:
                bundle.putString("clinic_type", "CT2");
                break;
        }
        fragment.setArguments(bundle);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameContainer2, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();


    }
}