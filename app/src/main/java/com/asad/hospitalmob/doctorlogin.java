package com.asad.hospitalmob;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class doctorlogin extends AppCompatActivity {
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctorlogin);
        EditText U_useremail = findViewById(R.id.useremail);
        EditText U_userpassword = findViewById(R.id.userpassword);
        Button signin = findViewById(R.id.signin);


        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = U_useremail.getText().toString();
                String password = U_userpassword.getText().toString();
                usersignin(email,password);


            }
        });





    }

    private void usersignin(String email, String password) {

        mAuth = FirebaseAuth.getInstance();

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Intent i = new Intent(doctorlogin.this, doctorinformation.class);
                startActivity(i);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("error", e.toString());
            }
        });
    }
}