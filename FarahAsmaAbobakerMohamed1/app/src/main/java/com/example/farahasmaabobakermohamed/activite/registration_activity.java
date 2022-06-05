package com.example.farahasmaabobakermohamed.activite;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.farahasmaabobakermohamed.R;
import com.example.farahasmaabobakermohamed.model.UserModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class registration_activity extends AppCompatActivity {
    TextView logi;
    FloatingActionButton enregistre;
    TextView enregis;
    EditText email,password,nom;
    FirebaseAuth auth;
    FirebaseDatabase database;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        auth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();

        logi=(TextView) findViewById(R.id.login);
        email=(EditText) findViewById(R.id.en_email);
        nom=(EditText) findViewById(R.id.en_nom);
        password=(EditText) findViewById(R.id.en_password);
        enregistre=(FloatingActionButton) findViewById(R.id.enregistrer);
        enregis=(TextView) findViewById(R.id.enregistrer1);


        logi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), login_activity.class);
                startActivity(intent);
            }
        });

        enregistre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createUser();
            }
        });

        enregis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createUser();
            }
        });

    }


        private void createUser(){
        String Username =nom.getText().toString();
        String Useremail =email.getText().toString();
        String Userpassword =password.getText().toString();

            if (TextUtils.isEmpty(Username)) {
                Toast.makeText(this,"Le Nom est Vide",Toast.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(Useremail)) {
                Toast.makeText(this,"L'email est Vide",Toast.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(Userpassword)) {
                Toast.makeText(this,"Le Mot de passe est Vide",Toast.LENGTH_SHORT).show();
                return;
            }

            if(Userpassword.length()<8){
                Toast.makeText(this,"Le Mot de passe doit etre superieur à 8 lettres",Toast.LENGTH_SHORT).show();
                return;
            }
            auth.createUserWithEmailAndPassword(Useremail,Userpassword)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                UserModel userModel=new UserModel(Username,Useremail,Userpassword);
                                String ids=task.getResult().getUser().getUid();
                                database.getReference().child("Users").child(ids).setValue(userModel);
                                Toast.makeText(registration_activity.this,"Enregistrement avec succéss",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(registration_activity.this,login_activity.class));
                            }else{
                                Toast.makeText(registration_activity.this,"Erreur d'enregistrement"+task.getException(),Toast.LENGTH_SHORT).show();
                            }
                        }
                    });


        }



}