package com.example.feedback;

import android.widget.EditText;

public class DBHelperClass {
    String email , feedback;

    public DBHelperClass() {

    }

    public DBHelperClass(String email, String feedback) {
        this.email = email;
        this.feedback = feedback;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
