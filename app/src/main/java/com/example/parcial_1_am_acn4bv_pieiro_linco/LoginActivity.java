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
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    EditText etEmail, etPwd;
    Button btLogin,btRegistrate,btSalir;

    private FirebaseAuth mAuth;
    public void login (String email, String password) {
        Log.i("firebase", "mail: " + email);
        Log.i("firebase", "password: " + password);

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(getApplicationContext(), WelcomeActivity.class);
                            startActivity(intent);

                        } else {
                            Toast.makeText(LoginActivity.this, "Fallo en la autenticación",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });


    }

    public void onLoginButtonClick (View view){
        EditText emailInput = findViewById(R.id.etEmail);
        EditText passInput = findViewById(R.id.etPwd);

        String email = emailInput.getText().toString();
        String password = passInput.getText().toString();

        this.login(email,password);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btLogin=findViewById(R.id.btLogin);
        btSalir = findViewById(R.id.btSalir);
        btRegistrate=findViewById(R.id.btRegistrate);
        btSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishAffinity();
            }

        });

        btRegistrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });

        mAuth = FirebaseAuth.getInstance();



    }
}