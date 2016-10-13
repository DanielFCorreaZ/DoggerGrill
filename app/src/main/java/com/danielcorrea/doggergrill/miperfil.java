package com.danielcorrea.doggergrill;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.TextView;


public class miperfil extends AppCompatActivity {
    ViewPager vp;
    TextView tName,tmail;
    String mais,nomb;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    private String[] opciones = new String[] {"Perfil", "Menu Principal", "Platos","Clasificacion","Cerrar Sesion"};
    private DrawerLayout drawerLayout;
    private ListView listView;
    private ActionBarDrawerToggle drawerToggle;
    String name,price,description;
    int ids;
    int num,cliente,i=0;
    int[] pros=new int[]{7,7,7,7,7};
    Productos datos[]=
            new Productos[]{
                    new Productos(),
                    new Productos(),
                    new Productos(),
                    new Productos(),
                    new Productos()
            };
    ListView list;
    ContentValues dataBD;
    SQLiteDatabase dbContactos,dbMisFavoritos,dbProductos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_miperfil);
        prefs=getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        editor=prefs.edit();

        ContactosSQLiteHelper misfavoritos = new ContactosSQLiteHelper(this,"MisFavoritosBD", null, 1);
        ContactosSQLiteHelper productos = new ContactosSQLiteHelper(this,"ProductosBD", null, 1);
        ContactosSQLiteHelper contactos = new ContactosSQLiteHelper(this,"ContactosBD", null, 1);
        dbContactos = contactos.getWritableDatabase();
        dbProductos = productos.getWritableDatabase();
        dbMisFavoritos = misfavoritos.getWritableDatabase();

        ActionBar actionBar1 = getSupportActionBar();
        if (actionBar1 != null){
            actionBar1.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
            actionBar1.setDisplayHomeAsUpEnabled(true);
        }
        drawerLayout = (DrawerLayout) findViewById(R.id.contenedorPrincipal3);
        listView = (ListView) findViewById(R.id.menuIzq3);

        listView.setAdapter(new ArrayAdapter<String>(getSupportActionBar().getThemedContext(),
                android.R.layout.simple_list_item_1, opciones));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Fragment fragment = null;
                switch (i){
                    case(0): //fragment = new SupermanFragment();

                        break;
                    case(1): //fragment = new BatmanFragment();
                        Intent intent=new Intent(miperfil.this,lista.class);
                        startActivity(intent);
                        finish();
                        break;
                    case(2): //fragment = new FlashFragment();
                        Intent intent1=new Intent(miperfil.this,MainActivity.class);
                        startActivity(intent1);
                        finish();
                        break;
                    case(3):  Intent intent2= new Intent(miperfil.this, clasi.class);
                        startActivity(intent2);
                        break;
                    case(4):Intent intent3= new Intent(miperfil.this, loggin.class);
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




        cliente=prefs.getInt("num",-1);

        num=prefs.getInt("num",-1);
        Cursor c = dbContactos.rawQuery("select * from Contactos where id='"+num+"'",null);
        if(c.moveToFirst()) {

            nomb = c.getString(1);
            mais = c.getString(3);
        }
        Cursor c1 = dbMisFavoritos.rawQuery("select * from MisFavoritos where user='"+cliente+"'",null);
        if(c1.moveToFirst()) {
            i=0;
            pros=new int[]{7,7,7,7,7};
            do {

                pros[i]=c1.getInt(2);
                i++;
            } while(c1.moveToNext());
        }
        for(int j=0;j<=4;j++) {

            Cursor c2 = dbProductos.rawQuery("select * from Productos where id='" + (pros[j]) + "'", null);
            if (c2.moveToFirst()) {
                ids = c2.getInt(1);
                name = c2.getString(2);
                description = c2.getString(3);
                price = c2.getString(4);

            }
            if(pros[j]==7){
                datos[j]=new Productos();
            }else{
            datos[j]= new Productos(ids,price,name,description);
        }
        }
      /*  tName=(TextView) findViewById(R.id.nombre);
        tmail=(TextView) findViewById(R.id.email);
        tName.setText(nomb);
        tmail.setText(mais);*/


        PagerAdapter page1 =new PagerAdapter(getSupportFragmentManager());
        vp = (ViewPager) findViewById(R.id.Pager1);
        vp.setAdapter(page1);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);




        ActionBar.TabListener tabListener = new ActionBar.TabListener() {
            @Override
            public void onTabSelected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {
                vp.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {

            }

            @Override
            public void onTabReselected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {
            }
        };

        ActionBar.Tab tab = actionBar.newTab().setText("Perfil").setTabListener(tabListener);
        actionBar.addTab(tab);

        tab = actionBar.newTab().setText("Favoritos").setTabListener(tabListener);
        actionBar.addTab(tab);



        vp.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                getSupportActionBar().setSelectedNavigationItem(position);
            }
        });


    }

    public class PagerAdapter extends FragmentPagerAdapter {
        public PagerAdapter(android.support.v4.app.FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0 : return new PerfilFragment(nomb,mais);
                case 1 : return new FavFragment(datos);

                default: return null;
            }

        }

        @Override
        public int getCount() {
            return 2;
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
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        switch (id){

            case(R.id.mPrincipal):
                Intent intent=new Intent(miperfil.this,lista.class);
                intent.putExtra("usus",nomb);
                intent.putExtra("mais",mais);
                startActivity(intent);
                break;
            case(R.id.Miperfil):
                break;

            case(R.id.platos):

                Intent intent1=new Intent(miperfil.this,MainActivity.class);
                intent1.putExtra("usus",nomb);
                intent1.putExtra("mais",mais);
                startActivity(intent1);

                break;
            case(R.id.clas):

                Intent intent2= new Intent(miperfil.this, clasi.class);
                intent2.putExtra("mais",nomb);
                intent2.putExtra("usus",mais);


                startActivity(intent2);

                break;
        }
        return super.onOptionsItemSelected(item);
    }*/
}
