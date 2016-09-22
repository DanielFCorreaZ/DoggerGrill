package com.danielcorrea.doggergrill;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity {

    ViewPager vp;

    String user,correeo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
    }
}
