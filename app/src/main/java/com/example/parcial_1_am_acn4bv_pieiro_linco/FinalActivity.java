package com.example.parcial_1_am_acn4bv_pieiro_linco;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.parcial_1_am_acn4bv_pieiro_linco.model.Partida;
import com.example.parcial_1_am_acn4bv_pieiro_linco.model.PartidaAdapter;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class FinalActivity extends AppCompatActivity {
    TextView tvGracias;

    RecyclerView rvPartidas;

    private List<Partida> listaPartidas;

    private PartidaAdapter partidaAdapter;

    Button btInicio, btSalir, btEnd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);


        btInicio=findViewById(R.id.btInicio);
        btSalir=findViewById(R.id.btSalir);
        btEnd = findViewById(R.id.btEnd);
        rvPartidas=findViewById(R.id.rvPartidas);
        listaPartidas = new ArrayList<>();
        partidaAdapter = new PartidaAdapter(listaPartidas);

        btInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent inicio = new Intent(FinalActivity.this, WelcomeActivity.class);
                startActivity(inicio);
                finish();
            }
        });
        rvPartidas.setLayoutManager(new LinearLayoutManager(this));
        rvPartidas.setAdapter(partidaAdapter);

        cargarPartidasDesdeFirestore();

        btSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishAffinity();
            }

        });

        btEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishAffinity();
            }
        });

    }
    private void cargarPartidasDesdeFirestore() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("partidas")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        listaPartidas.clear();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Partida partida = document.toObject(Partida.class);
                            listaPartidas.add(partida);
                        }
                        // Actualizar el RecyclerView directamente
                        rvPartidas.getAdapter().notifyDataSetChanged();
                    } else {
                        // Manejar errores al cargar partidas desde Firestore
                    }
                });
}}