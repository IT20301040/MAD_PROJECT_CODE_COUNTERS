package com.example.login2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class register extends AppCompatActivity {

    TextInputLayout regName, regMail, regUname, regPassword, regConNo;
    Button btn_register, btn_prof;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //hooks
        regName = findViewById(R.id.R_name);
        regMail = findViewById(R.id.R_email);
        regUname = findViewById(R.id.R_uname);
        regPassword = findViewById(R.id.R_pw);
        regConNo = findViewById(R.id.R_cnumber);
        btn_register = findViewById(R.id.btn_regis);
        btn_prof = findViewById(R.id.btn_profile);


//--------------create---------------

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("users");

                //get all the values
                String name = regName.getEditText().getText().toString();
                String email = regMail.getEditText().getText().toString();
                String username = regUname.getEditText().getText().toString();
                String password = regPassword.getEditText().getText().toString();
                String contactNo = regConNo.getEditText().getText().toString();

                UserHelperClass helperClass = new UserHelperClass(name, email, username, password, contactNo);
                reference.child(username).setValue(helperClass);


            }
        });

        btn_prof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(register.this, userProfile.class);
                startActivity(intent);
            }
        });
    }

    private Boolean validateName() {

        String val = regName.getEditText().getText().toString();

        if (val.isEmpty()) {
            regName.setError("Empty field");
            return false;
        } else {
            regName.setError(null);
            regName.setErrorEnabled(false);
            return true;
        }
    }
    private Boolean validateMail() {

        String val = regMail.getEditText().getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (val.isEmpty()) {
            regMail.setError("Empty field");
            return false;
        }else if (!val.matches(emailPattern)){
            regMail.setError("invalid E-mail address");
            return false;
        }

        else {
            regMail.setError(null);
            regMail.setErrorEnabled(false);
            return true;
        }
    }
    private Boolean validateUname() {

        String val = regUname.getEditText().getText().toString();

        if (val.isEmpty()) {
            regUname.setError("Empty field");
            return false;
        } else {
            regUname.setError(null);
            regUname.setErrorEnabled(false);
            return true;
        }
    }
    private Boolean validatePw() {

        String val = regPassword.getEditText().getText().toString();
        String passwordVal = "^"+"(?=.*[a-zA-Z])"+"(?=.[@#$%^&+=])"+"(?=.\\S+$)"+".(4,)"+"$";

        if (val.isEmpty()) {
            regPassword.setError("Empty field");
            return false;
        }else if (!val.matches(passwordVal)){
            regPassword.setError("Password is too weak");
            return false;
        }

        else {
            regPassword.setError(null);
            regPassword.setErrorEnabled(false);
            return true;
        }
    }
    private Boolean validateContact() {

        String val = regConNo.getEditText().getText().toString();

        if (val.isEmpty()) {
            regConNo.setError("Empty field");
            return false;
        } else {
            regConNo.setError(null);
            regConNo.setErrorEnabled(false);
            return true;
        }
    }

    public void registerUser(View view) {

        if(!validateName() |  !validateMail() | !validateUname() | !validatePw() | validateContact()){
            return;
        }

        String name = regName.getEditText().getText().toString();
        String email = regMail.getEditText().getText().toString();
        String username = regUname.getEditText().getText().toString();
        String password = regPassword.getEditText().getText().toString();
        String contactNo = regConNo.getEditText().getText().toString();
        UserHelperClass helperClass = new UserHelperClass(name, email, username, password, contactNo);
        reference.child(username).setValue(helperClass);

    }
}