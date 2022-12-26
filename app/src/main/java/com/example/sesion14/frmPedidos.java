package com.example.sesion14;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sesion14.Clases.Producto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class frmPedidos extends AppCompatActivity {

    List<Producto> productoList;

    RecyclerView recyclerProductos;
    LinearLayoutManager linearLayoutManager;
    ProductoAdapter productoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frm_pedidos);

        recyclerProductos = findViewById(R.id.recyclerProductos);
        productoList = new ArrayList<>();
        linearLayoutManager = new LinearLayoutManager(this);
    }
    public void ConsultaProductos(View view){
        Toast.makeText(this, "Cargando produtos...", Toast.LENGTH_SHORT).show();
        try {
            Statement st=conexionBD().createStatement();
            ResultSet rs=st.executeQuery("SELECT * FROM productos");
            while (rs.next()) {
                Producto producto = new Producto();

                producto.setNombre(rs.getString(2));
                producto.setCodigo(rs.getString(1));
                producto.setPrecio_venta(Float.parseFloat(rs.getString(4)));
                productoList.add(producto);
            }

            recyclerProductos.setLayoutManager(linearLayoutManager);
            productoAdapter = new frmPedidos.ProductoAdapter(frmPedidos.this, productoList);
            recyclerProductos.setAdapter(productoAdapter);
            rs.close();
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

    public class ProductoAdapter extends RecyclerView.Adapter<frmPedidos.ProductoAdapter.AdapterViewHolder>{

        LayoutInflater inflater;
        List<Producto> producto;
        Context ctx;

        public ProductoAdapter(Context ctx, List<Producto> producto){
            this.ctx = ctx;
            this.inflater = LayoutInflater.from(ctx);
            this.producto = producto;

        }

        @NonNull
        @Override
        public frmPedidos.ProductoAdapter.AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout.item_producto, parent, false);
            return new frmPedidos.ProductoAdapter.AdapterViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull AdapterViewHolder holder, int position) {
            holder.nombre.setText(producto.get(position).getNombre());
            holder.id.setText(producto.get(position).getCodigo());
            holder.precio.setText(producto.get(position).getPrecio_venta().toString());
        }

        @Override
        public int getItemCount() {
            return producto.size();
        }

        public class AdapterViewHolder extends RecyclerView.ViewHolder{
            TextView id;
            TextView nombre;
            TextView precio;
            public AdapterViewHolder(@NonNull View itemView) {
                super(itemView);
                id = itemView.findViewById(R.id.idProducto);
                nombre = itemView.findViewById(R.id.nombreProducto);
                precio = itemView.findViewById(R.id.precioProducto);
            }
        }
    }

}
