package com.example.farahasmaabobakermohamed.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import  android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.farahasmaabobakermohamed.R;
import com.example.farahasmaabobakermohamed.activite.AjoutProduitActivity;
import com.example.farahasmaabobakermohamed.activite.ShowAllActivity;
import com.example.farahasmaabobakermohamed.activite.ViewAllActivity;
import com.example.farahasmaabobakermohamed.adapter.CategorieAdapter;
import com.example.farahasmaabobakermohamed.adapter.CategorieProduitAdapter;
import com.example.farahasmaabobakermohamed.adapter.NewProduitAdapter;
import com.example.farahasmaabobakermohamed.model.CategorieProduitModel;
import com.example.farahasmaabobakermohamed.model.Categoriemodel;
import com.example.farahasmaabobakermohamed.model.NewProduitModel;
import com.example.farahasmaabobakermohamed.model.UserModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeFragment extends Fragment {

    RecyclerView catRecyclerView,newRecyclerView,catproRecyclerView;

    //Voir tout
    TextView acheterShowAll,nouveauShowAll,categorieShowAll;

    //Produit
    CategorieAdapter categorieAdapter;
    List<Categoriemodel> categoriemodelList;

    //Nouveau Produit
    NewProduitAdapter newProduitAdapter;
    List<NewProduitModel> newProduitModelList;

    //Categorie Produit
    CategorieProduitAdapter categorieProduitAdapter;
    List<CategorieProduitModel> categorieProduitModels;
    FirebaseDatabase database;
    FirebaseAuth auth;
    FirebaseFirestore db;
    LinearLayout linearLayout;
    CircleImageView imageView1;
    public HomeFragment() {
        // Required empty public constructor
    }

    /*@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        db = FirebaseFirestore.getInstance();
        View root= inflater.inflate(R.layout.fragment_home, container, false);
        auth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
        imageView1=root.findViewById(R.id.profile_image);
        ProgressDialog progressDialog=new ProgressDialog(getActivity());
        catRecyclerView=root.findViewById(R.id.rec_category);
        newRecyclerView=root.findViewById(R.id.new_product_rec);
        catproRecyclerView=root.findViewById(R.id.popular_rec);

        acheterShowAll=root.findViewById(R.id.category_see_all);
        nouveauShowAll=root.findViewById(R.id.popular_see_all);
        categorieShowAll=root.findViewById(R.id.newProducts_see_all);

        database.getReference().child("Users").child(FirebaseAuth.getInstance().getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        UserModel userModel=snapshot.getValue(UserModel.class);
                        Glide.with(getContext()).load(userModel.getProfileImg()).into(imageView1);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), AjoutProduitActivity.class));
            }
        });

        acheterShowAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), ShowAllActivity.class);
                startActivity(intent);
            }
        });

        nouveauShowAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), ViewAllActivity.class);
                startActivity(intent);
            }
        });

        categorieShowAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), ShowAllActivity.class);
                startActivity(intent);
            }
        });


         linearLayout =root.findViewById(R.id.home_layout);
         linearLayout.setVisibility(View.GONE);

        progressDialog.setTitle("Bienvenue sur l'application FAMA-Ecommerce");
        progressDialog.setMessage("Veuillez patienter.....");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        // Produit
        catRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        categoriemodelList=new ArrayList<>();
        categorieAdapter =new CategorieAdapter(getContext(),categoriemodelList);
        catRecyclerView.setAdapter(categorieAdapter);
        db.collection("Produit")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Categoriemodel categoriemodel=document.toObject(Categoriemodel.class);
                                categoriemodelList.add(categoriemodel);
                                categorieAdapter.notifyDataSetChanged();
                                linearLayout.setVisibility(View.VISIBLE);
                                progressDialog.dismiss();
                            }
                        } else {
                            Toast.makeText(getActivity(),""+task.getException(),Toast.LENGTH_SHORT);
                        }
                    }
                });


        // Nouveau Produit
        newRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        newProduitModelList= new ArrayList<>();
        newProduitAdapter= new NewProduitAdapter(getContext(),newProduitModelList);
        newRecyclerView.setAdapter(newProduitAdapter);
        db.collection("NouveauProduit")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                NewProduitModel newProduitModel=document.toObject(NewProduitModel.class);
                                newProduitModelList.add(newProduitModel);
                                newProduitAdapter.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(getActivity(),""+task.getException(),Toast.LENGTH_SHORT);
                        }
                    }
                });


        // Categorie Produit

        catproRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        categorieProduitModels=new ArrayList<>();
        categorieProduitAdapter =new CategorieProduitAdapter(getContext(),categorieProduitModels);
        catproRecyclerView.setAdapter(categorieProduitAdapter);
        db.collection("CategorieProduit")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                CategorieProduitModel categorieProduitModel=document.toObject(CategorieProduitModel.class);
                                categorieProduitModels.add(categorieProduitModel);
                                categorieProduitAdapter.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(getActivity(),""+task.getException(),Toast.LENGTH_SHORT);
                        }
                    }
                });




        return root;
    }
}