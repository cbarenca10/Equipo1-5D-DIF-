package com.androidavanzado.dif;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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

import java.util.Hashtable;
import java.util.Map;

public class juridico_formulario extends AppCompatActivity {


    EditText procedencia,observaciones_relevantes,contacto_alternativo,descripcion_detallada;
    Button guardar;
    Spinner proceso_actual_iniciado;
    String[] proceso = {"Orientación","Asesoría","Apoyo jurídico"};
    int spinner1=0;
    Intent i;

    String URL_SERVIDOR = "https://chilaquilesenterprise.com/conexion_jenni/formulario_juridico.php";
    ProgressBar loading;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juridico_formulario);

        procedencia = (EditText) findViewById(R.id.procedencia);
        observaciones_relevantes = (EditText) findViewById (R.id.observaciones_relevantes);
        contacto_alternativo = (EditText) findViewById(R.id.contacto_alternativo);
        descripcion_detallada = (EditText) findViewById(R.id.descripcion_detallada);
        guardar = (Button) findViewById(R.id.guardar);
        proceso_actual_iniciado = (Spinner) findViewById(R.id.proceso_actual_iniciado);
        proceso_actual_iniciado.setAdapter(new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_dropdown_item, proceso));

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(juridico_formulario.this, seguircaso.class);
                startActivity(i);
                llenar_formulario();
            }
        });
    }

    public void llenar_formulario() {
        StringRequest stringRequest;
        stringRequest = new StringRequest(Request.Method.POST, URL_SERVIDOR,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        // En este apartado se programa lo que deseamos hacer en caso de no haber errores

                        if(response.equals("ERROR 1")) {
                            Toast.makeText(juridico_formulario.this, "Se deben de llenar todos los campos.", Toast.LENGTH_SHORT).show();
                            loading.setVisibility(View.GONE);
                        } else if(response.equals("ERROR 2")) {
                            Toast.makeText(juridico_formulario.this, "Fallo el registro.", Toast.LENGTH_SHORT).show();
                            loading.setVisibility(View.GONE);
                        } else if(response.equals("MENSAJE")) {
                            Toast.makeText(juridico_formulario.this, "Registro exitoso.", Toast.LENGTH_LONG).show();
                            finish();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // En caso de tener algun error en la obtencion de los datos
                Toast.makeText(juridico_formulario.this, "ERROR CON LA CONEXION", Toast.LENGTH_LONG).show();
                loading.setVisibility(View.GONE);
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                // En este metodo se hace el envio de valores de la aplicacion al servidor
                Map<String, String> parametros = new Hashtable<String, String>();
                parametros.put("procedencia", procedencia.getText().toString());
                parametros.put("observaciones_relevantes", observaciones_relevantes.getText().toString());
                parametros.put("contacto_alternativo", contacto_alternativo.getText().toString());
                parametros.put("descripcion_detallada", descripcion_detallada.getText().toString());
                parametros.put("proceso_actual_iniciado", String.valueOf(proceso_actual_iniciado.getSelectedItem()));
                return parametros;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(juridico_formulario.this);
        requestQueue.add(stringRequest);
    }

}