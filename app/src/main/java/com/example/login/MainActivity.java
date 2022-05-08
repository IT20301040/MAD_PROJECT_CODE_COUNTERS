package com.example.login2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    EditText username, password;
    Button Regi, Signin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.L_username);
        password = findViewById(R.id.L_pw);
        Regi = findViewById(R.id.btn_reg);
        Signin = findViewById(R.id.btn_signin);



        Regi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, register.class);
                startActivity(intent);
            }
        });
    }

    private Boolean validateUser() {

        String val = username.getText().toString();

        if (val.isEmpty()) {
            username.setError("Empty field");
            Toast.makeText(this, "Invalid user", Toast.LENGTH_LONG).show();
            return false;
        } else {
            username.setError(null);
            return true;
        }
    }
    private Boolean validatePassword() {

        String val = password.getText().toString();

        if (val.isEmpty()) {
            password.setError("Empty field");
            return false;
        }
        else {
            password.setError(null);
            return true;
        }
    }

    public void loginUser(View view) {

        if(!validateUser() |  !validatePassword()){
            return;
        }
        else {
            isUser();
        }



    }

    private void isUser() {

        String userEnteredUsername = username.getText().toString().trim();
        String userEnteredPassword = password.getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");

        Query checkUser = reference.orderByChild("username").equalTo(userEnteredUsername);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists()){

                    username.setError(null);

                    String passwordFromDB = snapshot.child(userEnteredUsername).child("password").getValue(String.class);

                    if (passwordFromDB.equals(userEnteredPassword)){

                        username.setError(null);

                        String nameFromDB = snapshot.child(userEnteredUsername).child("regName").getValue(String.class);
                        String emailFromDB = snapshot.child(userEnteredUsername).child("regMail").getValue(String.class);
                        String usernameFromDB = snapshot.child(userEnteredUsername).child("regUname").getValue(String.class);
                        String contactFromDB = snapshot.child(userEnteredUsername).child("regConNo").getValue(String.class);

                        Intent intent = new Intent(getApplicationContext(),userProfile.class);

                        intent.putExtra("name",nameFromDB);
                        intent.putExtra("email",emailFromDB);
                        intent.putExtra("username",usernameFromDB);
                        intent.putExtra("password",passwordFromDB);
                        intent.putExtra("contactNo",contactFromDB);

                        startActivity(intent);

                    }
                    else {
                        password.setError("Wrong Password");
                        password.requestFocus();
                    }
                }
                else{
                    username.setError("No such User exist");
                    username.requestFocus();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });
    }
}