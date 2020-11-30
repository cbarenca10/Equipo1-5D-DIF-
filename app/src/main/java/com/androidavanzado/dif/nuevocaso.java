package com.androidavanzado.dif;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Calendar;
import java.util.Hashtable;
import java.util.Map;

public class nuevocaso extends AppCompatActivity implements View.OnClickListener{

    private static final String CERO = "0";
    private static final String BARRA = "-";
    String URL_SERVIDOR = "https://chilaquilesenterprise.com/conexion_jenni/nuevocaso.php";

    ProgressBar loading;

    ImageView casos,nuevocaso,seguircaso,salir;
    EditText descripcion_general;
    Button NuevoBenef, llenar_formulario;
    Intent i;
    Spinner estado;
    String[] estatus_del_caso = {"Abierto","En proceso","Finalizado"};
    int spinner1=0;

    //Calendario para obtener fecha & hora
    public final Calendar c = Calendar.getInstance();

    //Variables para obtener la fecha
    final int mes = c.get(Calendar.MONTH);
    final int dia = c.get(Calendar.DAY_OF_MONTH);
    final int anio = c.get(Calendar.YEAR);


    //Widgets
    EditText fecha_apertura;
    Button ibObtenerFecha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevocaso);

        //Widget EditText donde se mostrara la fecha obtenida
        fecha_apertura = (EditText) findViewById(R.id.fecha_apertura);
        //Widget ImageButton del cual usaremos el evento clic para obtener la fecha
        ibObtenerFecha = (Button) findViewById(R.id.ib_obtener_fecha);
        //Evento setOnClickListener - clic
        ibObtenerFecha.setOnClickListener(this);

        casos = (ImageView) findViewById(R.id.casos);
        nuevocaso = (ImageView) findViewById(R.id.nuevocaso);
        seguircaso = (ImageView) findViewById(R.id.seguimiento);
        salir = (ImageView) findViewById(R.id.salir);
        NuevoBenef = (Button) findViewById(R.id.btn_nuevoBenef);
        llenar_formulario =  (Button) findViewById(R.id.llenar_formato);
        estado = (Spinner) findViewById(R.id.estado);
        descripcion_general = (EditText) findViewById(R.id.descripcion_general);
        estado.setAdapter(new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_dropdown_item, estatus_del_caso));
        llenar_formulario.setOnClickListener(this);


        NuevoBenef.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(nuevocaso.this, nuevobeneficiario.class);
                startActivity(i);
            }
        });

        casos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(nuevocaso.this, Menu.class);
                startActivity(i);
            }
        });

        nuevocaso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(nuevocaso.this, nuevocaso.class);
                startActivity(i);
            }
        });

        seguircaso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(nuevocaso.this, seguircaso.class);
                startActivity(i);
            }
        });

        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(nuevocaso.this, Login.class);
                startActivity(i);
            }
        });


        llenar_formulario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(nuevocaso.this, psicologia_formulario.class);
                startActivity(i);
                registrar_caso();
            }
        });


    }


    public void registrar_caso() {
        StringRequest stringRequest;
        stringRequest = new StringRequest(Request.Method.POST, URL_SERVIDOR,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        // En este apartado se programa lo que deseamos hacer en caso de no haber errores

                        if(response.equals("ERROR 1")) {
                            Toast.makeText(nuevocaso.this, "Se deben de llenar todos los campos.", Toast.LENGTH_SHORT).show();
                            loading.setVisibility(View.GONE);
                            nuevocaso.setVisibility(View.VISIBLE);
                        } else if(response.equals("ERROR 2")) {
                            Toast.makeText(nuevocaso.this, "Fallo el registro.", Toast.LENGTH_SHORT).show();
                            loading.setVisibility(View.GONE);
                            nuevocaso.setVisibility(View.VISIBLE);
                        } else if(response.equals("MENSAJE")) {
                            Toast.makeText(nuevocaso.this, "Registro exitoso.", Toast.LENGTH_LONG).show();
                            finish();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // En caso de tener algun error en la obtencion de los datos
                Toast.makeText(nuevocaso.this, "ERROR CON LA CONEXION", Toast.LENGTH_LONG).show();
                loading.setVisibility(View.GONE);
                nuevocaso.setVisibility(View.VISIBLE);
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                // En este metodo se hace el envio de valores de la aplicacion al servidor
                Map<String, String> parametros = new Hashtable<String, String>();
                parametros.put("fecha_apertura", fecha_apertura.getText().toString());
                parametros.put("descripcion_general", descripcion_general.getText().toString());
                parametros.put("estado", String.valueOf(estado.getSelectedItem()));
                return parametros;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(nuevocaso.this);
        requestQueue.add(stringRequest);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ib_obtener_fecha:
                obtenerFecha();
                break;
        }
    }

    private void obtenerFecha(){
        DatePickerDialog recogerFecha = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                //Esta variable lo que realiza es aumentar en uno el mes ya que comienza desde 0 = enero
                final int mesActual = month + 1;
                //Formateo el día obtenido: antepone el 0 si son menores de 10
                String diaFormateado = (dayOfMonth < 10)? CERO + String.valueOf(dayOfMonth):String.valueOf(dayOfMonth);
                //Formateo el mes obtenido: antepone el 0 si son menores de 10
                String mesFormateado = (mesActual < 10)? CERO + String.valueOf(mesActual):String.valueOf(mesActual);
                //Muestro la fecha con el formato deseado
                fecha_apertura.setText(year+ BARRA+ mesFormateado + BARRA +diaFormateado);
            }
            //Estos valores deben ir en ese orden, de lo contrario no mostrara la fecha actual
            /**
             *También puede cargar los valores que usted desee
             */
        },anio, mes, dia);
        //Muestro el widget
        recogerFecha.show();

    }
}