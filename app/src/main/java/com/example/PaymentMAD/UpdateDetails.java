package com.example.kottupaymentmethod;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UpdateDetails extends AppCompatActivity {

    EditText PhoneNumber,CardNumber,CardHolderName,ExpireDate,CW;

    String StPhoneNumber,StCardNumber, StCardHolderName,StExpireDate,StCW;


    Button update;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_details);

        PhoneNumber=findViewById(R.id.txt_PhoneNumberNumber);
        CardNumber=findViewById(R.id.txt_CardNumber);
        CardHolderName=findViewById(R.id.txt_CardHolderName);
        ExpireDate=findViewById(R.id.txt_ExpireDate);
        CW=findViewById(R.id.txt_CW);
        update=findViewById(R.id.btnChange);

        getData();

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                rootNode=FirebaseDatabase.getInstance();
                reference=rootNode.getReference("CardDetails");

                String Phone = PhoneNumber.getEditableText().toString();
                String Number = CardNumber.getEditableText().toString();
                String Name = CardHolderName.getEditableText().toString();
                String Date = ExpireDate.getEditableText().toString();
                String cw = CW.getEditableText().toString();


                if(Phone.isEmpty() || Number.isEmpty() || Name.isEmpty() ||Date.isEmpty() || cw.isEmpty() )
                {
                    Toast.makeText(getApplicationContext(), "Please fill all field!",
                            Toast.LENGTH_LONG).show();
                }
                else
                {
                    PaymentHelperClass helperClass = new PaymentHelperClass(Phone,Number,Name,Date,cw);

                    reference.child(Number).setValue(helperClass);

                    Toast.makeText(getApplicationContext(), "Card details updated!",
                            Toast.LENGTH_LONG).show();


                }

            }
        });







    }


    private void getData(){

        if(getIntent().hasExtra("CW"))
        {
            StPhoneNumber = getIntent().getStringExtra("PhoneNumber");
            StCardNumber = getIntent().getStringExtra("CardNumber");
            StCardHolderName = getIntent().getStringExtra("CardHolderName");
            StExpireDate = getIntent().getStringExtra("ExpireDate");
            StCW = getIntent().getStringExtra("CW");

            setData();
        }
        else
        {
            Toast.makeText(getApplicationContext(), "No Data!",
                    Toast.LENGTH_LONG).show();
        }

    }


    private void setData(){

        PhoneNumber.setText(StPhoneNumber);
        CardNumber.setText(StCardNumber);
        CardHolderName.setText(StCardHolderName);
        ExpireDate.setText(StExpireDate);
        CW.setText(StCW);

    }

}