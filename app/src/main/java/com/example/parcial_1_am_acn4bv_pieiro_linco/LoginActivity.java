package com.example.parcial_1_am_acn4bv_pieiro_linco;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
    EditText etEmail, etPwd;
    Button btLogin;

    public void login (String email, String password){
        Log.i("firebase", "mail: "+ email);
        Log.i("firebase", "password: "+ password);

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



    }
}