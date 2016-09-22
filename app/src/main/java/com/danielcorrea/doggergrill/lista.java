package com.danielcorrea.doggergrill;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.zip.Inflater;

public class lista extends AppCompatActivity {

    //final String[] productos = new String[]{"Hanburguesa","Perros","Costillas BBQ","Sanduichs","Cervezas"};

    private Productos[] datos=
            new Productos[]{
                    new Productos(R.drawable.item1,"16000","Hamburguesa","Hamburguesa de dos carnes y doble queso"),
                    new Productos(R.drawable.item2,"12000","Perros","Con todo lo que le puedas hechar"),
                    new Productos(R.drawable.item3,"18000","Costillas BBQ","Costillas con el mejor sabor Grill"),
                    new Productos(R.drawable.item4,"15000","Sanduich","Los mejores aplastados de la ciudad")
            };

    ListView list;
    String user,correeo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        Bundle extras=getIntent().getExtras();
        user = extras.getString("usus");
        correeo = extras.getString("mais");

        Adapter adaptador = new Adapter(this,datos);
        list= (ListView) findViewById(R.id.list);
        list.setAdapter(adaptador);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }


    class Adapter extends ArrayAdapter<Productos>{

        public Adapter(Context context,Productos[] datos) {
            super(context,R.layout.layout_produc,datos);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater= LayoutInflater.from(getContext());
            View item= inflater.inflate(R.layout.layout_produc,null);



            TextView nombre=(TextView) item.findViewById(R.id.nompro);
            nombre.setText(datos[position].getNombre());

            TextView descrip=(TextView) item.findViewById(R.id.descpro);
            descrip.setText(datos[position].getDescripcion());

            TextView prec=(TextView) item.findViewById(R.id.prepro);
            prec.setText(datos[position].getPrecio());


            ImageView imagen=(ImageView) item.findViewById(R.id.imapro);
            imagen.setImageResource(datos[position].getIdima());


            return (item);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        switch (id){
            case(R.id.Miperfil):

                Intent intent= new Intent(lista.this, miperfil.class);
                intent.putExtra("mais",correeo);
                intent.putExtra("usus",user);


                startActivity(intent);


                break;
            case(R.id.mPrincipal):
                break;

            case(R.id.platos):
                Intent intent1= new Intent(lista.this, MainActivity.class);
                intent1.putExtra("mais",correeo);
                intent1.putExtra("usus",user);
                startActivity(intent1);
                break;
            case(R.id.clas):

                Intent intent2= new Intent(lista.this, clasi.class);
                intent2.putExtra("mais",correeo);
                intent2.putExtra("usus",user);


                startActivity(intent2);

                break;

        }

        return super.onOptionsItemSelected(item);
    }



}
