package com.example.farahasmaabobakermohamed.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.farahasmaabobakermohamed.R;
import com.example.farahasmaabobakermohamed.activite.AdresseActivity;
import com.example.farahasmaabobakermohamed.activite.AjoutProduitActivity;
import com.example.farahasmaabobakermohamed.activite.CartActivity;
import com.example.farahasmaabobakermohamed.activite.ChoisirActivity;
import com.example.farahasmaabobakermohamed.activite.EditProfileActivity;
import com.example.farahasmaabobakermohamed.activite.Pay_Activity;
import com.example.farahasmaabobakermohamed.activite.ViewAllActivity;
import com.example.farahasmaabobakermohamed.activite.login_activity;
import com.example.farahasmaabobakermohamed.activite.paiementActivity;
import com.example.farahasmaabobakermohamed.model.UserModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileFragment extends Fragment {
    CircleImageView imageView1;
    TextView nom;
    FirebaseAuth auth;
    FirebaseStorage storage;
    FirebaseDatabase database;

    //button
    Button btn1,btn2,btn3,btn4,btn5,btn6;
    private Uri profileUri;
    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_profile,container,false);
        auth=FirebaseAuth.getInstance();
        storage=FirebaseStorage.getInstance();
        database=FirebaseDatabase.getInstance();
        imageView1=view.findViewById(R.id.profile_image);
        nom=view.findViewById(R.id.profile_name_profile);

        //Button
        btn1=view.findViewById(R.id.btn_edit1);
        btn2=view.findViewById(R.id.btn_menu1);
        btn3=view.findViewById(R.id.btn_panier1);
        btn4=view.findViewById(R.id.btn_commande1);
        btn5=view.findViewById(R.id.btn_call1);
        btn6=view.findViewById(R.id.btn_logout1);

        database.getReference().child("Users").child(FirebaseAuth.getInstance().getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        UserModel userModel=snapshot.getValue(UserModel.class);
                        Glide.with(getContext()).load(userModel.getProfileImg()).into(imageView1);
                        nom.setText(userModel.getName());
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });



        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent home= new Intent(getActivity(), ChoisirActivity.class);
                startActivity(home);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getContext(), AjoutProduitActivity.class);
                startActivity(intent);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getContext(), CartActivity.class);
                startActivity(intent);
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getContext(), Pay_Activity.class);
                startActivity(intent);
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getContext(),"Error",Toast.LENGTH_SHORT).show();
                Intent intent= new Intent(getContext(), login_activity.class);
                startActivity(intent);
            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getContext(),login_activity.class);
                startActivity(intent);
            }
        });


        return view;
    }


}