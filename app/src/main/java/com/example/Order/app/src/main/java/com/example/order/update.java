package com.example.order;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.order1.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class update extends AppCompatActivity {

    EditText Product_Name, Price,DeliverCharges,Quantity;

    String editTextNumb1,editTextNumber2,editTextNumber10,cheeseQty;

    Button update;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Product_Name=findViewById(R.id.Pname);
        Price=findViewById(R.id.editTextNumber2);
        DeliverCharges=findViewById(R.id.editTextNumber10);
        Quantity=findViewById(R.id.editTextNumber);
        update=findViewById(R.id.Update);

        getData();

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                rootNode=FirebaseDatabase.getInstance();
                reference=rootNode.getReference("CardDetails");

                String Product = Product_Name.getEditableText().toString();
                String PPrice = Price.getEditableText().toString();
                String DCharges = DeliverCharges.getEditableText().toString();
                String CQuantity = Quantity.getEditableText().toString();



                if(Product.isEmpty() || PPrice.isEmpty() || DCharges.isEmpty() ||CQuantity.isEmpty())
                {
                    Toast.makeText(getApplicationContext(), "Please fill all field!",
                            Toast.LENGTH_LONG).show();
                }
                else
                {
                    cheeseModel helperClass = new cheeseModel(Product, PPrice, DCharges, CQuantity);

                    reference.child(Product).setValue(helperClass);

                    Toast.makeText(getApplicationContext(), "Order Details updated!",
                            Toast.LENGTH_LONG).show();


                }

            }
        });

    }


    private void getData(){

        if(getIntent().hasExtra("Product_Name"))
        {
            editTextNumb1 = getIntent().getStringExtra("Product_Name");
            editTextNumber2 = getIntent().getStringExtra("Price");
            editTextNumber10 = getIntent().getStringExtra("DeliverCharges");
            cheeseQty = getIntent().getStringExtra("Quantity");

            setData();
        }
        else
        {
            Toast.makeText(getApplicationContext(), "No Data!",
                    Toast.LENGTH_LONG).show();
        }

    }


    private void setData(){

        Product_Name.setText(editTextNumb1);
        Price.setText(editTextNumber2);
        DeliverCharges.setText(editTextNumber10);
        Quantity.setText(cheeseQty);


    }

}