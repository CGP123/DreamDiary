package com.example.dreamdiary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Authorization extends AppCompatActivity implements View.OnClickListener{
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private DatabaseReference myRef;
    private EditText ETemail;
    private EditText ETpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorization);

        mAuth = FirebaseAuth.getInstance();
        myRef = FirebaseDatabase.getInstance().getReference();
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null){
                    Log.d("MyLog", "onAuthStateChanged:signed_in:" + user.getUid());
                }else{
                    Log.d("MyLog", "onAuthStateChanged:signed_out");
                }
            }
        };

        ETemail = findViewById(R.id.et_email);
        ETpassword = findViewById(R.id.et_password);
        findViewById(R.id.btn_sign_in).setOnClickListener(this);
        findViewById(R.id.btn_registration).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_sign_in){
            signing(ETemail.getText().toString(), ETpassword.getText().toString());
        }else if (v.getId() == R.id.btn_registration){
            registration(ETemail.getText().toString(), ETpassword.getText().toString());
        }
    }
    public void signing(String email, String password){
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(Authorization.this, "Авторизация успешна", Toast.LENGTH_SHORT).show();
                    Authorization.super.finish();
                } else{
                    Toast.makeText(Authorization.this, "Ошибка авторизации", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void registration(String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    myRef = FirebaseDatabase.getInstance().getReference().child("users");
                    FirebaseUser currentUser = mAuth.getCurrentUser();
                    myRef.child(currentUser.getUid()).setValue(currentUser.getUid());
                    Toast.makeText(Authorization.this, "Регистрация успешна", Toast.LENGTH_SHORT).show();
                    Authorization.super.finish();
                } else{
                    Toast.makeText(Authorization.this, "Ошибка регистрации", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}