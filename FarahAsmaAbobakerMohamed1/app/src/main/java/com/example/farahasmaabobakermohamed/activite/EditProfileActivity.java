package com.example.farahasmaabobakermohamed.activite;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.farahasmaabobakermohamed.R;
import com.example.farahasmaabobakermohamed.model.AddproductModel;
import com.example.farahasmaabobakermohamed.model.UserModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

public class EditProfileActivity extends AppCompatActivity {
    CircleImageView imageView1;
    EditText nom,prix,description,type;
    Button ajouter;
    FirebaseAuth auth;
    FirebaseStorage storage;
    FirebaseDatabase database;
    FirebaseFirestore firestore;
    AddproductModel addproductModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        auth= FirebaseAuth.getInstance();
        storage= FirebaseStorage.getInstance();
        database= FirebaseDatabase.getInstance();
        firestore=FirebaseFirestore.getInstance();
        imageView1=findViewById(R.id.profile_image2);
        nom=findViewById(R.id.profile_name2);
        description=findViewById(R.id.profile_description2);
        prix=findViewById(R.id.profile_prix2);
        type=findViewById(R.id.profile_type2);
        ajouter=findViewById(R.id.product_ajouter2);

        /*database.getReference().child("Users").child(FirebaseAuth.getInstance().getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        UserModel userModel=snapshot.getValue(UserModel.class);
                        Glide.with(EditProfileActivity.this).load(userModel.getProfileImg()).into(imageView1);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });*/

        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,1);
            }
        });
        ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addtoCart();
            }
        });


        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Categorie Produit")
                .setItems(categorieclass.categori_produit, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                       String categoriee=categorieclass.categori_produit[i];
                       type.setText(categoriee);
                    }
                });



    }

    /*public void ajouteruserprofile(){

    }

    /*@Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data.getData() != null){
            Uri profileUri= data.getData();
            imageView1.setImageURI(profileUri);

            final StorageReference reference=storage.getReference().child("profile_picture")
                    .child(FirebaseAuth.getInstance().getUid());
            reference.putFile(profileUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(EditProfileActivity.this,"Ajouter",Toast.LENGTH_SHORT).show();

                }
            });

        }
    }*/

    private void addtoCart(){
        final HashMap<String,Object> cartMap=new HashMap();
        cartMap.put("Img_url",imageView1);
        cartMap.put("nom",nom.getText().toString());
        cartMap.put("prix",prix.getText().toString());
        cartMap.put("description",description.getText().toString());
        cartMap.put("type",prix.getText().toString());

        firestore.collection("NouveauProduit").document(auth.getCurrentUser().getUid())
                .collection("new").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                Toast.makeText(EditProfileActivity.this,"Produit ajouter" ,Toast.LENGTH_SHORT).show();
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(EditProfileActivity.this,"Erreur",Toast.LENGTH_SHORT).show();
            }
        });


    }






}