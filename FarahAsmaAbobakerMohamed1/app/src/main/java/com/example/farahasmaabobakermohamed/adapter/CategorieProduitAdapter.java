package com.example.farahasmaabobakermohamed.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.farahasmaabobakermohamed.R;
import com.example.farahasmaabobakermohamed.activite.ShowAllActivity;
import com.example.farahasmaabobakermohamed.activite.ViewAllActivity;
import com.example.farahasmaabobakermohamed.model.CategorieProduitModel;
import com.example.farahasmaabobakermohamed.model.NewProduitModel;

import java.util.List;

public class CategorieProduitAdapter extends RecyclerView.Adapter<CategorieProduitAdapter.ViewHolder>{
    private Context context;
    private List<CategorieProduitModel> list;

    public CategorieProduitAdapter(Context context, List<CategorieProduitModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.categorieproduit,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(list.get(position).getImg_url()).into(holder.ima);
        holder.nom.setText(list.get(position).getNom());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(context, ViewAllActivity.class);
                intent.putExtra("type",list.get(position).getType());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ima;
        TextView nom;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ima=itemView.findViewById(R.id.new_imga);
            nom=itemView.findViewById(R.id.new_nom);

        }
    }
}
