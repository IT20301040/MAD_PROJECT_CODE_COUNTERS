package com.example.order;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.android.material.textfield.TextInputLayout;
    public class update extends AppCompatActivity {
        TextInputLayout Quantity,Price,DelieveryCharges,Total;
        //Global variables
        String OQuantity,OPrice,ODelieveryCharges,OTotal;
        DatabaseReference reference;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_update);
            reference = FirebaseDatabase.getInstance().getReference("location");
            //hooks
            OQuantity = findViewById(R.id.inp_Quantity);
            OPrice = findViewById(R.id.inp_Price);
            ODelieveryCharges = findViewById(R.id.inp_DelieveryCharges);
            OTotal = findViewById(R.id.inp_Total);
            //show all data
            showAllDate();
        }
        private void showAllDate() {
            Intent intent = getIntent();
            String OQuantity = intent.getStringExtra("Quantity");
            String OPrice = intent.getStringExtra("Price");
            String ODelieveryCharges = intent.getStringExtra("DelieveryCharges");
            String OTotal = intent.getStringExtra("Total");

    }
