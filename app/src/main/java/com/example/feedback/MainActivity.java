package com.example.feedback;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    EditText email;
    EditText  feedback;


    Button sendbtn , viewbtn;


    FirebaseDatabase rootNode;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.get_emailhome);
        feedback = findViewById(R.id.get_feedbackhome);

        sendbtn = findViewById(R.id.btn_getfeedback);
        viewbtn = findViewById(R.id.btn_viewfeedback);

        //Save data in FireBase on button click
        sendbtn.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("feedback");


                //Get all the values

                String Email = email.getEditableText().toString();
                String Feedback = feedback.getEditableText().toString();


                if (Email.isEmpty() || Feedback.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please fill all field!",
                            Toast.LENGTH_LONG).show();
                } else {
                    DBHelperClass helperClass = new DBHelperClass(Email,Feedback);

                    reference.child(Email).setValue(helperClass);

                    Toast.makeText(getApplicationContext(), "feedback saved!",
                            Toast.LENGTH_LONG).show();

                }


            }
        });

        viewbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,viewfeedback.class);
                startActivity(intent);
            }
        });

    }

}
