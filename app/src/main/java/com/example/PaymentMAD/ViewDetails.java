package com.example.kottupaymentmethod;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ViewDetails extends AppCompatActivity {
    EditText CardNumber, CardHolderName, ExpireDate, CW;
    Button Change, Remove, AddNewCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_details);

        CardNumber = findViewById(R.id.txt_CardNumber);
        CardHolderName = findViewById(R.id.txt_CardHolderName);
        ExpireDate = findViewById(R.id.txt_ExpireDate);
        CW = findViewById(R.id.txt_CW);
        Change = findViewById(R.id.btnChange);
        Remove = findViewById(R.id.btnRemove);
        AddNewCard = findViewById(R.id.btnadd);

        //navigation
        AddNewCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewDetails.this, CardDetails.class);
                startActivity(intent);
            }
        });
    }
}