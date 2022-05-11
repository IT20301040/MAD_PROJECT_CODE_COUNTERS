package com.example.kottupaymentmethod;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class VerifyPhoneNumber extends AppCompatActivity {
    String verificationCodeBySystem;
    Button verify_btn;
    EditText txt_PhoneNumber;
    ProgressBar progressBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_phone_number);

        verify_btn = findViewById(R.id.verify_btn);
        txt_PhoneNumber = findViewById(R.id.txt_PhoneNumber);
        progressBar = findViewById(R.id.progressBar);

        String Phone = getIntent().getStringExtra("Phone");
        sendVerificationCodeToUser(Phone);

    }

    private void sendVerificationCodeToUser(String phone) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();



    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onCodeSent( String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);

            verificationCodeBySystem = s;
        }

        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

            String code = phoneAuthCredential.getSmsCode();
            if(code!=null){
                progressBar.setVisibility(View.VISIBLE);
                verifyCode(code);
            }

        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            Toast.makeText(VerifyPhoneNumber.this,e.getMessage(),Toast.LENGTH_SHORT).show();

        }
    };

    private void verifyCode(String CodeByUser){
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationCodeBySystem,CodeByUser);
        signInTheUserByCredentials(credential);
    }

    private void signInTheUserByCredentials(PhoneAuthCredential credential) {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(VerifyPhoneNumber.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            Intent intent = new Intent(getApplicationContext(),ViewDetails.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(VerifyPhoneNumber.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}






