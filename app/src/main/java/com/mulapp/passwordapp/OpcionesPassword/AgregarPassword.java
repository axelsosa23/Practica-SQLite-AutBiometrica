package com.mulapp.passwordapp.OpcionesPassword;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.mulapp.passwordapp.BaseDeDatos.BDHelper;
import com.mulapp.passwordapp.MainActivity;
import com.mulapp.passwordapp.R;
import com.mulapp.passwordapp.databinding.ActivityAgregarPasswordBinding;

public class AgregarPassword extends AppCompatActivity {

    private ActivityAgregarPasswordBinding binding;

    private BDHelper bdHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_agregar_password);

        bdHelper = new BDHelper(this);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle("");
    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_guardar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.Guardar_Password){
            guardarPassword();
        }
        return super.onOptionsItemSelected(item);
    }

    private void guardarPassword(){
        if (!binding.EtTitulo.getText().toString().trim().isEmpty()){
            //Almacenamos el tiempo del dispositivo
            String tiempo = "" + System.currentTimeMillis();

            long id = bdHelper.insertarRegistro(
                    "" + binding.EtTitulo.getText().toString().trim(),
                    "" + binding.EtCuenta.getText().toString().trim(),
                    "" + binding.EtNombreUsuario.getText().toString().trim(),
                    "" + binding.EtPassword.getText().toString().trim(),
                    "" + binding.EtSitioWeb.getText().toString().trim(),
                    "" + binding.EtNota.getText().toString().trim(),
                    "" + tiempo,
                    "" + tiempo
            );

            Toast.makeText(this, "Se ha guardado con éxito: " + id, Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, MainActivity.class));
            finish();
        } else {
            Toast.makeText(this, "Faltan datos", Toast.LENGTH_SHORT).show();
        }
    }
}