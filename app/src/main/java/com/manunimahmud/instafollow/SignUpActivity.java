package com.manunimahmud.instafollow;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.manunimahmud.instafollow.databinding.ActivitySignUpBinding;
import com.manunimahmud.instafollow.models.User;

public class SignUpActivity extends AppCompatActivity {
    ActivitySignUpBinding binding;
    private FirebaseAuth auth;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users");

        binding.createBtnId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkValidation();
            }
        });
        binding.Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });


    }
    private void checkValidation(){
        String userName = binding.textInput1.getEditText().getText().toString().trim();
        String email = binding.textInput2.getEditText().getText().toString().trim();
        String password = binding.textInput3.getEditText().getText().toString().trim();
        String profession = binding.textInput4.getEditText().getText().toString().trim();

        if (userName.isEmpty()){
            binding.textInput1.setError("Empty Field");
            binding.textInput1.requestFocus();
        }else if (email.isEmpty()){
            binding.textInput2.setError("Empty Field");
            binding.textInput2.requestFocus();
        }else if (password.isEmpty()){
            binding.textInput3.setError("Empty Field");
            binding.textInput3.requestFocus();
        }else if (profession.isEmpty()){
            binding.textInput4.setError("Empty Field");
            binding.textInput4.requestFocus();
        } else{
            auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        User user = new User(userName,email,password,profession);
                        databaseReference.child(task.getResult().getUser().getUid()).setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(SignUpActivity.this, "User Created Successfully!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(SignUpActivity.this,MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        });
                    }else {
                        Toast.makeText(SignUpActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(SignUpActivity.this, "Error :"+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}