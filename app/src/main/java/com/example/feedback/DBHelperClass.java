package com.example.feedback;

import android.widget.EditText;

public class DBHelperClass {
    EditText Email , Feedback;

    public DBHelperClass() {

    }

    public DBHelperClass(EditText email, EditText feedback) {
        Email = email;
        Feedback = feedback;
    }

    public EditText getEmail() {
        return Email;
    }

    public void setEmail(EditText email) {
        Email = email;
    }

    public EditText getFeedback() {
        return Feedback;
    }

    public void setFeedback(EditText feedback) {
        Feedback = feedback;
    }
}
