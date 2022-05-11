package com.example.order;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.example.order.Card;
import com.example.order.cheeseModel;
import com.example.order1.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Order_Dolpin extends AppCompatActivity {

    //ImageView imageView;
    //TextView textView;
    EditText Product_Name;
    EditText Price;
    EditText DeliverCharges;
    EditText Quantity;

    //String Product_Name,Price,DeliverCharges,Quantity;
    Button Add_To_Card, BUY_NOW;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Product_Name = findViewById(R.id.Pname);
        Price = findViewById(R.id.editTextNumber2);
        DeliverCharges = findViewById(R.id.editTextNumber10);
        Quantity = findViewById(R.id.editTextNumber);
        Add_To_Card = findViewById(R.id.button8);
        BUY_NOW = findViewById(R.id.buy);

        //Save data in FireBase on button click
        Add_To_Card.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("order");

                //Get all the values

                String Product = Product_Name.getEditableText().toString();
                String PPrice = Price.getEditableText().toString();
                String DCharges = DeliverCharges.getEditableText().toString();
                String CQuantity = Quantity.getEditableText().toString();

                // validation section
                if (Product.isEmpty() || PPrice.isEmpty() || DCharges.isEmpty() || CQuantity.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please fill all field!",
                            Toast.LENGTH_LONG).show();
                } else {
                    cheeseModel helperClass = new cheeseModel(Product, PPrice, DCharges, CQuantity);

                    reference.child("Product").setValue(helperClass);

                    Toast.makeText(getApplicationContext(), "Order details saved!",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        //navigation
        Add_To_Card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(com.example.order.Order_Dolpin.this, Card.class);
                startActivity(intent);
            }
        });
    }
}