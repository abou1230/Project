package com.example.farahasmaabobakermohamed.activite;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.farahasmaabobakermohamed.R;
import com.example.farahasmaabobakermohamed.adapter.ShowAllAdapter;
import com.example.farahasmaabobakermohamed.model.Categoriemodel;
import com.example.farahasmaabobakermohamed.model.ShowAllModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ShowAllActivity extends AppCompatActivity {
    RecyclerView showRecyclerView;
    ShowAllAdapter showAllAdapter;
    List<ShowAllModel>showAllModelList;
    FirebaseFirestore firestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all);

        String type=getIntent().getStringExtra("type");

        firestore= FirebaseFirestore.getInstance();
        showRecyclerView=findViewById(R.id.show_all_rec);
        showRecyclerView.setLayoutManager(new GridLayoutManager(this,2));
        showAllModelList =new ArrayList<>();
        showAllAdapter= new ShowAllAdapter(this,showAllModelList);
        showRecyclerView.setAdapter(showAllAdapter);

        if (type == null || type.isEmpty()){
            firestore.collection("Produit")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    ShowAllModel showAllModel=document.toObject(ShowAllModel.class);
                                    showAllModelList.add(showAllModel);
                                    showAllAdapter.notifyDataSetChanged();

                                }
                            } else {

                            }
                        }
                    });

        }


        if(type != null && type.equalsIgnoreCase("telephone")){
            firestore.collection("Produit").whereEqualTo("type","telephone")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    ShowAllModel showAllModel=document.toObject(ShowAllModel.class);
                                    showAllModelList.add(showAllModel);
                                    showAllAdapter.notifyDataSetChanged();

                                }
                            } else {

                            }
                        }
                    });
        }


        if(type != null && type.equalsIgnoreCase("ordinateur")){
            firestore.collection("Produit").whereEqualTo("type","ordinateur")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    ShowAllModel showAllModel=document.toObject(ShowAllModel.class);
                                    showAllModelList.add(showAllModel);
                                    showAllAdapter.notifyDataSetChanged();

                                }
                            } else {

                            }
                        }
                    });
        }


    }
}