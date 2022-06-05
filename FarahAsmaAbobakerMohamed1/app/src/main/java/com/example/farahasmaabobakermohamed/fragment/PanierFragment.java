package com.example.farahasmaabobakermohamed.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.farahasmaabobakermohamed.R;
import com.example.farahasmaabobakermohamed.activite.AdresseActivity;
import com.example.farahasmaabobakermohamed.activite.AjoutProduitActivity;
import com.example.farahasmaabobakermohamed.adapter.MyCartAdapter;
import com.example.farahasmaabobakermohamed.model.MyCartModel;
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

public class PanierFragment extends Fragment {
    TextView OverAllAmount;
    int OverTotalAllAmount;
    RecyclerView recyclerView;
    List<MyCartModel> cartModelList;
    MyCartAdapter cartAdapter;
    private FirebaseFirestore firestore;
    private FirebaseAuth auth;
    Button buy;
    FirebaseDatabase database;
    CircleImageView imageView1;
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_panier,container,false);
        auth= FirebaseAuth.getInstance();
        firestore= FirebaseFirestore.getInstance();

        buy=view.findViewById(R.id.buy_nows);
        LocalBroadcastManager.getInstance(getContext())
                .registerReceiver(mMessagereceiver,new IntentFilter("MyTotalAmount"));

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

        OverAllAmount=view.findViewById(R.id.textView3);

        recyclerView=view.findViewById(R.id.cart_rec);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        cartModelList=new ArrayList<>();
        cartAdapter =new MyCartAdapter(getContext(),cartModelList);
        recyclerView.setAdapter(cartAdapter);

        firestore.collection("AddToCart").document(auth.getCurrentUser().getUid())
                .collection("User").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        String documentId= document.getId();
                        MyCartModel myCartModel=document.toObject(MyCartModel.class);
                        myCartModel.setDocumentId(documentId);
                        cartModelList.add(myCartModel);
                        cartAdapter.notifyDataSetChanged();

                    }
                }
            }
        });

        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), AdresseActivity.class));
            }
        });



        return view;
    }

    public BroadcastReceiver mMessagereceiver =new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int totalBill=intent.getIntExtra("totalAmount",0);
            OverAllAmount.setText("Le Total de Produit :"+totalBill+"DJF");
        }
    };
}
