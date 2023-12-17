package com.example.parcial_1_am_acn4bv_pieiro_linco;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    EditText etEmail, etPwd, etApodo;
    Button btRegister,btSalir;

    private FirebaseAuth mAuth;
    FirebaseFirestore db;
    public void register (String email, String password) {

        String apodo = etApodo.getText().toString().trim();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();

                            // Agregar información adicional a Firestore
                            agregarInfoAdicionalFirestore(user.getUid(), apodo);
                            Intent intent = new Intent(getApplicationContext(), WelcomeActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(RegisterActivity.this, "Fallo en la autenticación",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });


    }

    public void onRegisterButtonClick (View view){
        EditText emailInput = findViewById(R.id.etEmail);
        EditText passInput = findViewById(R.id.etPwd);

        String email = emailInput.getText().toString();
        String password = passInput.getText().toString();

        this.register(email,password);
    }
    private void agregarInfoAdicionalFirestore(String userId, String apodo) {
        // Crear un mapa para almacenar la información adicional
        FirebaseUser currentUser = mAuth.getCurrentUser();
        Map<String, Object> userData = new HashMap<>();
        userData.put("apodo", apodo);
        userData.put("uid",currentUser.getUid());

        // Agregar la información adicional a Firestore
        db.collection("usuarios").document(userId)
                .set(userData)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            // Información adicional agregada con éxito
                            Toast.makeText(RegisterActivity.this, "Usuario registrado con éxito.",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            // Si falla la adición de información adicional, mostrar un mensaje al usuario
                            Toast.makeText(RegisterActivity.this, "Error al agregar información adicional.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register);
        btSalir = findViewById(R.id.btSalir);
        btRegister=findViewById(R.id.btRegister);
        etApodo = findViewById(R.id.etApodo);



        btSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishAffinity();
            }

        });

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();


    }}