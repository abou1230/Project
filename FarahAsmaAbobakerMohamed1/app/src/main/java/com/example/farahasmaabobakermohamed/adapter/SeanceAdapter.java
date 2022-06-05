package com.example.farahasmaabobakermohamed.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.farahasmaabobakermohamed.R;
import com.example.farahasmaabobakermohamed.model.SeanceModel;

import java.util.List;

public class SeanceAdapter extends RecyclerView.Adapter<SeanceAdapter.ViewHolder> {

    Context context;
    List<SeanceModel> seanceModelList;

    public SeanceAdapter(Context context, List<SeanceModel> seanceModelList) {
        this.context = context;
        this.seanceModelList = seanceModelList;
    }

    @NonNull
    @Override
    public SeanceAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.seance_app,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull SeanceAdapter.ViewHolder holder, int position) {
        Glide.with(context).load(seanceModelList.get(position).getImg_url()).into(holder.image);
        holder.nom.setText(seanceModelList.get(position).getNom());
        holder.description.setText(seanceModelList.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return seanceModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView nom,description;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.image1);
            nom=itemView.findViewById(R.id.nom1);
            description=itemView.findViewById(R.id.desc1);
        }
    }


}
