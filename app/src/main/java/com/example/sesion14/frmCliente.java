package com.example.sesion14;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class frmCliente extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frm_cliente);
    }
    public void Salir(View view) {
        Intent x = new Intent(frmCliente.this, MainActivity.class);
        startActivity(x);
    }

    public void RealizarPedidos(View view) {
        Intent x = new Intent(frmCliente.this, frmPedidos.class);
        startActivity(x);
    }
}