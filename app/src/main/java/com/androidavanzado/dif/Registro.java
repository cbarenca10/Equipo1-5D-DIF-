package com.androidavanzado.dif;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Hashtable;
import java.util.Map;

public class Registro extends AppCompatActivity {

    Intent i;
//String URL_SERVIDOR = "http://192.168.3.9/conection/register.php";
    String URL_SERVIDOR = "https://chilaquilesenterprise.com/conexion_jenni/register.php";

    ProgressBar loading;

    EditText nombres, AP, AM, correo, password, direccion, municipio, cp, tel, cedulaprof;
    Spinner spnrRoles, spnrEstados, spnrProfesion;
    String[] Roles = {"Seleccione Rol","Administrador","Medica","Psicologia","Trabajo Social","Juridico"};

    String[] Estados = {"Estado","Aguascalientes","Baja California","Baja California Sur","Campeche","Coahuila",
            "Colima","Chiapas","Chihuahua","Durango","Ciudad de México","Guanajuato",
            "Guerrero","Hidalgo","Jalisco","Estado de México","Michoacán","Morelos",
            "Nayarit","Nuevo León","Oaxaca","Puebla","Querétaro","Quintana Roo",
            "San Luis Potosí","Sinaloa","Sonora","Tabasco","Tamaulipas","Tlaxcala","Veracruz","Yucatán","Zacatecas"};

    String[] Profesiones = {"Seleccione Profesion","Doctor","Enfermera","Psicólogo","Trabajo Social","Otro"};

    Button Registro;
    TextView LoginReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        Registro = findViewById(R.id.btn_registro);
        LoginReturn = findViewById(R.id.login);

        nombres = findViewById(R.id.edit_nombre);
        AP = findViewById(R.id.edit_apellido_p);
        AM = findViewById(R.id.edit_apellido_m);
        correo = findViewById(R.id.edit_email);
        password = findViewById(R.id.edit_pass);
        direccion= findViewById(R.id.edit_direccion);
        municipio= findViewById(R.id.edit_municipio);
        cp= findViewById(R.id.edit_cp);
        tel= findViewById(R.id.edit_tel);
        cedulaprof= findViewById(R.id.edit_cedulaprof);

        spnrRoles =  findViewById(R.id.rolCatcher);
        spnrEstados =  findViewById(R.id.edit_estado);
        spnrProfesion =  findViewById(R.id.edit_profesion);

        spnrRoles.setAdapter(new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_dropdown_item, Roles));
        spnrEstados.setAdapter(new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_dropdown_item, Estados));
        spnrProfesion.setAdapter(new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_dropdown_item, Profesiones));


        LoginReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(Registro.this, Login.class);
                startActivity(i);
            }

        });
        Registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrar();
            }
        });

    }

    public void registrar() {
        StringRequest stringRequest;
        stringRequest = new StringRequest(Request.Method.POST, URL_SERVIDOR,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        // En este apartado se programa lo que deseamos hacer en caso de no haber errores

                        if(response.equals("ERROR 1")) {
                            Toast.makeText(Registro.this, "Se deben de llenar todos los campos.", Toast.LENGTH_SHORT).show();
                            loading.setVisibility(View.GONE);
                            Registro.setVisibility(View.VISIBLE);
                        } else if(response.equals("ERROR 2")) {
                            Toast.makeText(Registro.this, "Fallo el registro.", Toast.LENGTH_SHORT).show();
                            loading.setVisibility(View.GONE);
                            Registro.setVisibility(View.VISIBLE);
                        } else if(response.equals("MENSAJE")) {
                            Toast.makeText(Registro.this, "Registro exitoso.", Toast.LENGTH_LONG).show();
                            finish();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // En caso de tener algun error en la obtencion de los datos
                Toast.makeText(Registro.this, "ERROR CON LA CONEXION", Toast.LENGTH_LONG).show();
                loading.setVisibility(View.GONE);
                Registro.setVisibility(View.VISIBLE);
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                // En este metodo se hace el envio de valores de la aplicacion al servidor
                Map<String, String> parametros = new Hashtable<String, String>();
                parametros.put("nombres", nombres.getText().toString());
                parametros.put("AP", AP.getText().toString());
                parametros.put("AM", AM.getText().toString());
                parametros.put("correo", correo.getText().toString());
                parametros.put("password", password.getText().toString());
                parametros.put("rol", String.valueOf(spnrRoles.getSelectedItemPosition()));
                parametros.put("direccion", direccion.getText().toString());
                parametros.put("municipio", municipio.getText().toString());
                parametros.put("estado", String.valueOf(spnrEstados.getSelectedItemPosition()));
                parametros.put("cp", cp.getText().toString());
                parametros.put("tel", tel.getText().toString());
                parametros.put("profesion", String.valueOf(spnrProfesion.getSelectedItemPosition()));
                parametros.put("cedulaprof", cedulaprof.getText().toString());

                return parametros;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(Registro.this);
        requestQueue.add(stringRequest);
    }
}

