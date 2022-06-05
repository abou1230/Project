package com.example.farahasmaabobakermohamed.activite;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.farahasmaabobakermohamed.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddAdresseActivity extends AppCompatActivity {

    EditText nom,adresse,ville,codepostal,numerotelephone;
    Button ajouter;

    FirebaseAuth auth;
    FirebaseFirestore firestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_adresse);

        auth=FirebaseAuth.getInstance();
        firestore=FirebaseFirestore.getInstance();

        nom=findViewById(R.id.ad_name);
        adresse=findViewById(R.id.ad_address);
        ville=findViewById(R.id.ad_city);
        codepostal=findViewById(R.id.ad_code);
        numerotelephone=findViewById(R.id.ad_phone);
        ajouter=findViewById(R.id.ad_add_address);

        ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userNom=nom.getText().toString();
                String userVille=ville.getText().toString();
                String userAdresse=adresse.getText().toString();
                String userCodepostal=codepostal.getText().toString();
                String userNumeroTelephone=numerotelephone.getText().toString();

                String final_adresse ="";

                if(!userNom.isEmpty()){
                    final_adresse+=userNom+".";
                }
                if(!userVille.isEmpty()){
                    final_adresse+=userVille+".";
                }
                if(!userAdresse.isEmpty()){
                    final_adresse+=userAdresse+".";
                }
                if(!userCodepostal.isEmpty()){
                    final_adresse+=userCodepostal+".";
                }
                if(!userNumeroTelephone.isEmpty()){
                    final_adresse+=userNumeroTelephone+".";
                }

                if (!userNom.isEmpty() && !userVille.isEmpty() && !userAdresse.isEmpty() && !userCodepostal.isEmpty() && !userNumeroTelephone.isEmpty()){
                    Map<String,String> map=new HashMap<>();
                    map.put("userAdresse",final_adresse);

                    firestore.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                            .collection("Address").add(map).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentReference> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(AddAdresseActivity.this,"Adresse Ajouter",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(AddAdresseActivity.this,DetailActivity.class));
                                finish();
                            }
                        }
                    });
                } else{
                    Toast.makeText(AddAdresseActivity.this,"Veuillez remplir tous les champs",Toast.LENGTH_SHORT).show();
                }


            }
        });


    }
}