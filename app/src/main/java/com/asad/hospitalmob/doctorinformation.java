package com.asad.hospitalmob;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class doctorinformation extends AppCompatActivity {
    @SuppressLint("MissingInflatedId")
    Map<String, Object> user = new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctorinformation);

       EditText d_doctorname = findViewById(R.id.doctorname);
        EditText d_doctoremail = findViewById(R.id.doctoremail);
        EditText d_doctospecialization = findViewById(R.id.doctospecialization);
        EditText d_dob = findViewById(R.id.dob);
        Button d_register = findViewById(R.id.register);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        Button shift = findViewById(R.id.shiftupdate);



        shift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(doctorinformation.this, updatedoctor.class);
                startActivity(i);
            }
        });


        d_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String d_name = d_doctorname.getText().toString();
                String d_email = d_doctoremail.getText().toString();
                String d_specialization = d_doctospecialization.getText().toString();
                String dob = d_dob.getText().toString();
                registeruser(d_name,d_email,d_specialization,dob);
                
            }
        });
        
    }

    private void registeruser(String d_name, String d_email, String d_specialization, String dob) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        //map. put("firebasekey e.g doctorname", "value insert e.g abc")
        user.put("doctorname",d_name);
        user.put("doctoremail",d_email);
        user.put("doctorspecialization",d_specialization);
        user.put("dob",dob);
        db.collection("doctor")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(doctorinformation.this, "data inserted", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(doctorinformation.this,"data failed",Toast.LENGTH_SHORT).show();
                    }
                });

    }
}