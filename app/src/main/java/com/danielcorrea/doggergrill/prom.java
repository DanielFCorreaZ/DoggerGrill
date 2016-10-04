package com.danielcorrea.doggergrill;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class prom extends AppCompatActivity {
    TextView nombre,descrip;
    ImageView image;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prom);
        prefs=getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        editor=prefs.edit();


        nombre = (TextView) findViewById(R.id.pNom);
        descrip = (TextView) findViewById(R.id.pDes);
        image = (ImageView) findViewById(R.id.pIma);




        switch (prefs.getInt("val",-1)){

            case(0):nombre.setText("Hamburguesa");
                descrip.setText("Desde el viernes hasta el domingo disfruta de la clasica Cheese Burguer en combo por solo 16.000 pesos");
                image.setImageResource(R.drawable.ham);
                break;
            case(1):nombre.setText("Perros");
                descrip.setText("El sabado todo el dia aprovecha el 2x1 en perros, con todo lo que le puedas hechar por solo 12.000 pesos");
                image.setImageResource(R.drawable.dogger3);
                break;
            case(2):nombre.setText("Costillas");
                descrip.setText("Ven y prueba nuestros domingos de Costillas y elige entre nuestras diferentes preparaciones por solo 18.000 pesos");
                image.setImageResource(R.drawable.cost);
                break;
            case(3):nombre.setText("Sandwich");
                descrip.setText("Todo el fin de semana descuento en nuestros aplastados con todo lo que le puedas hechar por solo 15.000 pesos");
                image.setImageResource(R.drawable.prom4);
                break;
            case(4):nombre.setText("Cerveceria");
                descrip.setText("Disfruta de las cervezas Tres Cordilleras a 2x1 todos los viernes por solo 5000 pesos");
                image.setImageResource(R.drawable.beer);
                break;

        }
    }
}
