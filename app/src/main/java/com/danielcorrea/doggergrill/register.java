package com.danielcorrea.doggergrill;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class register extends AppCompatActivity {

    Button acept, cancel;
    EditText user, pass, repass, mail;
    int t=0,l=0,m=0;
    boolean emailcheck=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        acept = (Button) findViewById(R.id.acept);
        cancel = (Button) findViewById(R.id.cancel);
        user = (EditText) findViewById(R.id.user);
        pass = (EditText) findViewById(R.id.pass);
        repass = (EditText) findViewById(R.id.repass);
        mail = (EditText) findViewById(R.id.mail);

       acept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(TextUtils.isEmpty(user.getText().toString())) {
                    t=0;
                    Toast.makeText(getApplicationContext(), "No ha ingresado el nombre de usuario" ,Toast.LENGTH_SHORT).show();
                } else{t=1;
                }
                if(TextUtils.isEmpty(pass.getText().toString())) {
                    l=0;
                    Toast.makeText(getApplicationContext(), "No ha ingresado la contraseña" ,Toast.LENGTH_SHORT).show();
                } else{
                    if(TextUtils.isEmpty(repass.getText().toString())){
                        l=0;
                        Toast.makeText(getApplicationContext(), "No ha ingresado la contraseña" ,Toast.LENGTH_SHORT).show();
                    }else{
                        if((pass.getText().toString()).equals(repass.getText().toString())){
                            l=1;
                        }else{
                            Toast.makeText(getApplicationContext(), "No ha ingresado la misma contraseña" ,Toast.LENGTH_SHORT).show();
                        }

                    }
                }
                if(TextUtils.isEmpty(mail.getText().toString())) {
                    m=0;
                    Toast.makeText(getApplicationContext(), "No ha ingresado el correo correcto" ,Toast.LENGTH_SHORT).show();
                } else{checkemail(mail.getText().toString());
                    if(emailcheck==true)
                    {
                        m=1;
                    }else{
                        m=0;
                        Toast.makeText(getApplicationContext(), "No ha ingresado una direccion de correo correcto" ,Toast.LENGTH_SHORT).show();
                    }
                }


                if(t==1 && l==1 && m==1) {
                    Intent intent = new Intent();
                    intent.putExtra("usuario", user.getText().toString());
                    intent.putExtra("mail", mail.getText().toString());
                    intent.putExtra("contrasena", pass.getText().toString());
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                setResult(RESULT_CANCELED,intent);
                finish();
            }
        });


    }

    public void checkemail(String email)
    {
        Pattern pattern = Pattern.compile(".+@.+.[a-z]+");
        Matcher matcher = pattern.matcher(email);
        emailcheck = matcher.matches();
    }

}
