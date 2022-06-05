package com.example.farahasmaabobakermohamed.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.farahasmaabobakermohamed.R;
import com.example.farahasmaabobakermohamed.adapter.CategorieAdapter;
import com.example.farahasmaabobakermohamed.adapter.CategorieProduitAdapter;
import com.example.farahasmaabobakermohamed.adapter.NewProduitAdapter;
import com.example.farahasmaabobakermohamed.adapter.ViewAllAdapter;
import com.example.farahasmaabobakermohamed.model.CategorieProduitModel;
import com.example.farahasmaabobakermohamed.model.Categoriemodel;
import com.example.farahasmaabobakermohamed.model.NewProduitModel;
import com.example.farahasmaabobakermohamed.model.ViewAllModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class RechercheFragment extends Fragment {

    RecyclerView catRecyclerView,newRecyclerView,catproRecyclerView;


    private List<ViewAllModel> viewAllModelList;
    private RecyclerView recyclerView;
    private ViewAllAdapter viewAllAdapter;
    EditText search;


    FirebaseFirestore db;
    LinearLayout linearLayout;
    public RechercheFragment() {
    }


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        db = FirebaseFirestore.getInstance();
        View view=inflater.inflate(R.layout.fragment_recherche,container,false);

        search=view.findViewById(R.id.search_box);
        recyclerView=view.findViewById(R.id.recy_search);
        viewAllModelList=new ArrayList<>();
        viewAllAdapter=new ViewAllAdapter(getContext(),viewAllModelList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(viewAllAdapter);
        recyclerView.setHasFixedSize(true);
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().isEmpty()){
                    viewAllModelList.clear();
                    viewAllAdapter.notifyDataSetChanged();
                }else{
                    rechercheProduit(editable.toString());
                }

            }
        });

        return view;
    }

    public void rechercheProduit(String type){
        if(!type.isEmpty()){
            db.collection("Produit").whereEqualTo("type",type).get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful() && task.getResult() !=null){
                                viewAllModelList.clear();
                                viewAllAdapter.notifyDataSetChanged();
                                for (DocumentSnapshot doc :task.getResult().getDocuments()){
                                    ViewAllModel viewAllModel=doc.toObject(ViewAllModel.class);
                                    viewAllModelList.add(viewAllModel);
                                    viewAllAdapter.notifyDataSetChanged();
                                }
                            }else{
                                Toast.makeText(getActivity(),"Erreur"+task.getException(),Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
        }
    }



}
