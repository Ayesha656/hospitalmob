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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class updatedoctor extends AppCompatActivity {
    Map<String, Object> data = new HashMap<>();
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatedoctor);
        EditText doctorname = findViewById(R.id.updatename);
        EditText doctspecialization = findViewById(R.id.updatespecialization);
        EditText doctoremial = findViewById(R.id.doctoremail);
        Button btn = findViewById(R.id.update);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String u_dname = doctorname.getText().toString();
                String u_docspecialization = doctspecialization.getText().toString();
                String u_demail = doctoremial.getText().toString();

                doctorupdate(u_dname,u_docspecialization,u_demail);
            }
        });



    }

    private void doctorupdate(String u_dname, String u_docspecialization, String u_demail) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        data.put("doctorname",u_dname);
        data.put("doctorspecialization",u_docspecialization);

        db.collection("doctor")
                .whereEqualTo("doctoremail",u_demail)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful() && !task.getResult().isEmpty()){
                            DocumentSnapshot documentSnapshot = task.getResult().getDocuments().get(0);
                            String documentId = documentSnapshot.getId();
                            db.collection("doctor")
                                    .document(documentId)
                                    .update(data)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Toast.makeText(updatedoctor.this, "updated!", Toast.LENGTH_SHORT).show();
                                            Intent i = new Intent(updatedoctor.this, Home.class);
                                            startActivity(i);
                                                }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(updatedoctor.this, "Failed", Toast.LENGTH_SHORT).show();
                                            Intent i = new Intent(updatedoctor.this, MainActivity.class);
                                            startActivity(i);

                                            }
                                    });
                        }
                    }
                });


    }
}