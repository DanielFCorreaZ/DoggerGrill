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
                Intent intent=new Intent(miperfil.this,MainActivity.class);
                intent.putExtra("usu",nomb);
                intent.putExtra("mai",mais);
                startActivity(intent);
                break;
            case(R.id.Miperfil):
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
