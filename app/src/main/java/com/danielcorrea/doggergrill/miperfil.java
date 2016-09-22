package com.danielcorrea.doggergrill;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class miperfil extends AppCompatActivity {

    TextView tName,tmail;
    String mais,nomb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_miperfil);

        Bundle extra =getIntent().getExtras();
        mais=extra.getString("mais");
        nomb=extra.getString("usus");


        tName=(TextView) findViewById(R.id.nombre);
        tmail=(TextView) findViewById(R.id.email);
        tName.setText(nomb);
        tmail.setText(mais);
    }

    @Override
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
    }
}
