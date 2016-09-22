package com.danielcorrea.doggergrill;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class clasi extends AppCompatActivity {
    String mais,nomb;
    TextView most;
    EditText pun1,pun2;
    Button uno;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clasi);


        Bundle extra =getIntent().getExtras();
        mais=extra.getString("mais");
        nomb=extra.getString("usus");

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

    public boolean onCreateOptionsMenu(Menu menu) {
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
    }
}
