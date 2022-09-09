package com.mulapp.passwordapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.mulapp.passwordapp.Fragmentos.F_Ajustes;
import com.mulapp.passwordapp.Fragmentos.F_Todas;
import com.mulapp.passwordapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ActivityMainBinding binding;

    private boolean dobleToque = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        setSupportActionBar(binding.toolbar);

        binding.navView.setNavigationItemSelectedListener(this);
        binding.navView.setItemIconTintList(null);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, binding.drawerLayout, binding.toolbar, R.string.Nav_drawe_open, R.string.Nav_drawe_close);

        binding.drawerLayout.addDrawerListener(toggle);

        toggle.syncState();

        //FRAGMENTO POR DEFECTO AL INICIAR LA ACTIVITY
        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new F_Todas()).commit();
            binding.navView.setCheckedItem(R.id.Opcion_Todas);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.Opcion_Todas){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new F_Todas()).commit();
        } else if (id == R.id.Opcion_Ajustes){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new F_Ajustes()).commit();
        } else if (id == R.id.Opcion_Salir){
            Toast.makeText(this, "Sesion cerrada", Toast.LENGTH_SHORT).show();
        }

        binding.drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (dobleToque){
            super.onBackPressed();
            return;
        }

        this.dobleToque = true;
        Toast.makeText(this, "Presione nuevamente para salir", Toast.LENGTH_SHORT).show();

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                dobleToque = false;
            }
        }, 2000);
    }
}