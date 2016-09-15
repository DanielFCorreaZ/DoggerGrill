package com.danielcorrea.doggergrill;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity {


    String user,correeo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Bundle extras=getIntent().getExtras();
        user = extras.getString("usu");
        correeo = extras.getString("mai");

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

                Intent intent= new Intent(MainActivity.this, miperfil.class);
                intent.putExtra("mais",correeo);
                intent.putExtra("usus",user);


                startActivity(intent);


                break;
            case(R.id.mPrincipal):
                break;

        }

        return super.onOptionsItemSelected(item);
    }
}
