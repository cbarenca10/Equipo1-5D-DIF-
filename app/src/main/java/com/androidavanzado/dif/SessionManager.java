package com.androidavanzado.dif;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.HashMap;

public class SessionManager {

    SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;
    public Context context;
    int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "LOGIN";
    private static final String LOGIN = "IS_LOGIN";
    public static final String ID_USUARIO = "ID_USUARIO";
    public static final String NOMBRES = "NOMBRES";
    public static final String AP = "AP";
    public static final String AM = "AM";
    public static final String CORREO = "CORREO";
    public static final String ROL = "ROL";
    public static final String PROFESION = "PROFESION";

    public SessionManager(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = sharedPreferences.edit();
    }
    //id_usuario, nombres, AP, AM, correo, rol, profesion
    public void createSession(String id_usuario, String nombres,String aAP, String aAM, String correo, String rol, String profesion){

        editor.putBoolean(LOGIN, true);
        editor.putString(ID_USUARIO, id_usuario);
        editor.putString(NOMBRES, nombres);
        editor.putString(AP, aAP);
        editor.putString(AM, aAM);
        editor.putString(CORREO, correo);
        editor.putString(ROL, rol);
        editor.putString(PROFESION, profesion);
        editor.apply();

    }

    public boolean isLoggin(){
        return sharedPreferences.getBoolean(LOGIN, false);
    }

    public void checkLogin(){

        if (!this.isLoggin()){
            Intent i = new Intent(context, Login.class);
            context.startActivity(i);
            ((Menu) context).finish();
        }
    }

    public HashMap<String, String> getUserDetail(){

        HashMap<String, String> user = new HashMap<>();
        user.put(ID_USUARIO, sharedPreferences.getString(ID_USUARIO, null));
        user.put(NOMBRES, sharedPreferences.getString(NOMBRES, null));
        user.put(AM, sharedPreferences.getString(AM, null));
        user.put(AP, sharedPreferences.getString(AP, null));
        user.put(CORREO, sharedPreferences.getString(CORREO, null));
        user.put(ROL, sharedPreferences.getString(ROL, null));
        user.put(PROFESION, sharedPreferences.getString(PROFESION, null));

        return user;
    }

    public void logout(){

        editor.clear();
        editor.commit();
        Intent i = new Intent(context, Login.class);
        context.startActivity(i);
        ((Menu) context).finish();

    }

}