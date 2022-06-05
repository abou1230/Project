package com.example.farahasmaabobakermohamed.activite;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
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
import com.example.farahasmaabobakermohamed.adapter.MyCartAdapter;
import com.example.farahasmaabobakermohamed.model.MyCartModel;
import com.example.farahasmaabobakermohamed.model.ShowAllModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    TextView OverAllAmount;
    RecyclerView recyclerView;
    List<MyCartModel>cartModelList;
    MyCartAdapter cartAdapter;
    private FirebaseFirestore firestore;
    private FirebaseAuth auth;

    int OverTotalAllAmount;
    Button buy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        auth=FirebaseAuth.getInstance();
        firestore=FirebaseFirestore.getInstance();
        buy=findViewById(R.id.buy_now1);
        LocalBroadcastManager.getInstance(this)
                .registerReceiver(mMessagereceiver,new IntentFilter("MyTotalAmount"));

        OverAllAmount=findViewById(R.id.textView3);
        recyclerView=findViewById(R.id.cart_rec);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        cartModelList=new ArrayList<>();
        cartAdapter =new MyCartAdapter(this,cartModelList);
        recyclerView.setAdapter(cartAdapter);

        firestore.collection("AddToCart").document(auth.getCurrentUser().getUid())
                .collection("User").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (DocumentSnapshot doc : task.getResult().getDocuments()) {
                        MyCartModel myCartModel=doc.toObject(MyCartModel.class);
                        cartModelList.add(myCartModel);
                        cartAdapter.notifyDataSetChanged();

                    }
                }
            }
        });


    }


    public BroadcastReceiver mMessagereceiver =new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int totalBill=intent.getIntExtra("totalAmount",0);
            OverAllAmount.setText("Le Total de Produit :"+totalBill+"DJF");
        }
    };



}