package com.example.farahasmaabobakermohamed.activite;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.farahasmaabobakermohamed.R;
import com.example.farahasmaabobakermohamed.adapter.MyCartAdapter;
import com.example.farahasmaabobakermohamed.model.Categoriemodel;
import com.example.farahasmaabobakermohamed.model.MyCartModel;
import com.example.farahasmaabobakermohamed.model.NewProduitModel;
import com.example.farahasmaabobakermohamed.model.ShowAllModel;
import com.example.farahasmaabobakermohamed.model.ViewAllModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    ImageView detailimg,additem,removeitem;
    TextView notation,prix,description,nom,quantity;
    Button addTocart,buyTocart;

    int totalquantity=1;
    int totalprix=0;
    // New Profuit
    Categoriemodel categoriemodel;
    NewProduitModel newProduitModel=null;
    ShowAllModel showAllModel;
    ViewAllModel viewAllModel;
    MyCartAdapter cartAdapter;
    List<MyCartModel> cartModelList;
    MyCartModel myCartModel;
    private FirebaseFirestore firestore;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        auth= FirebaseAuth.getInstance();
        firestore= FirebaseFirestore.getInstance();
        quantity=findViewById(R.id.quantity);
        detailimg=findViewById(R.id.detailed_img);
        description=findViewById(R.id.detailed_desc);
        prix=findViewById(R.id.detailed_price);
        nom=findViewById(R.id.detailed_name);
        addTocart=findViewById(R.id.add_to_cart);
        notation=findViewById(R.id.rating);
        buyTocart=findViewById(R.id.buy_now);
        additem=findViewById(R.id.additem);
        removeitem=findViewById(R.id.removeitem);


        final Object obj= getIntent().getSerializableExtra("detailed");
        final Object objh= getIntent().getSerializableExtra("type");
        if(obj instanceof NewProduitModel){
            newProduitModel =(NewProduitModel) obj;
        }else if (obj instanceof ShowAllModel){
            showAllModel =(ShowAllModel) obj;
        }else if (obj instanceof Categoriemodel){
            categoriemodel= (Categoriemodel) obj;
        }else if(objh instanceof ViewAllModel){
            viewAllModel=(ViewAllModel) objh;
        }

        // Produit
        if(categoriemodel !=null){
            Glide.with(getApplicationContext()).load(categoriemodel.getImg_url()).into(detailimg);
            nom.setText(categoriemodel.getNom());
            description.setText(categoriemodel.getDescription());

        }

        // Nouveau Produit
        if(newProduitModel !=null){
            Glide.with(getApplicationContext()).load(newProduitModel.getImg_url()).into(detailimg);
            nom.setText(newProduitModel.getNom());
            description.setText(newProduitModel.getDescrip());
            notation.setText(newProduitModel.getNotation());
            prix.setText(String.valueOf(newProduitModel.getPrix()));
            totalprix=newProduitModel.getPrix() * totalquantity;
        }


        //Show all produit
        if(showAllModel !=null){
            Glide.with(getApplicationContext()).load(showAllModel.getImg_url()).into(detailimg);
            nom.setText(showAllModel.getNom());
            description.setText(showAllModel.getDescrip());
            notation.setText(showAllModel.getNotation());
            prix.setText(String.valueOf(showAllModel.getPrix()));
            totalprix=showAllModel.getPrix() * totalquantity;
        }

        //View all produit
        if(viewAllModel !=null){
            Glide.with(getApplicationContext()).load(viewAllModel.getImg_url()).into(detailimg);
            nom.setText(viewAllModel.getNom());
            description.setText(viewAllModel.getDescription());
            prix.setText(String.valueOf(viewAllModel.getPrix()));
            totalprix=viewAllModel.getPrix() * totalquantity;
        }


        //Buy now
        buyTocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buyCart();
                /*Intent intent=new Intent(DetailActivity.this,AdresseActivity.class);
                if (newProduitModel!=null){
                    intent.putExtra("item",newProduitModel);
                }
                if (showAllModel!=null){
                    intent.putExtra("item",showAllModel);
                }
                if (viewAllModel!=null){
                    intent.putExtra("item",viewAllModel);
                }
                startActivity(intent);
                cartModelList=new ArrayList<>();
                cartAdapter=new MyCartAdapter(DetailActivity.this,cartModelList);
                Intent intent=new Intent(DetailActivity.this,PayerActivity.class);
                intent.putExtra("itemslist",(Serializable) cartModelList);
                startActivity(intent);*/

            }
        });

        //Add To Cart
        addTocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addtoCart();
            }
        });

        additem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(totalquantity<10){
                    totalquantity++;
                    quantity.setText(String.valueOf(totalquantity));
                }
                if(newProduitModel !=null){
                    totalprix=newProduitModel.getPrix() * totalquantity;
                }
                if (showAllModel !=null){
                    totalprix=showAllModel.getPrix() * totalquantity;
                }
                if (viewAllModel !=null){
                    totalprix=viewAllModel.getPrix() * totalquantity;
                }
            }
        });

        removeitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(totalquantity >1){
                    totalquantity--;
                    quantity.setText(String.valueOf(totalquantity));
                }
            }
        });


    }



    private void addtoCart(){
        String saveCurrentTime,saveCurrentDate;
        Calendar calForDate=Calendar.getInstance();

        SimpleDateFormat currentDate =new SimpleDateFormat("MM dd,yyyy");
        saveCurrentDate=currentDate.format(calForDate.getTime());

        SimpleDateFormat currentTime =new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime=currentTime.format(calForDate.getTime());
        final HashMap<String,Object> cartMap=new HashMap();

        cartMap.put("Image",viewAllModel.getImg_url().toString());
        cartMap.put("nomproduit",nom.getText().toString());
        cartMap.put("prixproduit",prix.getText().toString());
        cartMap.put("Currenttime",saveCurrentTime);
        cartMap.put("Currentdate",saveCurrentDate);
        cartMap.put("totalquantite",quantity.getText().toString());
        cartMap.put("totalprix",totalprix);

        firestore.collection("AddToCart").document(auth.getCurrentUser().getUid())
                .collection("User").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                Toast.makeText(DetailActivity.this,"Produit ajouter" ,Toast.LENGTH_SHORT).show();
                finish();
            }
        });


    }



    private void buyCart(){
        String saveCurrentTime,saveCurrentDate;
        Calendar calForDate=Calendar.getInstance();

        SimpleDateFormat currentDate =new SimpleDateFormat("MM dd,yyyy");
        saveCurrentDate=currentDate.format(calForDate.getTime());

        SimpleDateFormat currentTime =new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime=currentTime.format(calForDate.getTime());
        final HashMap<String,Object> cartMap=new HashMap();

        cartMap.put("Image",viewAllModel.getImg_url().toString());
        cartMap.put("nomproduit",nom.getText().toString());
        cartMap.put("prixproduit",prix.getText().toString());
        cartMap.put("Currenttime",saveCurrentTime);
        cartMap.put("Currentdate",saveCurrentDate);
        cartMap.put("totalquantite",quantity.getText().toString());
        cartMap.put("totalprix",totalprix);
        cartMap.put("Status","Paiement reglé");

        firestore.collection("AddToCart").document(auth.getCurrentUser().getUid())
                .collection("Payer").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                Toast.makeText(DetailActivity.this,"Produit acheter" ,Toast.LENGTH_SHORT).show();
                finish();
            }
        });


    }











}