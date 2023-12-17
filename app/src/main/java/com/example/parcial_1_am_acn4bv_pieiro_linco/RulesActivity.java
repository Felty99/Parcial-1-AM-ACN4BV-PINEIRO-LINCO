package com.example.parcial_1_am_acn4bv_pieiro_linco;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

public class RulesActivity extends AppCompatActivity {

    Button btEmpezar,btSalir,btVolver;
    TextView tvBienvenido,tvReglas;
    Switch swMusic;
    MusicPlayer musicPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);

        tvBienvenido = findViewById(R.id.tvBienvenido);

        btSalir = findViewById(R.id.btSalir);

        btEmpezar = findViewById(R.id.btEmpezar);
        btVolver = findViewById(R.id.btVolver);

        musicPlayer = musicPlayer.getInstance();
        musicPlayer.initialize(this, R.raw.pkm);


        btSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        Intent recibir = getIntent();
        String nombre = recibir.getStringExtra("nombre");

        tvBienvenido.setText("Estimado "+nombre+ ":");

        btVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent volver = new Intent(RulesActivity.this, WelcomeActivity.class);
                startActivity(volver);
                finish();
            }
        });



        btEmpezar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent jugar = new Intent(RulesActivity.this, GameActivity.class);
                jugar.putExtra("nombre",nombre);
                startActivity(jugar);
                finish();
            }
        });
    }
}