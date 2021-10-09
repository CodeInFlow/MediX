package com.teamachievers.medix;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class MyAppointment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_my_appointment, container, false);

        cid=getArguments().getString("cid");
        did=getArguments().getString("did");

        drName =v.findViewById(R.id.drName);
        drProf=v.findViewById(R.id.txt_prf);
        drDetail=v.findViewById(R.id.txt_dr_detail);
        drTime=v.findViewById(R.id.txt_time);
        nos_pt=v.findViewById(R.id.nos_Patients);
        nos_Exp=v.findViewById(R.id.Exp);


        return v;
    }

}
