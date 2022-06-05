package com.example.farahasmaabobakermohamed.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.farahasmaabobakermohamed.R;
import com.example.farahasmaabobakermohamed.model.AdresseModel;

import java.util.List;

public class AdresseAdapter extends RecyclerView.Adapter<AdresseAdapter.ViewHolder> {

    SelectedAdresse selectedAdresse;
    Context context;
    List<AdresseModel> adresseModelList;

    private RadioButton selectedRadiobtn;

    public AdresseAdapter(SelectedAdresse selectedAdresse, Context context, List<AdresseModel> adresseModelList) {
        this.selectedAdresse = selectedAdresse;
        this.context = context;
        this.adresseModelList = adresseModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adresse_items,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.adresses.setText(adresseModelList.get(position).getUserAdresse());
        holder.radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (AdresseModel adresseModel:adresseModelList){
                    adresseModel.setSelected(false);
                }
                adresseModelList.get(position).setSelected(true);
                if (selectedRadiobtn!=null){
                    selectedRadiobtn.setChecked(false);
                }
                selectedRadiobtn=(RadioButton) view;
                selectedRadiobtn.setChecked(true);
                selectedAdresse.setAdresse(adresseModelList.get(position).getUserAdresse());
            }
        });

    }

    @Override
    public int getItemCount() {
        return adresseModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView adresses;
        RadioButton radioButton;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            adresses=itemView.findViewById(R.id.address_add);
            radioButton=itemView.findViewById(R.id.select_address);

        }
    }

    public interface SelectedAdresse{
        void setAdresse(String adresse);
    }

}
