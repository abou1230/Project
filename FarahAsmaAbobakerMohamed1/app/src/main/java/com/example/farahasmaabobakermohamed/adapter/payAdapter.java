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
import com.example.farahasmaabobakermohamed.model.payModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class payAdapter extends RecyclerView.Adapter<payAdapter.ViewHolder>{
    private Context context;
    private List<payModel> list;
    int totalAmount=0;
    FirebaseAuth auth;
    FirebaseFirestore firestore;
    public payAdapter(Context context, List<payModel> list) {
        this.context = context;
        this.list = list;
        firestore=FirebaseFirestore.getInstance();
        auth=FirebaseAuth.getInstance();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.pay_items,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.date.setText(list.get(position).getCurrentdate());
        holder.time.setText(list.get(position).getCurrenttime());
        Glide.with(context).load(list.get(position).getImage()).into(holder.image);
        holder.nom.setText(list.get(position).getNomproduit());
        holder.prix.setText(list.get(position).getPrixproduit()+"DJF");
        holder.totalprix.setText(String.valueOf(list.get(position).getTotalprix())+"DJF");
        holder.totalquantite.setText(list.get(position).getTotalquantite());
        holder.status.setText(list.get(position).getStatus());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView nom,prix,time,date,totalquantite,totalprix,status;
        public ViewHolder(View view) {
            super(view);
            date=itemView.findViewById(R.id.current_date);
            time=itemView.findViewById(R.id.current_time);
            image=itemView.findViewById(R.id.img_views);
            nom=itemView.findViewById(R.id.product_name);
            prix=itemView.findViewById(R.id.product_price);
            totalprix=itemView.findViewById(R.id.total_price);
            totalquantite=itemView.findViewById(R.id.total_quantity);
            status=itemView.findViewById(R.id.status);
        }
    }
}
