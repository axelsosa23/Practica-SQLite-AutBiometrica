package com.mulapp.passwordapp.BaseDeDatos;

public class Constants {

    //NOMBRE DE LA BD
    public static final String BD_NAME = "PASSWORD_BD";

    //VERSION DE LA BD
    public static final int BD_VERSION = 1;

    //NOMBRE DE LA TABLA
    public static final String TABLE_NAME = "PASSWORD_TABLE";

    public static final String C_ID = "ID";
    public static final String C_TITULO = "TITULO";
    public static final String C_CUENTA = "CUENTA";
    public static final String C_NOMBRE_USUARIO = "NOMBRE_USUARIO";
    public static final String C_PASSWORD = "PASSWORD";
    public static final String C_SITIO_WEB = "SITIO_WEB";
    public static final String C_NOTA = "NOTA";
    public static final String C_TIEMPO_REGISTRO = "TIMEPO_REGISTRO";
    public static final String C_TIEMPO_ACTUALIZACION = "TIMEPO_ACTUALIZACION";

    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
            + C_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + C_TITULO + " TEXT, "
            + C_CUENTA + " TEXT, "
            + C_NOMBRE_USUARIO + " TEXT, "
            + C_PASSWORD + " TEXT, "
            + C_SITIO_WEB + " TEXT, "
            + C_NOTA + " TEXT, "
            + C_TIEMPO_REGISTRO + " TEXT, "
            + C_TIEMPO_ACTUALIZACION + " TEXT)";
}
