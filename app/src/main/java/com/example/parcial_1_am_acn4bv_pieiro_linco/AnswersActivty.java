package com.example.parcial_1_am_acn4bv_pieiro_linco;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class AnswersActivty extends AppCompatActivity {
    private ImageView ivRespuesta;
    private ListView lvListaRespuestas;
    private ArrayAdapter<String> adapter;
    private TextView tvAcierto;
    Button btFinal;
    private String[] data = {"Pregunta 1","Pregunta 2","Pregunta 3","Pregunta 4","Pregunta 5"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answers_activty);
        ivRespuesta = findViewById(R.id.ivRespuesta);
        lvListaRespuestas = findViewById(R.id.lvListaRespuestas);
        btFinal = findViewById(R.id.btFinal);
        tvAcierto = findViewById(R.id.tvAcierto);

        Intent continuar = getIntent();
        String respuestas_correctas = continuar.getStringExtra("cantidad_respuestas");
        ivRespuesta.setImageResource(R.drawable.inicio);

        tvAcierto.setText("Acertaste "+respuestas_correctas+" pregunta/s.");

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
        lvListaRespuestas.setAdapter(adapter);

        btFinal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent seguir = new Intent(AnswersActivty.this, FinalActivity.class);
                startActivity(seguir);
                finish();

            }
        });

        lvListaRespuestas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String respuesta = (String) parent.getItemAtPosition(position);
               Toast.makeText(AnswersActivty.this,respuesta,Toast.LENGTH_SHORT).show();
                if (respuesta == "Pregunta 1"){
                    ivRespuesta.setImageResource(R.drawable.r0);
                } else if (respuesta == "Pregunta 2") {
                    ivRespuesta.setImageResource(R.drawable.r1);
                }else if (respuesta == "Pregunta 3") {
                    ivRespuesta.setImageResource(R.drawable.r2);
                }else if (respuesta == "Pregunta 4") {
                    ivRespuesta.setImageResource(R.drawable.r3);
                }else if (respuesta == "Pregunta 5") {
                    ivRespuesta.setImageResource(R.drawable.r4);
                }
            }
        });

    }
}