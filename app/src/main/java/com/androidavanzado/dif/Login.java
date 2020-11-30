package com.androidavanzado.dif;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;

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
import java.util.Hashtable;
import java.util.Map;


public class Login extends AppCompatActivity {
    //String URL_SERVIDOR = "http://192.168.3.9/conection/login.php";
    String URL_SERVIDOR = "https://chilaquilesenterprise.com/conexion_jenni/login.php";

    Intent i;
    Button login;
    EditText edit_email, edit_pass;
    SharedPreferences preferences;
    TextView registro;
    SessionManager sessionManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        edit_email = findViewById(R.id.edit_email);
        edit_pass = findViewById(R.id.edit_pass);
        login = findViewById(R.id.btn_ingresar);
        registro = findViewById(R.id.registro);

        preferences = this.getSharedPreferences("MYPREFS", Context.MODE_PRIVATE);

        sessionManager = new SessionManager(this);

//        login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                login();
//
//            }
//        });



        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(Login.this, Registro.class);
                startActivity(i);
            }

        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mEmail = edit_email.getText().toString().trim();
                String mPass = edit_pass.getText().toString().trim();

                if (!mEmail.isEmpty() || !mPass.isEmpty()) {
                    Login(mEmail, mPass);
                } else {
                    edit_email.setError("Ingresar Email");
                    edit_email.setError("Ingresar Contraseña");
                }
            }
        });
    }


    private void Login(final String correo, final String password) {


        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_SERVIDOR,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("login");

                            if (success.equals("1")) {

                                for (int i = 0; i < jsonArray.length(); i++) {

                                    JSONObject object = jsonArray.getJSONObject(i);

                                    String id_usuario = object.getString("id_usuario").trim();
                                    String nombres = object.getString("nombres").trim();
                                    String AP = object.getString("AP").trim();
                                    String AM = object.getString("AM").trim();
                                    String correo = object.getString("correo").trim();
                                    String rol = object.getString("rol").trim();
                                    String profesion = object.getString("profesion").trim();

                                    sessionManager.createSession(id_usuario, nombres, AP, AM, correo, rol, profesion);

                                    Intent intent = new Intent(Login.this, Menu.class);
                                    intent.putExtra("id_usuario", id_usuario);
                                    intent.putExtra("nombres", nombres);
                                    intent.putExtra("AP", AP);
                                    intent.putExtra("AM", AM);
                                    intent.putExtra("correo", correo);
                                    intent.putExtra("rol", rol);
                                    intent.putExtra("profesion", profesion);
                                    startActivity(intent);
                                    finish();



                                }

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(Login.this, "Correo o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Login.this, "Contacte a soporte tecnico**" +error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("correo", correo);
                params.put("password", password);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}