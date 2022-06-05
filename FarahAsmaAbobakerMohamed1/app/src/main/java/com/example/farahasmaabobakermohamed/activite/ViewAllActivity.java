package com.example.farahasmaabobakermohamed.activite;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.farahasmaabobakermohamed.R;
import com.example.farahasmaabobakermohamed.adapter.ShowAllAdapter;
import com.example.farahasmaabobakermohamed.adapter.ViewAllAdapter;
import com.example.farahasmaabobakermohamed.model.ShowAllModel;
import com.example.farahasmaabobakermohamed.model.ViewAllModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ViewAllActivity extends AppCompatActivity {

    FirebaseFirestore firestore;
    RecyclerView recyclerView;
    ViewAllAdapter viewAllAdapter;
    List<ViewAllModel> viewAllModelList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all);

        String type=getIntent().getStringExtra("type");
        firestore= FirebaseFirestore.getInstance();
        recyclerView=findViewById(R.id.view_all_rec);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        viewAllModelList =new ArrayList<>();
        viewAllAdapter= new ViewAllAdapter(this,viewAllModelList);
        recyclerView.setAdapter(viewAllAdapter);



        if (type == null || type.isEmpty()){
            firestore.collection("Produit")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    ViewAllModel viewAllModel=document.toObject(ViewAllModel.class);
                                    viewAllModelList.add(viewAllModel);
                                    viewAllAdapter.notifyDataSetChanged();

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
                                    ViewAllModel viewAllModel=document.toObject(ViewAllModel.class);
                                    viewAllModelList.add(viewAllModel);
                                    viewAllAdapter.notifyDataSetChanged();

                                }
                            } else {

                            }
                        }
                    });
        }


        if(type != null && type.equalsIgnoreCase("baffe")){
            firestore.collection("Produit").whereEqualTo("type","baffe")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    ViewAllModel viewAllModel=document.toObject(ViewAllModel.class);
                                    viewAllModelList.add(viewAllModel);
                                    viewAllAdapter.notifyDataSetChanged();

                                }
                            } else {

                            }
                        }
                    });
        }



        if(type != null && type.equalsIgnoreCase("tablette")){
            firestore.collection("Produit").whereEqualTo("type","tablette")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    ViewAllModel viewAllModel=document.toObject(ViewAllModel.class);
                                    viewAllModelList.add(viewAllModel);
                                    viewAllAdapter.notifyDataSetChanged();

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
                                    ViewAllModel viewAllModel=document.toObject(ViewAllModel.class);
                                    viewAllModelList.add(viewAllModel);
                                    viewAllAdapter.notifyDataSetChanged();

                                }
                            } else {

                            }
                        }
                    });
        }


        if(type != null && type.equalsIgnoreCase("montre")){
            firestore.collection("Produit").whereEqualTo("type","montre")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    ViewAllModel viewAllModel=document.toObject(ViewAllModel.class);
                                    viewAllModelList.add(viewAllModel);
                                    viewAllAdapter.notifyDataSetChanged();

                                }
                            } else {

                            }
                        }
                    });
        }

        if(type != null && type.equalsIgnoreCase("ecouteur")){
            firestore.collection("Produit").whereEqualTo("type","ecouteur")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    ViewAllModel viewAllModel=document.toObject(ViewAllModel.class);
                                    viewAllModelList.add(viewAllModel);
                                    viewAllAdapter.notifyDataSetChanged();

                                }
                            } else {

                            }
                        }
                    });
        }








    }
}