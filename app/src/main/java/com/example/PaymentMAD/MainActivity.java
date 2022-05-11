package com.example.kottupaymentmethod;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity {
    RadioButton CardPayment,CashOnDelivery;
    Button Ok,Cancel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CardPayment=findViewById(R.id.rgCardPayment);
        CashOnDelivery=findViewById(R.id.rgCashOnDelivery);
        Ok=findViewById(R.id.btnOK);
        Cancel=findViewById(R.id.btnCancel);


        //navigation
        Ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(CardPayment.isChecked())
                {
                    Intent intent= new Intent(MainActivity.this,CardDetails.class);
                    startActivity(intent);
                }

                else
                {

                }

            }
        });


    }
    }
