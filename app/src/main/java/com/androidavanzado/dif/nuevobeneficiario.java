package com.androidavanzado.dif;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TimePicker;
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

public class nuevobeneficiario extends AppCompatActivity implements View.OnClickListener {

    String URL_SERVIDOR = "https://chilaquilesenterprise.com/conexion_jenni/beneficiario.php";
    String CERO = "0";
    String DOS_PUNTOS = ":";
    String BARRA = "-";
    Spinner spnrGenero, spnrEstados, spnrEscolaridad, spnrEstado_civil;
    String[] Genero = {"Seleccionar Genero","Masculino", "Femenino"};
    String[] Estados = {"Seleccionar Estado","Aguascalientes","Baja California","Baja California Sur","Campeche","Coahuila",
            "Colima","Chiapas","Chihuahua","Durango","Ciudad de México","Guanajuato",
            "Guerrero","Hidalgo","Jalisco","Estado de México","Michoacán","Morelos",
            "Nayarit","Nuevo León","Oaxaca","Puebla","Querétaro","Quintana Roo",
            "San Luis Potosí","Sinaloa","Sonora","Tabasco","Tamaulipas","Tlaxcala","Veracruz","Yucatán","Zacatecas"};
    String[] Escolaridad = {"Seleccionar Escolaridad","Preescolar","Primaria","Secundaria","Bachillerato","Licenciatura"};
    String[] Estado_civil = {"Seleccionar Estado Civil","Soltero","Casado","Divorciado","Viudo","Relacion Abierta"};
    ProgressBar loading;
    Button Registro;
    ImageView ibObtenerFecha, ibObtenerFecha2, ibObtenerHora;
    Intent i;
    EditText fecha_nac, fecha_regis,nombre,AP,AM,curp,telefono,municipio,domicilio,cp,lugar_nacimiento,grado_escolar,nombre_escuela,ocupacion;
    //Calendario para obtener fecha
    Calendar c = Calendar.getInstance();
    //Variables para obtener la fecha
    int mes = c.get(Calendar.MONTH);
    int dia = c.get(Calendar.DAY_OF_MONTH);
    int anio = c.get(Calendar.YEAR);
    //Variables para obtener la hora hora
    int hora = c.get(Calendar.HOUR_OF_DAY);
    int minuto = c.get(Calendar.MINUTE);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevobeneficiario);

        Registro = (Button) findViewById(R.id.btn_registroB);
        nombre = (EditText) findViewById(R.id.edit_nombres);
        AP = (EditText) findViewById(R.id.edit_apellido_paterno);
        AM = (EditText) findViewById(R.id.edit_apellido_materno);
        curp = (EditText) findViewById(R.id.edit_curp);
        telefono = (EditText) findViewById(R.id.edit_telefono);
        spnrEstados = (Spinner) findViewById(R.id.edit_estado);
        municipio = (EditText) findViewById(R.id.edit_municipio);
        domicilio = (EditText) findViewById(R.id.edit_domicilio);
        cp = (EditText) findViewById(R.id.edit_cp);
        spnrGenero = (Spinner) findViewById(R.id.edit_sexo);
        fecha_nac = (EditText) findViewById(R.id.edit_fecha_nacimiento);
        lugar_nacimiento = (EditText) findViewById(R.id.edit_lugar_nacimiento);
        fecha_regis = (EditText) findViewById(R.id.edit_fecha_registro);
        spnrEstado_civil = (Spinner) findViewById(R.id.edit_estado_civil);
        spnrEscolaridad = (Spinner) findViewById(R.id.edit_escolaridad);
        grado_escolar = (EditText) findViewById(R.id.edit_gradoescolar);
        nombre_escuela = (EditText) findViewById(R.id.edit_escuela);
        ocupacion = (EditText) findViewById(R.id.edit_ocupacion);
        ibObtenerFecha = (ImageView) findViewById(R.id.btn_calendario);
        ibObtenerFecha2 = (ImageView) findViewById(R.id.btn_calendario2);
        ibObtenerHora = (ImageView) findViewById(R.id.btn_hora);

        ibObtenerFecha.setOnClickListener(this);
        ibObtenerFecha2.setOnClickListener(this);
        ibObtenerHora.setOnClickListener(this);

        spnrGenero.setAdapter(new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_dropdown_item, Genero));
        spnrEstados.setAdapter(new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_dropdown_item, Estados));
        spnrEscolaridad.setAdapter(new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_dropdown_item, Escolaridad));
        spnrEstado_civil.setAdapter(new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_dropdown_item, Estado_civil));

        Registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrar();
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_calendario:
                obtenerFecha();
                break;
            case R.id.btn_calendario2:
                obtenerFecha2();
                break;
            case R.id.btn_hora:
                obtenerHora();
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
                fecha_nac.setText(year + BARRA + mesFormateado + BARRA + diaFormateado+" ");
            }
            //Estos valores deben ir en ese orden, de lo contrario no mostrara la fecha actual
        },anio, mes, dia);
        //Muestro el widget
        recogerFecha.show();

    }

    private void obtenerFecha2(){
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
                fecha_regis.setText(year + BARRA + mesFormateado + BARRA + diaFormateado);
            }
            //Estos valores deben ir en ese orden, de lo contrario no mostrara la fecha actual
        },anio, mes, dia);
        //Muestro el widget
        recogerFecha.show();

    }

    public void registrar() {
        StringRequest stringRequest;
        stringRequest = new StringRequest(Request.Method.POST, URL_SERVIDOR,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        // En este apartado se programa lo que deseamos hacer en caso de no haber errores

                        if(response.equals("ERROR 1")) {
                            Toast.makeText(nuevobeneficiario.this, "Se deben de llenar todos los campos.", Toast.LENGTH_LONG).show();
                            loading.setVisibility(View.GONE);
                            Registro.setVisibility(View.VISIBLE);
                        } else if(response.equals("ERROR 2")) {
                            Toast.makeText(nuevobeneficiario.this, "Fallo el registro.", Toast.LENGTH_LONG).show();
                            loading.setVisibility(View.GONE);
                            Registro.setVisibility(View.VISIBLE);
                        } else if(response.equals("MENSAJE")) {
                            Toast.makeText(nuevobeneficiario.this, "Registro exitoso.", Toast.LENGTH_LONG).show();
                            finish();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // En caso de tener algun error en la obtencion de los datos
                Toast.makeText(nuevobeneficiario.this, "ERROR CON LA CONEXION", Toast.LENGTH_LONG).show();
                loading.setVisibility(View.GONE);
                Registro.setVisibility(View.VISIBLE);
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                // En este metodo se hace el envio de valores de la aplicacion al servidor
                Map<String, String> parametros = new Hashtable<String, String>();
                parametros.put("nombres", nombre.getText().toString());
                parametros.put("AP", AP.getText().toString());
                parametros.put("AM", AM.getText().toString());
                parametros.put("curp", curp.getText().toString());
                parametros.put("telefono", telefono.getText().toString());
                parametros.put("estado", String.valueOf(spnrEstados.getSelectedItemPosition()));
                parametros.put("municipio", municipio.getText().toString());
                parametros.put("domicilio", domicilio.getText().toString());
                parametros.put("cp", cp.getText().toString());
                parametros.put("sexo", String.valueOf(spnrGenero.getSelectedItem()));
                parametros.put("fecha_nacimiento", fecha_nac.getText().toString());
                parametros.put("lugar_nacimiento", lugar_nacimiento.getText().toString());
                parametros.put("fecha_registro", fecha_regis.getText().toString());
                parametros.put("estado_civil", String.valueOf(spnrEstado_civil.getSelectedItem()));
                parametros.put("escolaridad", String.valueOf(spnrEscolaridad.getSelectedItem()));
                parametros.put("gradoescolar", grado_escolar.getText().toString());
                parametros.put("nombre_escuela", nombre_escuela.getText().toString());
                parametros.put("ocupacion", ocupacion.getText().toString());
                return parametros;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(nuevobeneficiario.this);
        requestQueue.add(stringRequest);
    }

    private void obtenerHora(){
        TimePickerDialog recogerHora = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                //Formateo el hora obtenido: antepone el 0 si son menores de 10
                String horaFormateada =  (hourOfDay < 10)? String.valueOf(CERO + hourOfDay) : String.valueOf(hourOfDay);
                //Formateo el minuto obtenido: antepone el 0 si son menores de 10
                String minutoFormateado = (minute < 10)? String.valueOf(CERO + minute):String.valueOf(minute);
                //Obtengo el valor a.m. o p.m., dependiendo de la selección del usuario
                String AM_PM;
                if(hourOfDay < 12) {
                    AM_PM = "a.m.";
                } else {
                    AM_PM = "p.m.";
                }
                //Muestro la hora con el formato deseado
                fecha_regis.setText(horaFormateada + DOS_PUNTOS + minutoFormateado + " " + AM_PM);
            }
            //Estos valores deben ir en ese orden
            //Al colocar en false se muestra en formato 12 horas y true en formato 24 horas
            //Pero el sistema devuelve la hora en formato 24 horas
        }, hora, minuto, true);

        recogerHora.show();
    }
}