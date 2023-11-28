package com.example.parcial_1_am_acn4bv_pieiro_linco;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FinalActivity extends AppCompatActivity {
    TextView tvGracias;

    Button btInicio, btSalir, btEnd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);

        tvGracias=findViewById(R.id.tvGracias);
        btInicio=findViewById(R.id.btInicio);
        btSalir=findViewById(R.id.btSalir);

        btInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent inicio = new Intent(FinalActivity.this, WelcomeActivity.class);
                startActivity(inicio);
                finish();
            }
        });

        btSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }

        });
        btEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }

        });
    }
}