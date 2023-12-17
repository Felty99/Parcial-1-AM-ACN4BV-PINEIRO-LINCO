package com.example.parcial_1_am_acn4bv_pieiro_linco;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.parcial_1_am_acn4bv_pieiro_linco.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class GameActivity extends AppCompatActivity {
  //Casteo de elementos.

    ImageView imgPregunta;
    RadioGroup rgRespuestas;
    RadioButton rb1,rb2,rb3,rb4;
    Button btEnviar,btSalir;

    FirebaseAuth mAuth;
    FirebaseFirestore db;

    User user;
    Integer correctas = 0;
    int index = 0;
    int rta =1;
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent jugar = getIntent();
        String nombre = jugar.getStringExtra("nombre");
        imgPregunta = findViewById(R.id.imgPregunta);
        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
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

        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/tpfinal-52f32.appspot.com/o/p0.jpg?alt=media&token=fa1dff50-fbcb-45e9-8ab0-52aab708f6bf").into(imgPregunta);


        btEnviar.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
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
                        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/tpfinal-52f32.appspot.com/o/p1.jpg?alt=media&token=7e5ae2f7-ae84-43ae-8a6a-4920d1c3f9ef").into(imgPregunta);
                        //imgPregunta.setImageResource(R.drawable.p1);
                        rb1.setText("Charmander");
                        rb2.setText("Bulbasaur");
                        rb3.setText("Ditto");
                        rb4.setText("Spearow");
                        rta++;
                        break;
                    case 2:

                        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/tpfinal-52f32.appspot.com/o/p2.jpg?alt=media&token=e4a3a87d-cd09-477b-86ae-cc8bd5469fde").into(imgPregunta);
                        //imgPregunta.setImageResource(R.drawable.p2);
                        rb1.setText("Metapod");
                        rb2.setText("Rattata");
                        rb3.setText("Vulpix");
                        rb4.setText("Gengar");
                        rta++;
                        break;
                    case 3:
                        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/tpfinal-52f32.appspot.com/o/p3.png?alt=media&token=91e0ca54-e476-4ab5-9a43-a4d98d9f4662").into(imgPregunta);
                        // imgPregunta.setImageResource(R.drawable.p3);
                        rb1.setText("Blastoise");
                        rb2.setText("Charizard");
                        rb3.setText("Squirtle");
                        rb4.setText("Ninetails");
                        rta++;
                        break;
                    case 4:
                        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/tpfinal-52f32.appspot.com/o/p4.png?alt=media&token=d900155a-0f8e-4745-a38a-8e4f397e0787").into(imgPregunta);
                        //imgPregunta.setImageResource(R.drawable.p4);
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

                        Map<String, Object> docData = new HashMap<>();
                        docData.put("apodo", nombre);
                        docData.put("correctas", correctas);
                        docData.put("fecha", new Timestamp(new Date()));
                        db.collection("partidas").add(docData)
                                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                    @Override
                                    public void onSuccess(DocumentReference documentReference) {
                                        Log.d(TAG, "DocumentSnapshot written with ID: " + documentReference.getId());
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.w(TAG, "Error adding document", e);
                                    }
                                });

                        continuar.putExtra("cantidad_respuestas",scorrectas);
                        startActivity(continuar);
                        finish();
                        break;
                    default:
                        Intent volver = new Intent (GameActivity.this,GameActivity.class);
                        startActivity(volver);
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