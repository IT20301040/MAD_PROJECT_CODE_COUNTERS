package com.example.location;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UpdateLocation extends AppCompatActivity {

    TextInputLayout Province;
    TextInputLayout District;
    TextInputLayout City;
    TextInputLayout Address;

    String Lo_Province,Lo_District, Lo_City,Lo_Address;

    Button update;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_location);

        Province=findViewById(R.id.inp_provice);
        District=findViewById(R.id.inp_district);
        City=findViewById(R.id.inp_city);
        Address=findViewById(R.id.inp_address);

        update=findViewById(R.id.btn_continue);


        getData();

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                rootNode=FirebaseDatabase.getInstance();
                reference=rootNode.getReference("location");

                String province = Province.getEditText().toString();
                String district = District.getEditText().toString();
                String city = City.getEditText().toString();
                String address = Address.getEditText().toString();


                if(province.isEmpty() || district.isEmpty() || city.isEmpty() ||address.isEmpty())
                {
                    Toast.makeText(getApplicationContext(), "Fill all field!",
                            Toast.LENGTH_LONG).show();
                }
                else
                {
                    UserHelperClass helperClass = new UserHelperClass(province,district,city,address);

                    reference.child(address).setValue(helperClass);

                    Toast.makeText(getApplicationContext(), "Location updated!",
                            Toast.LENGTH_LONG).show();


                }

            }
        });
    }

    private void getData() {
        if(getIntent().hasExtra("Address"))
        {
            Lo_Province = getIntent().getStringExtra("Province");
            Lo_District = getIntent().getStringExtra("District");
            Lo_City = getIntent().getStringExtra("City");
            Lo_Address = getIntent().getStringExtra("Address");

            setData();
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Data is Same!",
                    Toast.LENGTH_LONG).show();
        }
    }

    private void setData() {
        Province.getEditText().setText(Lo_Province);
        District.getEditText().setText(Lo_District);
        City.getEditText().setText(Lo_City);
        Address.getEditText().setText(Lo_Address);
    }
}