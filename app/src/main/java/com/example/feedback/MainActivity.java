package com.example.feedback;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    EditText email, feedback;
    Button btn_feedback;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email =findViewById(R.id.add_email1);
        feedback =findViewById(R.id.add_feedbak);
        btn_feedback =findViewById(R.id.btn_feedback);


        btn_feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this,feedlist.class);
                startActivity(intent);
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("feedback");
                
                //get all the values
                
                String Email = email.getEditableText().toString();
                String Feedback = feedback.getEditableText().toString();
                
                DBHelperClass helperClass = new DBHelperClass(email ,feedback);

                reference.setValue(helperClass);
            }
        });
    }
}