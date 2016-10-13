package com.danielcorrea.doggergrill;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by DELL on 08/10/2016.
 */
public class ContactosSQLiteHelper extends SQLiteOpenHelper {

    private String DATA_BASE_NAME= "AgendaBD";
    private int DATA_VERSION = 1;

    String sqlCreate = "CREATE TABLE Contactos (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "nombre     TEXT," +
            "contra   TEXT," +
            "correo     TEXT)";

    String sqlCreate1 = "CREATE TABLE Productos (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "idima     int," +
            "nombre     TEXT," +
            "descrip   TEXT," +
            "precio     TEXT)";
    String sqlCreate2 = "CREATE TABLE MisFavoritos (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "user     int," +
            "prod     int)";

    public ContactosSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreate);
        db.execSQL(sqlCreate1);
        db.execSQL(sqlCreate2);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Contactos");
        db.execSQL(sqlCreate);
        db.execSQL("DROP TABLE IF EXISTS Productos");
        db.execSQL(sqlCreate1);
        db.execSQL("DROP TABLE IF EXISTS MisFavoritos");
        db.execSQL(sqlCreate2);

    }
}
