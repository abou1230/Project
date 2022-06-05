package com.example.farahasmaabobakermohamed.activite;

import androidx.annotation.NonNull;
import  androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.farahasmaabobakermohamed.R;
import com.example.farahasmaabobakermohamed.adapter.payAdapter;
import com.example.farahasmaabobakermohamed.fragment.ProfileFragment;
import com.example.farahasmaabobakermohamed.model.payModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Pay_Activity extends AppCompatActivity {
    TextView OverAllAmount;
    RecyclerView recyclerView;
    List<payModel> payModelList;
    payAdapter payAdapter;
    private FirebaseFirestore firestore;
    private FirebaseAuth auth;
    //Button retour;
    int OverTotalAllAmount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        auth=FirebaseAuth.getInstance();
        firestore=FirebaseFirestore.getInstance();
        //retour=findViewById(R.id.pay_return);

        LocalBroadcastManager.getInstance(this)
                .registerReceiver(mMessagereceiver,new IntentFilter("MyTotalAmount"));

        OverAllAmount=findViewById(R.id.textView3);
        recyclerView=findViewById(R.id.cart_rec);
        recyclerView.setLayoutManager(new LinearLayoutManager(Pay_Activity.this,RecyclerView.HORIZONTAL,false));
        payModelList=new ArrayList<>();
        payAdapter =new payAdapter(this,payModelList);
        recyclerView.setAdapter(payAdapter);
        firestore.collection("AddToCart").document(auth.getCurrentUser().getUid())
                .collection("Payer").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (DocumentSnapshot doc : task.getResult().getDocuments()) {
                        payModel payModel=doc.toObject(payModel.class);
                        payModelList.add(payModel);
                        payAdapter.notifyDataSetChanged();

                    }
                }
            }
        });


        /*retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Pay_Activity.this, ProfileFragment.class);
                startActivity(intent);
            }
        });*/


    }

    public BroadcastReceiver mMessagereceiver =new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int totalBill=intent.getIntExtra("totalAmount",0);
            OverAllAmount.setText("Le Total de Produit :"+totalBill+"DJF");
        }
    };


}