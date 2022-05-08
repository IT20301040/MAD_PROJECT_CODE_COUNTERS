package com.example.order;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    EditText Quantity,Price,DelieveryCharges,Total;
    Button Add_To_Card,BUY_NOW;

    FirebaseDatabase rootNode;
    DatabaseReference reference;
    //private Object Cart;

//    EditText editTextNumber;
//    EditText editTextNumber2;
//    EditText editTextNumber3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Add_To_Card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this,Card.class);
                startActivity(intent);
            }
        });

        //data retrieve
        BUY_NOW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Card.class);

                String Quantity = Oquantity.getEditableText().toString();
                String  Price = OPrice.getEditableText().toString();
                String DelieveryCharges = ODelieveryCharges.getEditableText().toString();
                String Total = OTotal.getEditableText().toString();

                intent.putExtra("Quantity",Quantity);
                intent.putExtra("Price ",Price );
                intent.putExtra("DelieveryCharges",DelieveryCharges);
                intent.putExtra("Total ",Total );


                startActivity(intent);
            }
        });
//        editTextNumber =findViewById(R.id.editTextNumber);
//        editTextNumber2 =findViewById(R.id.editTextNumber2);
//        editTextNumber3 =findViewById(R.id.editTextNumber3);

        Quantity = findViewById(R.id.editTextNumber);
        Price = findViewById(R.id.editTextNumber2);
        DelieveryCharges = findViewById(R.id.editTextNumber10);
        Total= findViewById(R.id.editTextTotal);
        Add_To_Card = findViewById(R.id.button8);


        Add_To_Card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("order");

                //get all the values
                String Oquantity=Quantity.getEditableText().toString();
                String OPrice = Price.getEditableText().toString();
                String ODelieveryCharges= DelieveryCharges.getEditableText().toString();
                String OTotal =  Total.getEditableText().toString();

                UserHelperClass helperClass = new UserHelperClass(Oquantity,OPrice,ODelieveryCharges,OTotal);

                reference.child(Oquantity).setValue(helperClass);


            }
        });

    }
}