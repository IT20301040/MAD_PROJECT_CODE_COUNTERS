package com.example.order;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.example.order1.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

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
                Intent intent= new Intent(MainActivity.this,Card.class);
                startActivity(intent);
            }
        });
    }
}

        //cheeseQty = (EditText)findViewById(R.id.editTextNumber);

        //data retrieve
     //databaseReference =FirebaseDatabase.getInstance().child("Category");
//        databaseReference = FirebaseDatabase.getInstance().getReference().child("Category");
//
//         Add_To_Card =(Button)findViewById(R.id.button8);
//
//        Add_To_Card.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Product_Name ="Cheese Kottu";
//                Price = String.valueOf(750);
//                DeliverCharges =String.valueOf(200);
//                cheeseQty = (EditText)findViewById(R.id.editTextNumber);
//
//                Quantity = String.valueOf(cheeseQty);
//
//                Bundle resultsBundle = new Bundle();
//                resultsBundle.putString("Product_Name",Product_Name);
//                resultsBundle.putString("Price",Price);
//                resultsBundle.putString("Quantity",String.valueOf(cheeseQty));
//
//                Intent getDetailsIntent = new Intent(com.example.order.MainActivity.this,com.example.order.Card.class);
//                getDetailsIntent.putExtras(resultsBundle);
//                startActivity(getDetailsIntent);
//
//                uploadFile();
//            }
//        });
//    }
//
//    private void uploadFile(){
//
//        com.example.order.cheeseModel
//                puddingModel = new com.example.order.cheeseModel(
//                Product_Name,Price,DeliverCharges,Quantity
//        );
//
//        String uploadId = databaseReference.push().getKey();
//        databaseReference.child(uploadId).setValue(puddingModel);
//        Toast.makeText( com.example.order.MainActivity.this, "Upload Success", Toast.LENGTH_SHORT).show();
//
//    }
//}



