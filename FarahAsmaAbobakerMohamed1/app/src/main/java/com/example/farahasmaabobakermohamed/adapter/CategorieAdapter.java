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
import com.example.farahasmaabobakermohamed.activite.ShowAllActivity;
import com.example.farahasmaabobakermohamed.model.Categoriemodel;

import java.util.List;

public class CategorieAdapter extends RecyclerView.Adapter<CategorieAdapter.ViewHolder> {
    private Context context;
    private List<Categoriemodel> list;

    public CategorieAdapter(Context context, List<Categoriemodel> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.categorie_list,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(list.get(position).getImg_url()).into(holder.catImg);
        holder.catName.setText(list.get(position).getNom());
        holder.cat_desc.setText(list.get(position).getDescription());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(context, ShowAllActivity.class);
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
        ImageView catImg;
        TextView catName,cat_desc;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            catImg=itemView.findViewById(R.id.cat_img);
            catName=itemView.findViewById(R.id.cat_name);
            cat_desc=itemView.findViewById(R.id.cat_description);
        }
    }
}
