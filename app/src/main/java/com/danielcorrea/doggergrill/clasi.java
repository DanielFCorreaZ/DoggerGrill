package com.danielcorrea.doggergrill;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class clasi extends AppCompatActivity {
    String mais,nomb;
    TextView most;
    EditText pun1,pun2;
    Button uno;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    private String[] opciones = new String[] {"Perfil", "Menu Principal", "Platos","Clasificacion","Cerrar Sesion"};
    private DrawerLayout drawerLayout;
    private ListView listView;
    private ActionBarDrawerToggle drawerToggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clasi);
        prefs=getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        editor=prefs.edit();

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        drawerLayout = (DrawerLayout) findViewById(R.id.contenedorPrincipal2);
        listView = (ListView) findViewById(R.id.menuIzq2);

        listView.setAdapter(new ArrayAdapter<String>(getSupportActionBar().getThemedContext(),
                android.R.layout.simple_list_item_1, opciones));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Fragment fragment = null;
                switch (i){
                    case(0): //fragment = new SupermanFragment();
                        Intent intent2= new Intent(clasi.this, miperfil.class);


                        startActivity(intent2);

                        break;
                    case(1): //fragment = new BatmanFragment();
                        Intent intent=new Intent(clasi.this,lista.class);

                        startActivity(intent);
                        break;

                    case(2): //fragment = new FlashFragment();
                        Intent intent1=new Intent(clasi.this,MainActivity.class);

                        startActivity(intent1);
                        break;
                    case(3):
                        break;
                    case(4):Intent intent3= new Intent(clasi.this, loggin.class);
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







        most=(TextView) findViewById(R.id.casi3);
        uno=(Button) findViewById(R.id.calis);
       pun1 = (EditText)findViewById(R.id.casi1);
        pun2 = (EditText) findViewById(R.id.casi2);

        most.setVisibility(View.INVISIBLE);

        uno.setOnClickListener(new View.OnClickListener() {
            //String un1=pun1.getText().toString();
            //String un2=pun2.getText().toString();
            @Override
            public void onClick(View view) {
        if(TextUtils.isEmpty(pun1.getText().toString())) {
            most.setVisibility(View.INVISIBLE);
            Toast.makeText(getApplicationContext(), "No ha ingresado clasificacion del restaurante" ,Toast.LENGTH_SHORT).show();
        } else{
            if(TextUtils.isEmpty(pun2.getText().toString())){
                most.setVisibility(View.INVISIBLE);
                Toast.makeText(getApplicationContext(), "No ha ingresado clasificacion de la aplicacion" ,Toast.LENGTH_SHORT).show();
            }else{
                most.setVisibility(View.VISIBLE);
            }
        }
            }
        });


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

    /*public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        switch (id){

            case(R.id.mPrincipal):
                Intent intent=new Intent(clasi.this,lista.class);
                intent.putExtra("usus",nomb);
                intent.putExtra("mais",mais);
                startActivity(intent);
                break;
            case(R.id.Miperfil):
                Intent intent2= new Intent(clasi.this, miperfil.class);
                intent2.putExtra("mais",nomb);
                intent2.putExtra("usus",mais);


                startActivity(intent2);
                break;

            case(R.id.platos):

                Intent intent1=new Intent(clasi.this,MainActivity.class);
                intent1.putExtra("usus",nomb);
                intent1.putExtra("mais",mais);
                startActivity(intent1);

                break;
            case(R.id.clas):



                break;
        }
        return super.onOptionsItemSelected(item);
    }*/
}
