package com.example.location;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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

        //---------insert---------------

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


                if(province.isEmpty() || district.isEmpty() || city.isEmpty() ||address.isEmpty() )
                {
                    Toast.makeText(getApplicationContext(), "Please fill all field!",
                            Toast.LENGTH_LONG).show();
                }
                else
                {
                    UserHelperClass helperClass = new UserHelperClass(province, district, city,address);

                    reference.child(address).setValue(helperClass);

                    Toast.makeText(getApplicationContext(), "Location added Successfully!",
                            Toast.LENGTH_LONG).show();

                }

            }
        });

        //-------------------navigation---------------
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this,ViewLocation.class);
                startActivity(intent);
            }
        });

    }

}