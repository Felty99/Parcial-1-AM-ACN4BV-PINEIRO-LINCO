package com.example.parcial_1_am_acn4bv_pieiro_linco;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class WelcomeActivity extends AppCompatActivity {
    TextView tvTitulo;
    EditText etNombre;
    Button btContinuar,btSalir,btLogout;

    Switch swMusic;
    MusicPlayer musicPlayer;

    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        mAuth = FirebaseAuth.getInstance();
        etNombre=findViewById(R.id.etNombre);
        btContinuar=findViewById(R.id.btContinuar);
        btLogout=findViewById(R.id.btLogout);
        btSalir = findViewById(R.id.btSalir);
        swMusic = findViewById(R.id.swMusic);
        musicPlayer = musicPlayer.getInstance();
        musicPlayer.initialize(this, R.raw.pkm);

        swMusic.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    musicPlayer.start();
                } else {
                    musicPlayer.pause();
                }
            }
        });

        btSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }

        });

        btLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }

        });

        etNombre.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    btContinuar.setVisibility(View.VISIBLE);
                } else {
                    btContinuar.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        btContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = etNombre.getText().toString();

                Intent continuar = new Intent(WelcomeActivity.this, RulesActivity.class);


                continuar.putExtra("nombre",nombre);

                startActivity(continuar);
                finish();
            }
        });


    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        musicPlayer.release();
    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            btLogout.setVisibility(View.VISIBLE);
        } else {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            Log.i("Firebase","No hay usuario");
        }
    }
    public void logout (){
        mAuth.signOut();
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
    }
}

