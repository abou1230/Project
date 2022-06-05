package com.example.farahasmaabobakermohamed.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.farahasmaabobakermohamed.R;
import com.example.farahasmaabobakermohamed.activite.AjoutProduitActivity;
import com.example.farahasmaabobakermohamed.adapter.SeanceAdapter;
import com.example.farahasmaabobakermohamed.model.Categoriemodel;
import com.example.farahasmaabobakermohamed.model.SeanceModel;
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

public class SeanceFragment extends Fragment {

    RecyclerView recyclerView;
    FirebaseFirestore db;
    List<SeanceModel> seanceModelList;
    SeanceAdapter seanceAdapter;

    FirebaseDatabase database;
    FirebaseAuth auth;
    CircleImageView imageView1;
    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_seance,container,false);
        db=FirebaseFirestore.getInstance();
        auth=FirebaseAuth.getInstance();
        database= FirebaseDatabase.getInstance();
        imageView1=view.findViewById(R.id.profile_image);

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

        recyclerView=view.findViewById(R.id.seance_rec);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        seanceModelList=new ArrayList<>();
        seanceAdapter=new SeanceAdapter(getActivity(),seanceModelList);
        recyclerView.setAdapter(seanceAdapter);
        db.collection("Seance")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                SeanceModel seanceModel=document.toObject(SeanceModel.class);
                                seanceModelList.add(seanceModel);
                                seanceAdapter.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(getActivity(),"Erreur"+task.getException(),Toast.LENGTH_SHORT);
                        }
                    }
                });

        return view;
    }

}