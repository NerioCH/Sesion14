package com.example.sesion14;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class frmAdmin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frm_admin);
    }
    public void NuevoCliente(View view) {
        Intent x = new Intent(frmAdmin.this, frmNuevoCliente.class);
        startActivity(x);
    }
}