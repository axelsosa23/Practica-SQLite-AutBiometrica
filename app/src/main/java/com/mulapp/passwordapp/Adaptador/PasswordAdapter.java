package com.mulapp.passwordapp.Adaptador;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mulapp.passwordapp.Modelo.Password;
import com.mulapp.passwordapp.databinding.ItemPasswordBinding;

import java.util.ArrayList;
import java.util.List;

public class PasswordAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Password> passwordList;
    private Context context;

    public PasswordAdapter(List<Password> passwordList, Context context) {
        this.passwordList = passwordList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        return new PasswordViewHolder(ItemPasswordBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false), context);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
//        viewHolder.setIsRecyclable(false);

        ((PasswordViewHolder) viewHolder).setData(passwordList);

    }

    @Override
    public int getItemCount() {
        return passwordList.size();
    }

    static class PasswordViewHolder extends RecyclerView.ViewHolder {

        private final ItemPasswordBinding binding;

        PasswordViewHolder(ItemPasswordBinding itemPasswordBinding, Context context) {
            super(itemPasswordBinding.getRoot());
            binding = itemPasswordBinding;

            itemView.setOnClickListener(view -> {

            });

            binding.ibMasOpciones.setOnClickListener(view -> {

            });
        }

        void setData(List<Password> password) {
            binding.itemTitulo.setText(password.get(getAdapterPosition()).getTitulo());
            binding.itemCuenta.setText(password.get(getAdapterPosition()).getCuenta());
            binding.itemNombreUsuario.setText(password.get(getAdapterPosition()).getNombreUsuario());
            binding.itemPassword.setText(password.get(getAdapterPosition()).getPassword());
            binding.itemSitioWeb.setText(password.get(getAdapterPosition()).getSitioWeb());
            binding.itemNota.setText(password.get(getAdapterPosition()).getNota());
        }
    }

    /*@SuppressLint("NotifyDataSetChanged")
    public void addItem(JSONObject jsonObject) {
        model.add(jsonObject);
        notifyDataSetChanged();
    }

    public void clearItems() {
        model.clear();
    }*/
}
