package com.example.calculator;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText CajaUsuario;
    EditText CajaContraseña;
    private Object Main2Activity;
    private Object Main3Activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CajaUsuario = findViewById(R.id.tx_usuario);
        CajaContraseña = findViewById(R.id.tx_contraseña);
    }

    public void botonRegistrar(View view){
        Toast.makeText(this, "Te vas a registrar", Toast.LENGTH_LONG).show();
        Intent Intent1 = new Intent(this, Main2Activity.class);
        startActivity(Intent1);

    }

    public void botonIngresar(View view){
        AdminSQLite admin = new AdminSQLite(getBaseContext());
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();//modo lectura y scritura

        String usuario = CajaUsuario.getText().toString();
        String contraseña = CajaContraseña.getText().toString();

        if((!usuario.isEmpty()) || (!contraseña.isEmpty())){
            Cursor fila = BaseDeDatos.rawQuery
                    ("Select usuario, contraseña from USUARIOS where usuario ='"+ usuario +"' and contraseña = '"+ contraseña +"'", null);

            //fijarnos si tiene valores en la bd y mostrar
            if(fila.moveToFirst()){
            CajaUsuario.setText(fila.getString(0));
            CajaContraseña.setText(fila.getString(1));
                Toast.makeText(this, "Ingresaste a la calculadora", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(MainActivity.this, Main3Activity.class);
                startActivity(i);
                BaseDeDatos.close();
            } else {
                Toast.makeText(this, "No existe Usuario con esa contraseña", Toast.LENGTH_SHORT).show();
                BaseDeDatos.close();
            }


        } else{
            Toast.makeText(this, "Tenes que ingresar datos", Toast.LENGTH_SHORT).show();
        }

    }
}

