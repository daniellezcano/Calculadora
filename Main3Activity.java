package com.example.calculator;

import android.content.Intent;
import android.service.autofill.OnClickAction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity {

    TextView PantallaD;
    String b;
    double resultado = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        PantallaD = (TextView) findViewById(R.id.pant);
    }

    public void botones(View view){
        PantallaD = (TextView) findViewById(R.id.pant); //a PantallaD le asigno el valor de pant
        String Display = PantallaD.getText().toString(); //lo pongo en una variable string
        if(!Display.isEmpty()){//si es distinto de vacio (esto lo hice por si tiene un resultado)
            if (Button.class.isAssignableFrom(view.getClass())) { //pregunto si el boton se puede asignar
                Button num = (Button) view; //creo una clase Button y le asigno la vista
                PantallaD.setText(TextUtils.concat(PantallaD.getText(), num.getText()));//concateno a la pantalla el num
            }
        }
        else{
            if (Button.class.isAssignableFrom(view.getClass())) {
                Button num = (Button) view;
                if(Character.isDigit(num.getText().charAt(0)))
                PantallaD.setText(TextUtils.concat(PantallaD.getText(), num.getText()));
            }
        }

    }


    public void botonIgual(View view){
        b = (String) PantallaD.getText(); //A la variable B le asigno la pantalla
        if(!b.isEmpty()){ //si b no esta vacio (osea contiene algo)
            Calculadora c = new Calculadora(); //instancio la clase calculadora
            try {
                resultado = c.compute(b); //alojo en la variable resultado lo que me calcula compute con parametro de pantalla
                b = String.valueOf(resultado); //Paso la varibal de double a string
            }
            catch (RuntimeException e){
                b = "Error";
            }
            PantallaD.setText(b);
            b = String.valueOf(0.0);
        }
        else
            Toast.makeText(this, "No hay cuentas para realizar", Toast.LENGTH_SHORT).show();
    }

    public void botonBorrar(View view){
        resultado = 0.0;
        PantallaD.setText("");
    }

}
