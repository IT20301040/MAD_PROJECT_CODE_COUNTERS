package com.example.location1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    TextInputLayout lProvince, lDistrict, lCity, lAddress;
    Button save, next;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lProvince = findViewById(R.id.inp_provice);
        lDistrict = findViewById(R.id.inp_district);
        lCity = findViewById(R.id.inp_city);
        lAddress = findViewById(R.id.inp_address);
        save = findViewById(R.id.btn_save);
        next = findViewById(R.id.btn_next);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("location");

                //get all the values

                String province = lProvince.getEditText().getText().toString();
                String district = lDistrict.getEditText().getText().toString();
                String city = lCity.getEditText().getText().toString();
                String address = lAddress.getEditText().getText().toString();

                UserHelperClass helperClass = new UserHelperClass(province, district, city,address);

                reference.child(city).setValue(helperClass);

            }
        });

        //navigation
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,update.class);

                String province = lProvince.getEditText().getText().toString();
                String district = lDistrict.getEditText().getText().toString();
                String city = lCity.getEditText().getText().toString();
                String address = lAddress.getEditText().getText().toString();

                intent.putExtra("province",province);
                intent.putExtra("district",district);
                intent.putExtra("city",city);
                intent.putExtra("address",address);


                startActivity(intent);
            }
        });

    }

}