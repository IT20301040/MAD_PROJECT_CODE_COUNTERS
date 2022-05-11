package com.example.feedback;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Updatefeedback extends AppCompatActivity {


    EditText Email, Feedback;

    String Stemail,Stfeedback;


    Button update;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatefeedback);





            Email=findViewById(R.id.get_email1);
        Feedback=findViewById(R.id.get_feedback2);
        update=findViewById(R.id.btnedit2);


            getData();

            update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    rootNode=FirebaseDatabase.getInstance();
                    reference=rootNode.getReference("feedback");

                    String email = Email.getEditableText().toString();
                    String feedback = Feedback.getEditableText().toString();



                    if(email.isEmpty() || feedback.isEmpty()  )
                    {
                        Toast.makeText(getApplicationContext(), "Please fill all field!",
                                Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        DBHelperClass helperClass = new DBHelperClass(email,feedback);

                        reference.child(email).setValue(helperClass);

                        Toast.makeText(getApplicationContext(), "Feed back updated!",
                                Toast.LENGTH_LONG).show();


                    }

                }
            });







        }


        private void getData(){

            if(getIntent().hasExtra("email"))
            {
                Stemail  = getIntent().getStringExtra("Email");
                Stfeedback = getIntent().getStringExtra("Feedback");


                setData();
            }
            else
            {
                Toast.makeText(getApplicationContext(), "No Data!",
                        Toast.LENGTH_LONG).show();
            }

        }


        private void setData(){

            Email.setText(Stemail);
            Feedback.setText(Stfeedback);


        }

    }

