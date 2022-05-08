package com.example.kottupaymentmethod;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CardDetails extends AppCompatActivity {
    EditText PhoneNumber,CardNumber,CardHolderName,ExpireDate,CW;
    Button Save,View;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_details);

        PhoneNumber=findViewById(R.id.txt_PhoneNumber);
        CardNumber=findViewById(R.id.txt_CardNumber);
        CardHolderName=findViewById(R.id.txt_CardHolderName);
        ExpireDate=findViewById(R.id.txt_ExpireDate);
        CW=findViewById(R.id.txt_CW);
        Save=findViewById(R.id.btnSave);
        View=findViewById(R.id.btnView);

        //Save data in FireBase on button click
        Save.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rootNode=FirebaseDatabase.getInstance();
                reference=rootNode.getReference("CardDetails");

                //Get all the values

                String Phone = PhoneNumber.getEditableText().toString();
                String Number = CardNumber.getEditableText().toString();
                String Name = CardHolderName.getEditableText().toString();
                String Date = ExpireDate.getEditableText().toString();
                String cw = CW.getEditableText().toString();


                PaymentHelperClass helperClass = new PaymentHelperClass(Phone,Number,Name,Date,cw);

                reference.child(Number).setValue(helperClass);
            }
        });

        //navigation
        View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(CardDetails.this,ViewDetails.class);
                startActivity(intent);



            }
        });

    }
}