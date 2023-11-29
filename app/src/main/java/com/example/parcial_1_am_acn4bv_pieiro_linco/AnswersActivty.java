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

import com.squareup.picasso.Picasso;

public class AnswersActivty extends AppCompatActivity {
    private ImageView ivRespuesta;
    private ListView lvListaRespuestas;
    private ArrayAdapter<String> adapter;
    private TextView tvAcierto,tvPokedex;
    Button btFinal;    private String[] data = {"Pregunta 1","Pregunta 2","Pregunta 3","Pregunta 4","Pregunta 5"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answers_activty);
        ivRespuesta = findViewById(R.id.ivRespuesta);
        lvListaRespuestas = findViewById(R.id.lvListaRespuestas);
        btFinal = findViewById(R.id.btFinal);
        tvAcierto = findViewById(R.id.tvAcierto);
        tvPokedex = findViewById(R.id.tvPokedex);


        Intent continuar = getIntent();
        String respuestas_correctas = continuar.getStringExtra("cantidad_respuestas");
        ivRespuesta.setImageResource(R.drawable.inicio);
        Integer rtas = Integer.parseInt(respuestas_correctas);

        if (rtas == 0){
            tvAcierto.setTextColor(getResources().getColor(R.color.pokemon_red));
            tvAcierto.setText("No acertaste ninguna pregunta");
        } else if (rtas == 1 ) {
            tvAcierto.setText("Acertaste 1 pregunta");
        } else if (rtas > 1) {
            tvAcierto.setText("Acertaste "+respuestas_correctas+" pregunta/s.");}


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
                    Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/tpfinal-52f32.appspot.com/o/r0.jpg?alt=media&token=457ca7a5-8ade-497a-9e69-0474541da28e").into(ivRespuesta);
                    //ivRespuesta.setImageResource(R.drawable.r0);
                } else if (respuesta == "Pregunta 2") {
                    Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/tpfinal-52f32.appspot.com/o/r1.jpg?alt=media&token=f4899876-6fd4-4317-8d01-a12fd125025b").into(ivRespuesta);
                    //ivRespuesta.setImageResource(R.drawable.r1);
                }else if (respuesta == "Pregunta 3") {
                    Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/tpfinal-52f32.appspot.com/o/r2.jpg?alt=media&token=dcc1f30b-9397-4afe-a975-521a9b4a6393").into(ivRespuesta);
//ivRespuesta.setImageResource(R.drawable.r2);
                }else if (respuesta == "Pregunta 4") {
                    Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/tpfinal-52f32.appspot.com/o/r3.png?alt=media&token=b68d4ab9-7e4e-4d43-8dd6-329611b71acf").into(ivRespuesta);
                    //ivRespuesta.setImageResource(R.drawable.r3);
                }else if (respuesta == "Pregunta 5") {
                    Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/tpfinal-52f32.appspot.com/o/r4.png?alt=media&token=2e76a730-7504-4074-b82c-853a830967ad").into(ivRespuesta);
//ivRespuesta.setImageResource(R.drawable.r4);
                }
            }
        });

    }

}