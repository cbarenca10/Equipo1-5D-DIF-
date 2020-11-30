package com.androidavanzado.dif;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Menu extends AppCompatActivity {

    private static final String TAG = Menu.class.getSimpleName(); //getting the info
    ImageView casos,nuevocaso,seguircaso,salir;
    String Rol;
    Intent i;
    SessionManager sessionManager;
    String getId;
    private static String URL_READ = "https://chilaquilesenterprise.com/conexion_jenni/read_detail.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();

        casos = (ImageView) findViewById(R.id.casos_M);
        nuevocaso = (ImageView) findViewById(R.id.nuevocaso_M);
        seguircaso = (ImageView) findViewById(R.id.seguimiento_M);
        salir = (ImageView) findViewById(R.id.salir_M);

        HashMap<String, String> user = sessionManager.getUserDetail();
        getId = user.get(sessionManager.ID_USUARIO);

        casos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(Menu.this, Menu.class);
                startActivity(i);
            }
        });

        nuevocaso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(Rol.equals("2")){
                    i = new Intent(Menu.this, nuevocaso_medicina.class);
                    startActivity(i);
                }
                if(Rol.equals("3")){
                    i = new Intent(Menu.this, nuevocaso.class);
                    startActivity(i);
                }
                if(Rol.equals("4")){
                    i = new Intent(Menu.this, nuevocaso_trabajosocial.class);
                    startActivity(i);
                }
                if(Rol.equals("5")){
                    i = new Intent(Menu.this, nuevocaso_juridico.class);
                    startActivity(i);
                }

            }
        });

        seguircaso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(Menu.this, seguircaso.class);
                startActivity(i);
            }
        });

        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(Menu.this, Login.class);
                startActivity(i);
            }
        });
    }

    private void getUserDetail(){

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Cargando...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_READ,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        Log.i(TAG, response.toString());

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("read");

                            if (success.equals("1")){

                                for (int i =0; i < jsonArray.length(); i++){

                                    JSONObject object = jsonArray.getJSONObject(i);

                                    String strName = object.getString("nombres").trim();
                                    String strAP = object.getString("AP").trim();
                                    String strAM = object.getString("AM").trim();
                                    String strRol = object.getString("rol").trim();
                                    String strProfesion = object.getString("profesion").trim();

                                    Rol = strRol;

                                }

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            progressDialog.dismiss();
                            Toast.makeText(Menu.this, "Error leyendo detalles "+e.toString(), Toast.LENGTH_LONG).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(Menu.this, "Error leyendo detalles "+error.toString(), Toast.LENGTH_LONG).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String > params = new HashMap<>();
                params.put("id_usuario", getId);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    @Override
    protected void onResume() {
        super.onResume();
        getUserDetail();
    }

}