package com.example.farahasmaabobakermohamed.activite;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.farahasmaabobakermohamed.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login_activity extends AppCompatActivity {
    FloatingActionButton conn;
    EditText login,password;
    TextView con,regis;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login=(EditText) findViewById(R.id.email);
        password=(EditText) findViewById(R.id.password);
        con=(TextView) findViewById(R.id.connexion1);
        conn=(FloatingActionButton) findViewById(R.id.connexion);
        regis=(TextView) findViewById(R.id.registration);
        firebaseAuth=FirebaseAuth.getInstance();

        conn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signuser();
            }

        });


        con.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signuser();
            }

        });




        regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), registration_activity.class);
                startActivity(intent);

            }
        });


    }


    private void signuser(){
        String Useremail=login.getText().toString();
        String Userpassword=password.getText().toString();

        if (TextUtils.isEmpty(Useremail)) {
            Toast.makeText(this,"L'email est Vide",Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(Userpassword)) {
            Toast.makeText(this,"Le Mot de passe est Vide",Toast.LENGTH_SHORT).show();
            return;
        }
        firebaseAuth.signInWithEmailAndPassword(Useremail,Userpassword)
                .addOnCompleteListener(login_activity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(login_activity.this,"Connexion etablit",Toast.LENGTH_SHORT);
                            startActivity(new Intent(login_activity.this,MainActivity.class));
                        }else{
                            Toast.makeText(login_activity.this,"Login ou password incorrect",Toast.LENGTH_SHORT).show();
                        }
                    }
                });


    }




}
