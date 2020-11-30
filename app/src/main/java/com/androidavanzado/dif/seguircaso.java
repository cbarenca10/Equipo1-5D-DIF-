package com.androidavanzado.dif;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class seguircaso extends AppCompatActivity {

    ImageView casos,nuevocaso,seguircaso,salir;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seguircaso);

        casos = (ImageView) findViewById(R.id.casos);
        nuevocaso = (ImageView) findViewById(R.id.nuevocaso);
        seguircaso = (ImageView) findViewById(R.id.seguimiento);
        salir = (ImageView) findViewById(R.id.salir);

        casos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(seguircaso.this, Menu.class);
                startActivity(i);
            }
        });

        nuevocaso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(seguircaso.this, nuevocaso.class);
                startActivity(i);
            }
        });

        seguircaso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(seguircaso.this, seguircaso.class);
                startActivity(i);
            }
        });

        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(seguircaso.this, Login.class);
                startActivity(i);
            }
        });
    }
}