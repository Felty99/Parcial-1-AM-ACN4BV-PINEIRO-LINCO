package com.example.parcial_1_am_acn4bv_pieiro_linco;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class GameActivity extends AppCompatActivity {
  //Casteo de elementos.

    ImageView imgPregunta;
    RadioGroup rgRespuestas;
    RadioButton rb1,rb2,rb3,rb4;
    Button btEnviar,btSalir;

    Integer correctas = 0;
    int index = 0;
    int rta =1;
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgPregunta = findViewById(R.id.imgPregunta);

        imgPregunta.setImageResource(R.drawable.p0);

        rgRespuestas = findViewById(R.id.rgRespuestas);

        btEnviar = findViewById(R.id.btEnviar);
        btSalir = findViewById(R.id.btSalir);

        rb1 = findViewById(R.id.rb1);
        rb2 = findViewById(R.id.rb2);
        rb3 = findViewById(R.id.rb3);
        rb4 = findViewById(R.id.rb4);

        btEnviar.setEnabled(false);
        btSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        TextView respuestas = new TextView(this);
        LinearLayout Juego = findViewById(R.id.Juego);
        Juego.addView(respuestas);
        respuestas.setTextColor(+R.color.teal_700);
        CompoundButton.OnCheckedChangeListener radioButtonsListener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (rb1.isChecked() || rb2.isChecked() || rb3.isChecked() || rb4.isChecked()) {
                    btEnviar.setEnabled(true);
                } else {
                    btEnviar.setEnabled(false);
                }
            }
        };
        rb1.setOnCheckedChangeListener(radioButtonsListener);
        rb2.setOnCheckedChangeListener(radioButtonsListener);
        rb3.setOnCheckedChangeListener(radioButtonsListener);
        rb4.setOnCheckedChangeListener(radioButtonsListener);

        rb1.setText("Pikachu");
        rb2.setText("Gastly");
        rb3.setText("Jigglypuf");
        rb4.setText("Snorlax");


        btEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar snackbar = Snackbar.make(view, R.string.respuesta_enviada, 1000); // Duración de 3000 milisegundos (3 segundos)
                snackbar.show();
                respuestas.setText("Has respondido "+rta+ " preguntas/s");

                if (index ==0 &&  rb1.isChecked()){
                    correctas++;}
                if (index == 1 && rb3.isChecked()){
                    correctas++;}
                if (index == 2 && rb4.isChecked()){
                    correctas++;}
                if (index == 3 && rb2.isChecked()){
                    correctas++;}
                if (index == 4 && rb4.isChecked()){
                    correctas++;}
                index++;
                limpiarRb();
                switch (index) {
                    case 1:
                        imgPregunta.setImageResource(R.drawable.p1);
                        rb1.setText("Charmander");
                        rb2.setText("Bulbasaur");
                        rb3.setText("Ditto");
                        rb4.setText("Spearow");
                        rta++;
                        break;
                    case 2:
                        imgPregunta.setImageResource(R.drawable.p2);
                        rb1.setText("Metapod");
                        rb2.setText("Rattata");
                        rb3.setText("Vulpix");
                        rb4.setText("Gengar");
                        rta++;
                        break;
                    case 3:
                        imgPregunta.setImageResource(R.drawable.p3);
                        rb1.setText("Blastoise");
                        rb2.setText("Charizard");
                        rb3.setText("Squirtle");
                        rb4.setText("Ninetails");
                        rta++;
                        break;
                    case 4:
                        imgPregunta.setImageResource(R.drawable.p4);
                        rb1.setText("Machop");
                        rb2.setText("Gloom");
                        rb3.setText("Diglett");
                        rb4.setText("Eevee");
                        rta++;
                        break;
                    case 5:
                        rta++;
                        Toast.makeText(getApplicationContext(), R.string.respuesta_final, Toast.LENGTH_LONG).show();
                        Intent continuar = new Intent(GameActivity.this, AnswersActivty.class);
                        String scorrectas = correctas.toString();
                        continuar.putExtra("cantidad_respuestas",scorrectas);
                        startActivity(continuar);
                        finish();
                        break;
                    default:
                        Intent volver = new Intent (GameActivity.this,GameActivity.class);
                        startActivity(volver);
                        finish();
                        finish();
                }

            }
        });
    }


    //Método para limpiar los radio buttons.
    public void limpiarRb() {

        rb1.setChecked(false);
        rb2.setChecked(false);
        rb3.setChecked(false);
        rb4.setChecked(false);
    }
}