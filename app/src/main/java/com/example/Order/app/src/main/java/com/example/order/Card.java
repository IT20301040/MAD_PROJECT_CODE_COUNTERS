package com.example.order;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.order1.R;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

ArrayList<cheeseModel> list;
RecyclearView recyclerView;

public class Card extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     setContentView(R.layout.activity_card);

     recyclearView = findViewById(R.id.RecycleView);
     list= new ArrayList<cheeseModel>();

     MyAdapter myAdapter = new MyAdapter(getApplicationContext(),list);
     recyclearView.setAdapter(myAdapter);
     recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

     readSavedCardList();
        Update = findViewById(R.id.Update);

     //navigation
        Update.setOnClickListener(new View.onClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Card.this, MainActivity.class);
                startActivity(intent);
            }

        });
}

    void readSavedCardList(){

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("MainActivity");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                HashMap<String, Object> allData = (HashMap<String, Object>) snapshot.getValue();
                String[] allKey = allData.keySet().toArray(new String[0]);

                list.clear();

                for (DataSnapshot dataSnapshot1:snapshot.getChildren()) {
                    String key=dataSnapshot1.getKey();
                   cheeseModel v = dataSnapshot1.getValue(cheeseModel.class);


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

            Remove.setOnClickListener(new View.OnClickListener()){
                @Override
                public void onClick(View view) {

                    cheeseModel helperclass = new cheeseModel(key,Product_Name,Price,DeliverCharges,Quantity);
                    helperclass.Remove(key,Product_Name,Price,DeliverCharges,Quantity.getText().toString());

                    Toast.makeText(MainActivity.this"Order deleted" ,Toast.LENGTH_SHORT).show();
                }
        });


    }