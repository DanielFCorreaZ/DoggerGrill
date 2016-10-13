package com.danielcorrea.doggergrill;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class prom extends AppCompatActivity {
    TextView nombre,descrip;
    ImageView image;
    Button favo;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    int e=0,cliente,prod,t,s;
    ContentValues dataBD;
    SQLiteDatabase dbMisFavoritos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prom);
        prefs=getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        editor=prefs.edit();
        ContactosSQLiteHelper misfavoritos = new ContactosSQLiteHelper(this,"MisFavoritosBD", null, 1);

        dbMisFavoritos = misfavoritos.getWritableDatabase();
        dataBD = new ContentValues();

        favo = (Button) findViewById(R.id.fav);
        nombre = (TextView) findViewById(R.id.pNom);
        descrip = (TextView) findViewById(R.id.pDes);
        image = (ImageView) findViewById(R.id.pIma);

        cliente=prefs.getInt("num",-1);
        prod=prefs.getInt("pro",-1);

        Cursor c = dbMisFavoritos.rawQuery("select * from MisFavoritos where user='"+cliente+"'",null);
        if(c.moveToFirst()) {
            do {
                if (c.getInt(2)==prod){
                    favo.setBackgroundResource(R.drawable.estrella1);
                    e=1;
                    break;
                }else{favo.setBackgroundResource(R.drawable.estrella);
                    e=0;}
            } while(c.moveToNext());
        }

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


        favo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(e==0){
                    favo.setBackgroundResource(R.drawable.estrella1);
                    Toast.makeText(getApplicationContext(), "Producto agregado a favoritos" ,Toast.LENGTH_SHORT).show();
                    dataBD.put("user",cliente);
                    dataBD.put("prod",prod);
                    dbMisFavoritos.insert("MisFavoritos",null,dataBD);

                    e=1;
                }else{
                    favo.setBackgroundResource(R.drawable.estrella);
                    Toast.makeText(getApplicationContext(), "Producto eliminado de favoritos" ,Toast.LENGTH_SHORT).show();
                    Cursor c = dbMisFavoritos.rawQuery("select * from MisFavoritos where user='"+cliente+"'",null);
                    if(c.moveToFirst()) {
                        do {
                            if (c.getInt(2)==prod){
                                t = c.getInt(0);}
                        } while(c.moveToNext());
                    }
                    dbMisFavoritos.delete("MisFavoritos", "id='"+t+"'",null);
                    e=0;
                }
            }
        });

    }
}
