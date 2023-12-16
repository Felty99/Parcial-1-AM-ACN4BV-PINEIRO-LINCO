package com.example.parcial_1_am_acn4bv_pieiro_linco;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    EditText etEmail, etPwd;
    Button btRegistro;

    private FirebaseAuth mAuth;
    public void login (String email, String password) {
        Log.i("firebase", "mail: " + email);
        Log.i("firebase", "password: " + password);

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(getApplicationContext(), WelcomeActivity.class);
                            startActivity(intent);

                        } else {
                            Toast.makeText(RegisterActivity.this, "Fallo en la autenticación",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });


    }

    public void onRegisterButtonClick (View view){
        EditText emailInput = findViewById(R.id.etEmail);
        EditText passInput = findViewById(R.id.etPwd);

        String email = emailInput.getText().toString();
        String password = passInput.getText().toString();

        this.login(email,password);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btRegistro=findViewById(R.id.btRegistro);

        mAuth = FirebaseAuth.getInstance();



    }}