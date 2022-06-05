package com.example.farahasmaabobakermohamed.activite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.farahasmaabobakermohamed.R;

public class ChoisirActivity extends AppCompatActivity {
Button btn1,btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choisir);
        btn1=findViewById(R.id.bouton1);
        btn2=findViewById(R.id.bouton2);

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ChoisirActivity.this, EditProfileActivity.class));
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ChoisirActivity.this,Update_profile_Activity.class));
            }
        });

    }
}