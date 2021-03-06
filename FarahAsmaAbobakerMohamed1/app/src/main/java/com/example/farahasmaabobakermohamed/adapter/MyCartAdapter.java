package com.example.farahasmaabobakermohamed.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.farahasmaabobakermohamed.R;
import com.example.farahasmaabobakermohamed.model.MyCartModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class MyCartAdapter extends RecyclerView.Adapter<MyCartAdapter.ViewHolder>{

    private Context context;
    private List<MyCartModel> list;
    int totalAmount=0;

    FirebaseAuth auth;
    FirebaseFirestore firestore;
    public MyCartAdapter(Context context, List<MyCartModel> list) {
        this.context = context;
        this.list = list;
        firestore=FirebaseFirestore.getInstance();
        auth=FirebaseAuth.getInstance();
    }
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.my_cart_items,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.date.setText(list.get(position).getCurrentdate());
        holder.time.setText(list.get(position).getCurrenttime());
        Glide.with(context).load(list.get(position).getImage()).into(holder.image);
        holder.nom.setText(list.get(position).getNomproduit());
        holder.prix.setText(list.get(position).getPrixproduit()+"DJF");
        holder.totalprix.setText(String.valueOf(list.get(position).getTotalprix())+"DJF");
        holder.totalquantite.setText(list.get(position).getTotalquantite());

        //delete de produit dans le panier
        holder.deletee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firestore.collection("AddToCart").document(auth.getCurrentUser().getUid())
                        .collection("User")
                        .document(list.get(position).getDocumentId())
                        .delete()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){
                                    totalAmount=totalAmount- list.get(position).getTotalprix();
                                    list.remove(list.get(position));
                                    notifyDataSetChanged();
                                    Toast.makeText(context,"Produit supprim?? du panier",Toast.LENGTH_SHORT).show();
                                }else {
                                    Toast.makeText(context,"Erreur"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                                }

                            }
                        });

            }
        });

        //total de panier
        totalAmount=totalAmount+ list.get(position).getTotalprix();
        Intent intent=new Intent("MyTotalAmount");
        intent.putExtra("totalAmount",totalAmount);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);

        //transfere de produit dans le panier
       /* holder.buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firestore.collection("AddToCart").document(auth.getCurrentUser().getUid())
                        .collection("User")
                        .document(list.get(position).getDocumentId())
                        .delete()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){
                                    totalAmount=totalAmount- list.get(position).getTotalprix();
                                    list.remove(list.get(position));
                                    notifyDataSetChanged();
                                    Toast.makeText(context,"Produit supprim?? du panier",Toast.LENGTH_SHORT).show();
                                }else {
                                    Toast.makeText(context,"Erreur"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                                }

                            }
                        });

            }
        });*/


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image,deletee;
        TextView nom,prix,time,date,totalquantite,totalprix;
        Button buy;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            date=itemView.findViewById(R.id.current_date);
            time=itemView.findViewById(R.id.current_time);
            image=itemView.findViewById(R.id.img_views);
            nom=itemView.findViewById(R.id.product_name);
            prix=itemView.findViewById(R.id.product_price);
            totalprix=itemView.findViewById(R.id.total_price);
            totalquantite=itemView.findViewById(R.id.total_quantity);
            deletee=itemView.findViewById(R.id.delete_panier);
            buy=itemView.findViewById(R.id.buy_now1);
        }
    }
}
