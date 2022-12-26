package com.example.sesion14;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sesion14.Clases.Producto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class frmNuevoCliente extends AppCompatActivity {
    private EditText txtNom, txtAp, txtDN, txtDirec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frm_nuevo_cliente);
        txtNom = findViewById(R.id.txtNombre);
        txtAp = findViewById(R.id.txtApellidos);
        txtDN= findViewById(R.id.txtDNI);
        txtDirec= findViewById(R.id.txtDireccion);
    }

    public void InsertarCliente(View view){
        try {
            Statement st=conexionBD().createStatement();
            ResultSet rs=st.executeQuery("INSERT INTO clientes VALUES('CLI00005','USU00001','"+txtNom.getText()+"','" +txtAp.getText()+"','"+txtDN.getText()+"','"+txtDirec.getText()+"')");
            Toast.makeText(this, "Cliente Agregado", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }

    }
    public Connection conexionBD(){
        Connection cnn = null;
        try {
            StrictMode.ThreadPolicy pol=new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(pol);
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            cnn = DriverManager.getConnection("jdbc:jtds:sqlserver://192.168.0.100:1433/DBProyecto;"+
                    "instance=MSSQLSERVER;user=sa;password=nerio123");
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }
        return cnn;
    }
}