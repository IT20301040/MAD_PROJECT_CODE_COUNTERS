package com.example.kottupaymentmethod;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class ViewDetails extends AppCompatActivity {
    EditText CardNumber, CardHolderName, ExpireDate, CW;
    Button Change, Remove, AddNewCard;

    ArrayList<PaymentHelperClass> list;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_details);


        recyclerView=findViewById(R.id.RecycleView);
        list=new ArrayList<PaymentHelperClass>();

        MyAdapter myAdapter=new MyAdapter(getApplicationContext(),list);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        readSavedCardList();

        AddNewCard = findViewById(R.id.btnAddNewCard);

        //navigation
        AddNewCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewDetails.this, CardDetails.class);
                startActivity(intent);
            }
        });
    }


    void readSavedCardList(){

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("CardDetails");



        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                HashMap<String, Object> allData = (HashMap<String, Object>) snapshot.getValue();
                String[] allKey = allData.keySet().toArray(new String[0]);

                list.clear();

                for (DataSnapshot dataSnapshot1:snapshot.getChildren()) {
                    String key=dataSnapshot1.getKey();
                    PaymentHelperClass v = dataSnapshot1.getValue(PaymentHelperClass.class);


                    list.add(v);
                }

                MyAdapter myAdapter=new MyAdapter(getApplicationContext(),list);
                recyclerView.setAdapter(myAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(getApplicationContext(),"Something is wrong...!",Toast.LENGTH_LONG).show();

            }
        });


    }

}