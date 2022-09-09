package com.mulapp.passwordapp.Fragmentos;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.mulapp.passwordapp.Adaptador.PasswordAdapter;
import com.mulapp.passwordapp.BaseDeDatos.BDHelper;
import com.mulapp.passwordapp.BaseDeDatos.Constants;
import com.mulapp.passwordapp.OpcionesPassword.AgregarPassword;
import com.mulapp.passwordapp.R;
import com.mulapp.passwordapp.databinding.FragmentFTodasBinding;

public class F_Todas extends Fragment {

    private FragmentFTodasBinding binding;
    private BDHelper bdHelper;
    private Dialog dialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFTodasBinding.inflate(inflater, container, false);

        bdHelper = new BDHelper(getActivity());
        dialog = new Dialog(getActivity());

        binding.FABAgregarPassword.setOnClickListener(view -> {
            startActivity(new Intent(getActivity(), AgregarPassword.class));
        });

        cargarRegistros();
        return binding.getRoot();
    }

    private void cargarRegistros() {
        PasswordAdapter adapter = new PasswordAdapter(bdHelper.getRegistros(Constants.C_TITULO + " ASC"), getActivity());
        binding.rvRegistros.setAdapter(adapter);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_fragmento_todos, menu);

        MenuItem item = menu.findItem(R.id.buscar_registros);
        SearchView searchView = (SearchView)item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                //BUSCAR POR EL TECLADO
                buscarRegistros(s);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                //BUSCAR MIENTRAS ESCRIBE
                buscarRegistros(s);
                return true;
            }
        });

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.Numero_Registros){
            visualizarTotalRegistros();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    private void visualizarTotalRegistros(){
        TextView total;
        Button btnEntendidoTotal;

        dialog.setContentView(R.layout.dialogo_total_registros);

        total = dialog.findViewById(R.id.total);
        btnEntendidoTotal = dialog.findViewById(R.id.btnEntendidoTotal);

        total.setText(" " + bdHelper.getNumeroRegistros());

        btnEntendidoTotal.setOnClickListener(view -> {
            dialog.dismiss();
        });

        dialog.show();
        dialog.setCancelable(false);
    }

    private void buscarRegistros(String consulta){
        PasswordAdapter passwordAdapter = new PasswordAdapter(bdHelper.buscarRegistros(consulta), getActivity());
        binding.rvRegistros.setAdapter(passwordAdapter);
    }
}