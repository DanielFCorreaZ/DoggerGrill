package com.danielcorrea.doggergrill;

import android.content.Intent;
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
    EditText usser,paass;
    String user,contrasena, correeo;
    int l=0,t=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loggin);

        reg = (Button) findViewById(R.id.reg);
        ok = (Button) findViewById(R.id.ok);
        paass = (EditText)findViewById(R.id.paass);
        usser = (EditText) findViewById(R.id.usser);

       // reg.setOnClickListener((View.OnClickListener) this);
        //ok.setOnClickListener((View.OnClickListener) this);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String useer=usser.getText().toString();
                String pas=paass.getText().toString();

                if(TextUtils.isEmpty(useer)) {
                     t=0;
                   Toast.makeText(getApplicationContext(), "No ha ingresado el nombre de usuario" ,Toast.LENGTH_SHORT).show();
                } else{
                        if(useer.equals(user)){
                             t=1;
                        }else{
                            Toast.makeText(getApplicationContext(), "No ha ingresado el nombre de usuario correcto" ,Toast.LENGTH_SHORT).show();
                        }
                }
                if(TextUtils.isEmpty(pas)) {
                     l=0;
                    Toast.makeText(getApplicationContext(), "No ha ingresado la contraseña" ,Toast.LENGTH_SHORT).show();
                } else{
                    if(pas.equals(contrasena)){
                         l=1;
                    }else{
                        Toast.makeText(getApplicationContext(), "No ha ingresado la contraseña correcta" ,Toast.LENGTH_SHORT).show();

                    }
                }

                if(l==1 && t==1){
                    Intent intent= new Intent(loggin.this,MainActivity.class);
                    intent.putExtra("usu",user);
                    intent.putExtra("mai",correeo);

                    startActivity(intent);
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
            //Log.d("user",user);
            //Log.d("contraseña",contrasena);
            //Toast.makeText(this, "user: "+user+" contrasena: "+contrasena,Toast.LENGTH_SHORT).show();
        }
        if (requestCode==012 && resultCode == RESULT_CANCELED){
            Log.d("mensaje","no se cargaron datos");
        }


    }



}
