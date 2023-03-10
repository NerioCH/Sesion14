package com.example.sesion14;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {

    private EditText txtUsu, txtP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtUsu = findViewById(R.id.txtUsuario);
        txtP = findViewById(R.id.txtPassword);
    }

    public void Consulta(View view){
        try {
            Statement st=conexionBD().createStatement();
            ResultSet rs=st.executeQuery("SELECT * FROM usuarios where logeo='"+txtUsu.getText().toString()+"' and clave='"+txtP.getText().toString()+"'");
            if(rs.next()){
                Toast.makeText(getApplicationContext(),"TOOOOOODO OK",Toast.LENGTH_SHORT).show();
                String tipoUser = rs.getString(2);
                if (tipoUser.compareTo("TPU00001") == 0) { // CLIENTE
                    Intent x = new Intent(MainActivity.this, frmCliente.class);
                    startActivity(x);
                }
                if (tipoUser.compareTo("TPU00002") == 0) { // ADMIN
                    Intent x = new Intent(MainActivity.this, frmAdmin.class);
                    startActivity(x);
                }
                if (tipoUser.compareTo("TPU00003") == 0) { // INVITADO
                    Intent x = new Intent(MainActivity.this, frmInvitado.class);
                    startActivity(x);
                }
            }
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