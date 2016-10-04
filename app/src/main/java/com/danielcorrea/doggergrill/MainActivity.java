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


public class MainActivity extends AppCompatActivity {

    ViewPager vp;
    private String[] opciones = new String[] {"Perfil", "Menu Principal", "Platos","Clasificacion","Cerrar Sesion"};
    private DrawerLayout drawerLayout;
    private ListView listView;
    private ActionBarDrawerToggle drawerToggle;
    String user,correeo;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prefs=getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        editor=prefs.edit();
        ActionBar actionBar1 = getSupportActionBar();
        if (actionBar1 != null){
            actionBar1.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
            actionBar1.setDisplayHomeAsUpEnabled(true);
        }
        drawerLayout = (DrawerLayout) findViewById(R.id.contenedorPrincipal1);
        listView = (ListView) findViewById(R.id.menuIzq1);

        listView.setAdapter(new ArrayAdapter<String>(getSupportActionBar().getThemedContext(),
                android.R.layout.simple_list_item_1, opciones));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Fragment fragment = null;
                switch (i){
                    case(0): //fragment = new SupermanFragment();
                        Intent intent= new Intent(MainActivity.this, miperfil.class);
                        intent.putExtra("mais",correeo);
                        intent.putExtra("usus",user);


                        startActivity(intent);

                        break;
                    case(1): //fragment = new BatmanFragment();
                        Intent intent1= new Intent(MainActivity.this, lista.class);
                        intent1.putExtra("mais",correeo);
                        intent1.putExtra("usus",user);
                        startActivity(intent1);
                        break;
                    case(2): //fragment = new FlashFragment();

                        break;
                    case(3): Intent intent2= new Intent(MainActivity.this, clasi.class);
                        intent2.putExtra("mais",correeo);
                        intent2.putExtra("usus",user);


                        startActivity(intent2);
                       // finish();
                        break;
                    case(4):Intent intent3= new Intent(MainActivity.this, loggin.class);
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





        PagerAdapter page =new PagerAdapter(getSupportFragmentManager());
        vp = (ViewPager) findViewById(R.id.Pager);
        vp.setAdapter(page);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        Bundle extras=getIntent().getExtras();
        user = extras.getString("usus");
        correeo = extras.getString("mais");


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

        ActionBar.Tab tab = actionBar.newTab().setIcon(R.drawable.ham).setTabListener(tabListener);
        actionBar.addTab(tab);

        tab = actionBar.newTab().setIcon(R.drawable.dogger3).setTabListener(tabListener);
        actionBar.addTab(tab);

        tab = actionBar.newTab().setIcon(R.drawable.cost).setTabListener(tabListener);
        actionBar.addTab(tab);

        vp.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                getSupportActionBar().setSelectedNavigationItem(position);
            }
        });
      /*  FragmentManager fragm = getFragmentManager();
        FragmentTransaction fragt = fragm.beginTransaction();

        LogoFragment lofra =new LogoFragment();
        fragt.add(android.R.id.content, lofra).commit();*/

    }

    public class PagerAdapter extends FragmentPagerAdapter {
        public PagerAdapter(android.support.v4.app.FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0 : return new Producto1Fragment();
                case 1 : return new Producto2Fragment();
                case 2 : return new Producto3Fragment();
                default: return null;
            }

        }

        @Override
        public int getCount() {
            return 3;
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


   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
       // FragmentManager fragm = getFragmentManager();
       // FragmentTransaction fragt = fragm.beginTransaction();
        switch (id){
            case(R.id.Miperfil):

                Intent intent= new Intent(MainActivity.this, miperfil.class);
                intent.putExtra("mais",correeo);
                intent.putExtra("usus",user);


                startActivity(intent);
               // Producto1Fragment ham = new Producto1Fragment();
               // fragt.replace(android.R.id.content, ham).commit();

                break;
            case(R.id.mPrincipal):

                Intent intent1= new Intent(MainActivity.this, lista.class);
                intent1.putExtra("mais",correeo);
                intent1.putExtra("usus",user);


                startActivity(intent1);

                break;


            case(R.id.clas):

                Intent intent2= new Intent(MainActivity.this, clasi.class);
                intent2.putExtra("mais",correeo);
                intent2.putExtra("usus",user);


                startActivity(intent2);

                break;

            case(R.id.platos):



                break;

        }

        return super.onOptionsItemSelected(item);
    }*/



}
