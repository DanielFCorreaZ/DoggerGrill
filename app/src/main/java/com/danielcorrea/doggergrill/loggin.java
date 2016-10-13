package com.danielcorrea.doggergrill;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
    int id;
    String contrasena, correeo, contra="",correo,useer,pas;
    int l=0,t=0;

    ContentValues dataBD;
    SQLiteDatabase dbContactos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loggin);

        ContactosSQLiteHelper contactos = new ContactosSQLiteHelper(this,"ContactosBD", null, 1);
        dbContactos = contactos.getWritableDatabase();


        prefs=getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        editor=prefs.edit();

        reg = (Button) findViewById(R.id.reg);
        ok = (Button) findViewById(R.id.ok);
        paass = (EditText)findViewById(R.id.paass);
        usser = (EditText) findViewById(R.id.usser);


        if((prefs.getInt("var",-1)==1)){
            Intent intent= new Intent(loggin.this,lista.class);
            startActivity(intent);
            finish();
        }

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                useer=usser.getText().toString();
                 pas=paass.getText().toString();

                if(TextUtils.isEmpty(useer)) {
                     t=0;
                   Toast.makeText(getApplicationContext(), "No ha ingresado el nombre de usuario" ,Toast.LENGTH_SHORT).show();
                } else{Cursor c = dbContactos.rawQuery("select * from Contactos where nombre='"+useer+"'",null);
                    if(c.moveToFirst()) {
                        id = c.getInt(0);
                        contra = c.getString(2);
                        correo = c.getString(3);
                    }
                        if(TextUtils.isEmpty(contra)){
                            Toast.makeText(getApplicationContext(), "El nombre de usuario no se encuentra registrado" ,Toast.LENGTH_SHORT).show();


                        }else{
                            t=1;
                        }
                }
                if(TextUtils.isEmpty(pas)) {
                     l=0;
                    Toast.makeText(getApplicationContext(), "No ha ingresado la contraseña" ,Toast.LENGTH_SHORT).show();
                } else{
                    if(pas.equals(contra)){
                         l=1;
                    }else{
                        Toast.makeText(getApplicationContext(), "No ha ingresado la contraseña correcta" ,Toast.LENGTH_SHORT).show();

                    }
                }

                if((l==1 && t==1)){
                    Intent intent= new Intent(loggin.this,lista.class);
                    editor.putInt("num",id);

                    editor.putInt("var",1);
                    editor.commit();

                    startActivity(intent);
                    finish();
                }


            }
        });

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inten = new Intent(loggin.this,register.class);
                startActivity(inten);
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




}
