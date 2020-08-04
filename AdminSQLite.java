package com.example.calculator;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;


public class AdminSQLite extends SQLiteOpenHelper {


private static String USUARIOS = "create table USUARIOS(usuario text primary key, email text, contraseña text)";


    public AdminSQLite(@Nullable Context context) {
        super(context,"dbtest.db",null,3);
    }

    @Override
    public void onCreate(SQLiteDatabase BaseDeDatos) {
        BaseDeDatos.execSQL("Drop table if exists USUARIOS");
        BaseDeDatos.execSQL(USUARIOS);
        Log.e("oncreate","oncreate");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.e("onupgrade","onupgrade");
        db.execSQL("Drop table if exists USUARIOS");
        db.execSQL(USUARIOS);

    }
}
