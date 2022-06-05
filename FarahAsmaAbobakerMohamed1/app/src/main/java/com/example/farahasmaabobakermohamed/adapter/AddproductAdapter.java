package com.example.farahasmaabobakermohamed.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.farahasmaabobakermohamed.R;
import com.example.farahasmaabobakermohamed.activite.DetailActivity;
import com.example.farahasmaabobakermohamed.activite.EditProfileActivity;
import com.example.farahasmaabobakermohamed.model.AddproductModel;

import java.util.List;

public class AddproductAdapter extends RecyclerView.Adapter<AddproductAdapter.ViewHolder> {

    Context context;
    List<AddproductModel>list;
    public AddproductAdapter(Context context,List<AddproductModel>list){
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_edit_profile,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(list.get(position).getImg_url()).into(holder.ima);
        holder.nom.setText(list.get(position).getNom());
        holder.desc.setText(list.get(position).getDescription());
        holder.prix.setText(String.valueOf(list.get(position).getPrix())+"DJF");
        holder.type.setText(list.get(position).getType());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ima;
        EditText nom,desc,prix,type;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ima=itemView.findViewById(R.id.profile_image2);
            nom=itemView.findViewById(R.id.profile_name2);
            desc=itemView.findViewById(R.id.profile_description2);
            prix=itemView.findViewById(R.id.profile_prix2);
            type=itemView.findViewById(R.id.profile_type2);
        }
    }
}
