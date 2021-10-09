package com.teamachievers.medix.Model;

import com.google.firebase.firestore.PropertyName;

public class clinicsModel {

    @PropertyName("name")
    public String clinic_img;
    @PropertyName("img")
    public String clinic_name;

    public String getClinicName() {
        return clinic_name;
    }

    public String getClinicImage() {
        return clinic_img;
    }

}
