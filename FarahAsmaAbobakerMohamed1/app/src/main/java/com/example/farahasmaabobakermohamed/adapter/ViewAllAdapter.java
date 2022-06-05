package com.example.farahasmaabobakermohamed.adapter;

import android.annotation.SuppressLint;
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
import com.example.farahasmaabobakermohamed.activite.DetailActivity;
import com.example.farahasmaabobakermohamed.activite.ViewAllActivity;
import com.example.farahasmaabobakermohamed.model.Categoriemodel;
import com.example.farahasmaabobakermohamed.model.ViewAllModel;

import java.util.List;

public class ViewAllAdapter extends RecyclerView.Adapter<ViewAllAdapter.ViewHolder> {

    Context context;
    List<ViewAllModel> list;

    public ViewAllAdapter(Context context, List<ViewAllModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_all_items,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(list.get(position).getImg_url()).into(holder.ima);
        holder.nom.setText(list.get(position).getNom());
        holder.desc.setText(list.get(position).getDescription());
        holder.prix.setText(String.valueOf(list.get(position).getPrix())+"DJF");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, DetailActivity.class);
                intent.putExtra("type", list.get(position));
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
        TextView nom,desc,prix;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ima=itemView.findViewById(R.id.view_img_cat);
            nom=itemView.findViewById(R.id.view_nom_cat);
            desc=itemView.findViewById(R.id.view_desc_cat);
            prix=itemView.findViewById(R.id.view_prix_cat);
        }
    }
}
