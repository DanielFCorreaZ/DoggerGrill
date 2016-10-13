package com.danielcorrea.doggergrill;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
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

    private String[] opciones = new String[] {"Perfil", "Menu Principal", "Platos","Clasificacion","Cerrar Sesion"};
    private DrawerLayout drawerLayout;
    private ListView listView;
    private ActionBarDrawerToggle drawerToggle;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;

    ContentValues dataBD;
    SQLiteDatabase dbContactos,dbProductos;

    int[] ima = new int[]{R.drawable.item1,R.drawable.item2,R.drawable.item3,R.drawable.item4,R.drawable.beer};
    String[] prec = new String[]{"16000","12000","18000","15000","5000"};
    String[] nombr = new String[]{"Hamburguesa","Perros","Costillas BBQ","Sandwich","Cerveceria"};
    String[] descr = new String[]{"Hamburguesa de dos carnes y doble queso","Con todo lo que le puedas hechar","Costillas con el mejor sabor Grill","Los mejores aplastados de la ciudad","Las mejores cervezas nacionales"};

    private Productos datos[]=
            new Productos[]{
                    new Productos(),
                    new Productos(),
                    new Productos(),
                    new Productos(),
                    new Productos()
            };

    ListView list;
    String name,price,description;
    int ids;
    int[] idp= new int[]{0,1,2,3,4};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        prefs=getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        editor=prefs.edit();

        ContactosSQLiteHelper contactos = new ContactosSQLiteHelper(this,"ContactosBD", null, 1);
        ContactosSQLiteHelper productos = new ContactosSQLiteHelper(this,"ProductosBD", null, 1);
        dbContactos = contactos.getWritableDatabase();
        dbProductos = productos.getWritableDatabase();
        dataBD = new ContentValues();

        for( int i=0;i<=4;i++){
            dataBD.put("idima",ima[i]);
            dataBD.put("nombre",nombr[i]);
            dataBD.put("descrip",descr[i]);
            dataBD.put("precio",prec[i]);

            dbProductos.insert("Productos",null,dataBD);
            Cursor c = dbProductos.rawQuery("select * from Productos where nombre='"+nombr[i]+"'",null);
            if(c.moveToFirst()) {

                idp[i] = c.getInt(0);
                }

            Cursor c1 = dbProductos.rawQuery("select * from Productos where id='"+idp[i]+"'",null);
            if(c1.moveToFirst()) {
                ids = c1.getInt(1);
                name = c1.getString(2);
                description = c1.getString(3);
                price = c1.getString(4);
            }

           datos[i]= new Productos(ids,price,name,description);
        }

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        drawerLayout = (DrawerLayout) findViewById(R.id.contenedorPrincipal);
        listView = (ListView) findViewById(R.id.menuIzq);

        listView.setAdapter(new ArrayAdapter<String>(getSupportActionBar().getThemedContext(),
                android.R.layout.simple_list_item_1, opciones));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Fragment fragment = null;
                switch (i){
                    case(0): //fragment = new SupermanFragment();
                        Intent intent= new Intent(lista.this, miperfil.class);



                        startActivity(intent);

                        break;
                    case(1): //fragment = new BatmanFragment();

                        break;
                    case(2): //fragment = new FlashFragment();
                        Intent intent1= new Intent(lista.this, MainActivity.class);

                        startActivity(intent1);
                        break;
                    case(3): Intent intent2= new Intent(lista.this, clasi.class);



                        startActivity(intent2);
                       // finish();
                        break;
                    case(4):Intent intent3= new Intent(lista.this, loggin.class);
                        editor.putInt("var",-1);
                        editor.commit();
                        startActivity(intent3);
                        finish();
                        break;
                }
                if (i != 3) {
                   // FragmentManager fragmentManager = getSupportFragmentManager();
                   // fragmentManager.beginTransaction().replace(R.id.contenedorFrame, fragment).commit();

                }
                listView.setItemChecked(i,true);
                drawerLayout.closeDrawer(listView);
            }
        });

        drawerToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.abierto, R.string.cerrado);

        drawerLayout.setDrawerListener(drawerToggle);








        Adapter adaptador = new Adapter(this,datos);
        list= (ListView) findViewById(R.id.list);
        list.setAdapter(adaptador);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case(0):editor.putInt("val",0);
                        editor.putInt("pro",idp[0]);
                        break;
                    case(1):editor.putInt("val",1);
                        editor.putInt("pro",idp[1]);
                        break;
                    case(2):editor.putInt("val",2);
                        editor.putInt("pro",idp[2]);
                        break;
                    case(3):editor.putInt("val",3);
                        editor.putInt("pro",idp[3]);
                        break;
                    case(4):editor.putInt("val",4);
                        editor.putInt("pro",idp[4]);
                        break;

                } editor.commit();
                Intent intent4= new Intent(lista.this, prom.class);
                startActivity(intent4);

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
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                drawerLayout.openDrawer(Gravity.LEFT);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }





    /*@Override
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
    }*/



}
