package com.teamachievers.medix.Model;

import com.google.firebase.firestore.PropertyName;

public class doctorsModel {

    @PropertyName("name")
    public String doctor_name;
    @PropertyName("img")
    public String doctor_img;

    public String getDoctorName() {
        return doctor_name;
    }

    public String getDoctorImage() {
        return doctor_img;
    }
}
