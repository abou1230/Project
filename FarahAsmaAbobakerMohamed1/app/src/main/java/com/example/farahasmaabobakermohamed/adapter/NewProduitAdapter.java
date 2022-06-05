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
import com.example.farahasmaabobakermohamed.activite.DetailActivity;
import com.example.farahasmaabobakermohamed.model.NewProduitModel;

import java.util.List;

public class NewProduitAdapter extends RecyclerView.Adapter<NewProduitAdapter.ViewHolder> {

    private Context context;
    private List<NewProduitModel> list;

    public NewProduitAdapter(Context context, List<NewProduitModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.new_product,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(list.get(position).getImg_url()).into(holder.image);
        holder.nom.setText(list.get(position).getNom());
        holder.prix.setText(String.valueOf(list.get(position).getPrix()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(context, DetailActivity.class);
                intent.putExtra("detailed",list.get(position));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView nom,prix;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.new_img);
            nom=itemView.findViewById(R.id.new_name);
            prix=itemView.findViewById(R.id.new_price);

        }
    }
}
