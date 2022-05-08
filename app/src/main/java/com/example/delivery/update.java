package com.example.location1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class update extends AppCompatActivity {

    TextInputLayout lProvince, lDistrict, lCity, lAddress;

    //Global variables
    String Lo_province, Lo_district, Lo_city, Lo_address;

    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        reference = FirebaseDatabase.getInstance().getReference("location");

        //hooks
        lProvince = findViewById(R.id.inp_provice);
        lDistrict = findViewById(R.id.inp_district);
        lCity = findViewById(R.id.inp_city);
        lAddress = findViewById(R.id.inp_address);


 //show all data
        showAllDate();

    }

    private void showAllDate() {

        Intent intent = getIntent();
        String Lo_province = intent.getStringExtra("province");
        String Lo_district = intent.getStringExtra("district");
        String Lo_city = intent.getStringExtra("city");
        String Lo_address = intent.getStringExtra("address");

        lProvince.getEditText().setText(Lo_province);
        lDistrict.getEditText().setText(Lo_district);
        lCity.getEditText().setText(Lo_city);
        lAddress.getEditText().setText(Lo_address);
    }

    //-------------------update------------
    public void onUpdate(View view){

        if (isProvinceChanged() || isDistrictChanged() || isCityChanged() || isAddressChanged()){
            Toast.makeText(this, "Data has Changed", Toast.LENGTH_LONG).show();
        }
        else Toast.makeText(this, "Data same", Toast.LENGTH_LONG).show();
    }

    private boolean isAddressChanged() {
        if (!Lo_address.equals(lAddress.getEditText().getText().toString())){

            reference.child(Lo_address).child("address").setValue(lAddress.getEditText().getText().toString());
            Lo_address = lAddress.getEditText().getText().toString();
            return true;
        }
        else {
            return false;
        }
    }

    private boolean isCityChanged() {
        if (!Lo_city.equals(lCity.getEditText().getText().toString())){

            reference.child(Lo_city).child("city").setValue(lCity.getEditText().getText().toString());
            Lo_city = lCity.getEditText().getText().toString();
            return true;
        }
        else {
            return false;
        }
    }

    private boolean isDistrictChanged() {
        if (!Lo_district.equals(lDistrict.getEditText().getText().toString())){

            reference.child(Lo_district).child("district").setValue(lDistrict.getEditText().getText().toString());
            Lo_district = lDistrict.getEditText().getText().toString();
            return true;
        }
        else {
            return false;
        }
    }

    private boolean isProvinceChanged() {

        if (!Lo_province.equals(lProvince.getEditText().getText().toString())){

            reference.child(Lo_province).child("province").setValue(lProvince.getEditText().getText().toString());
            Lo_province = lProvince.getEditText().getText().toString();
            return true;
        }
        else {
            return false;
        }
    }


}