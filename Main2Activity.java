package com.example.calculator;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    public EditText CajaUsuario, CajaEmail, CajaContraseña, CajaCContraseña;
    public ImageView Foto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        CajaUsuario = (EditText) findViewById(R.id.tx_usuario);
        CajaEmail = (EditText) findViewById(R.id.tx_email);
        CajaContraseña = (EditText) findViewById(R.id.tx_contraseña);
        CajaCContraseña = (EditText) findViewById(R.id.tx_ccontraseña);
        Foto = (ImageView) findViewById(R.id.imagen);

    }


    public void botonGuardar(View view) {
        AdminSQLite Admin = new AdminSQLite(getBaseContext());
//Abrir bd de lectura y escritura
        SQLiteDatabase BaseDeDatos = Admin.getWritableDatabase();

        String usuario = CajaUsuario.getText().toString();
        String email = CajaEmail.getText().toString();
        String contraseña = CajaContraseña.getText().toString();
        String ccontraseña = CajaCContraseña.getText().toString();

        if (!usuario.isEmpty() && !email.isEmpty() && !contraseña.isEmpty() && !ccontraseña.isEmpty()) {

            ContentValues Registro = new ContentValues();


            Registro.put("usuario", usuario);
            Registro.put("email", email);
            Registro.put("contraseña", contraseña);

            BaseDeDatos.insert("USUARIOS", null, Registro);

            BaseDeDatos.close();
            CajaUsuario.setText("");
            CajaEmail.setText("");
            CajaContraseña.setText("");
            CajaCContraseña.setText("");


            Toast.makeText(this, "REGISTRO EXITOSO", Toast.LENGTH_SHORT).show();

            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        } else {
            Toast.makeText(this, "DEBES INGRESAR TODOS LOS CAMPOS", Toast.LENGTH_SHORT).show();

        }

    }

    public void botonFoto(View view) {
        Toast.makeText(this, "Sonrie para la foto", Toast.LENGTH_SHORT).show();
        Intent tomarFoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE); //intento de tomar foto
        startActivityForResult(tomarFoto, 0); //permite saber si se hizo correctamente y arrojo resultado en el diseño

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bitmap = (Bitmap) data.getExtras().get("data");
        Drawable d = new BitmapDrawable(getResources(), bitmap);
        Foto.setBackground(d);
    }

}

