package com.example.farahasmaabobakermohamed.activite;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.farahasmaabobakermohamed.R;
import com.example.farahasmaabobakermohamed.adapter.AdresseAdapter;
import com.example.farahasmaabobakermohamed.model.AdresseModel;
import com.example.farahasmaabobakermohamed.model.MyCartModel;
import com.example.farahasmaabobakermohamed.model.NewProduitModel;
import com.example.farahasmaabobakermohamed.model.ShowAllModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class AdresseActivity extends AppCompatActivity implements AdresseAdapter.SelectedAdresse{

    Button addAdresse,paymentbuy;
    RecyclerView recyclerView;
    private List<AdresseModel> adresseModelList;
    private AdresseAdapter adresseAdapter;
    FirebaseAuth auth;
    FirebaseFirestore firestore;

    String mAdresse="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adresse);

        //Obtenir des données à partir d'activités détaillées
        Object obj =getIntent().getSerializableExtra("item");


        firestore=FirebaseFirestore.getInstance();
        auth=FirebaseAuth.getInstance();
        recyclerView=findViewById(R.id.address_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        adresseModelList=new ArrayList<>();
        adresseAdapter=new AdresseAdapter(this, getApplicationContext(), adresseModelList);
        recyclerView.setAdapter(adresseAdapter);
        firestore.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                .collection("Address").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (DocumentSnapshot doc : task.getResult().getDocuments()) {
                        AdresseModel adresseModels=doc.toObject(AdresseModel.class);
                        adresseModelList.add(adresseModels);
                        adresseAdapter.notifyDataSetChanged();

                    }
                }
            }
        });


        paymentbuy=findViewById(R.id.payment_btn);
        paymentbuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double amount =0.0;
                if (obj instanceof NewProduitModel){
                    NewProduitModel newProduitModel=(NewProduitModel) obj;
                    amount=newProduitModel.getPrix();
                }
                if (obj instanceof ShowAllModel){
                    ShowAllModel showAllModel=(ShowAllModel) obj;
                    amount=showAllModel.getPrix();
                }
                Intent intent=new Intent(AdresseActivity.this,paiementActivity.class);
                intent.putExtra("amount",amount);
                startActivity(intent);
            }
        });


        addAdresse=findViewById(R.id.add_address_btn);
        addAdresse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdresseActivity.this,AddAdresseActivity.class));
            }
        });

    }

    @Override
    public void setAdresse(String adresse) {
        mAdresse=adresse;
    }
}