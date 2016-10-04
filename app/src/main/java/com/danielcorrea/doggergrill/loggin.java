package com.danielcorrea.doggergrill;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class loggin extends AppCompatActivity {
    Button ok,reg;

    SharedPreferences prefs;
    SharedPreferences.Editor editor;

    EditText usser,paass;
    String user,contrasena, correeo;
    int l=0,t=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loggin);

        prefs=getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        editor=prefs.edit();

        reg = (Button) findViewById(R.id.reg);
        ok = (Button) findViewById(R.id.ok);
        paass = (EditText)findViewById(R.id.paass);
        usser = (EditText) findViewById(R.id.usser);

       // reg.setOnClickListener((View.OnClickListener) this);
        //ok.setOnClickListener((View.OnClickListener) this);
        if((prefs.getInt("var",-1)==1)){
            Intent intent= new Intent(loggin.this,lista.class);
            user=prefs.getString("user","");
            correeo=prefs.getString("mail","");

            intent.putExtra("usus",user);
            intent.putExtra("mais",correeo);

            startActivity(intent);
            finish();
        }

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String useer=usser.getText().toString();
                String pas=paass.getText().toString();

                if(TextUtils.isEmpty(useer)) {
                     t=0;
                   Toast.makeText(getApplicationContext(), "No ha ingresado el nombre de usuario" ,Toast.LENGTH_SHORT).show();
                } else{
                        if(useer.equals(prefs.getString("user",""))){
                             t=1;
                        }else{
                            Toast.makeText(getApplicationContext(), "No ha ingresado el nombre de usuario correcto" ,Toast.LENGTH_SHORT).show();
                        }
                }
                if(TextUtils.isEmpty(pas)) {
                     l=0;
                    Toast.makeText(getApplicationContext(), "No ha ingresado la contraseña" ,Toast.LENGTH_SHORT).show();
                } else{
                    if(pas.equals(prefs.getString("pass",""))){
                         l=1;
                    }else{
                        Toast.makeText(getApplicationContext(), "No ha ingresado la contraseña correcta" ,Toast.LENGTH_SHORT).show();

                    }
                }

                if((l==1 && t==1)){
                    Intent intent= new Intent(loggin.this,lista.class);
                    user=prefs.getString("user","");
                    correeo=prefs.getString("mail","");
                    intent.putExtra("usus",user);
                    intent.putExtra("mais",correeo);
                    editor.putInt("var",1);
                    editor.commit();
                    Log.d("var","var");
                    startActivity(intent);
                    finish();
                }


            }
        });

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inten = new Intent(loggin.this,register.class);
                startActivityForResult(inten,012);
            }
        });

    }

   /* public void onClick(View view) {
        int id = view.getId();

        switch (id) {
            case R.id.reg:
                Intent intent = new Intent(this, register.class);
                startActivityForResult(intent,1234);
                break;
            case R.id.ok:
                break;
        }

    }*/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 012 && resultCode == RESULT_OK){
             user = data.getExtras().getString("usuario");
            correeo = data.getExtras().getString("mail");
             contrasena = data.getExtras().getString("contrasena");
            editor.putString("user",user);
            editor.putString("pass",contrasena);
            editor.putString("mail",correeo);
            editor.commit();
            //Log.d("user",user);
            //Log.d("contraseña",contrasena);
            //Toast.makeText(this, "user: "+user+" contrasena: "+contrasena,Toast.LENGTH_SHORT).show();
        }
        if (requestCode==012 && resultCode == RESULT_CANCELED){
            Log.d("mensaje","no se cargaron datos");

        }


    }



}
