package com.example.parcial_1_am_acn4bv_pieiro_linco;

import androidx.annotation.NonNull;
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

import com.example.parcial_1_am_acn4bv_pieiro_linco.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class WelcomeActivity extends AppCompatActivity {
    TextView tvTitulo,tvNombre;
    Button btContinuar,btSalir,btLogout;

    Switch swMusic;
    MusicPlayer musicPlayer;

    FirebaseAuth mAuth;
    FirebaseFirestore db;

    User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        tvNombre=findViewById(R.id.tvNombre);
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
                finishAffinity();
            }

        });

        btLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }

        });


        btContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = user.getApodo();
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
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            btLogout.setVisibility(View.VISIBLE);
            String uid= currentUser.getUid();
            db.collection("usuarios")
                    .whereEqualTo("uid", uid)
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if(task.isSuccessful()){
                                for (QueryDocumentSnapshot documento: task.getResult()) {
                                    String id = documento.getId();
                                    Object data = documento.getData();
                                    user = documento.toObject(User.class);
                                    String apodo = user.getApodo();
                                    tvNombre.setText("¡Bienvenido "+apodo+"!");
                                }
                            }
                        }
                    });
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

