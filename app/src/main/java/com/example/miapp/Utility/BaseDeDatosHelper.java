package com.example.miapp.Utility;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

import com.example.miapp.models.PublicacionCabecera;

import java.util.ArrayList;
import java.util.List;

public class BaseDeDatosHelper extends SQLiteOpenHelper {

    private static final String NOMBRE_BD = "NRBYEES.db";
    private static final int VERSION_BD = 1;

    public static final String TABLA_PUBLICACIONES_CABECERA = "publicaciones_cabecera";
    public static final String TABLA_PUBLICACIONES_DETALLE= "publicaciones_detalle";

    public BaseDeDatosHelper(Context context) {
        super(context, NOMBRE_BD, null, VERSION_BD);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREAR_TABLA = "CREATE TABLE " + TABLA_PUBLICACIONES_CABECERA + " (" +
                " id_publicacion INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " id_empresa INTEGER, "+
                " id_facultad INTEGER,"+
                " tipo_publicacion INTEGER,"+
                " descripcion TEXT,"+
                " contador_like INTEGER,"+
                " fecha_evento TEXT,"+
                " hora_inicio_evento TEXT,"+
                " hora_fin_evento TEXT,"+
                " usuario_creacion TEXT,"+
                " fecha_creacion TEXT"+
                ")";
        db.execSQL(SQL_CREAR_TABLA);
        SQL_CREAR_TABLA = "CREATE TABLE " + TABLA_PUBLICACIONES_DETALLE + " (" +
                " id_publicacion INTEGER, " +
                " secuencial INTEGER, "+
                " imagen_documento TEXT"+
                ")";
        db.execSQL(SQL_CREAR_TABLA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Actualizaciones si cambias la estructura
        db.execSQL("DROP TABLE IF EXISTS " + TABLA_PUBLICACIONES_CABECERA);
        onCreate(db);
    }
    public void insertarPublicacion(PublicacionCabecera publicacionCabecera) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("id_empresa", publicacionCabecera.id_empresa);
        valores.put("id_facultad", publicacionCabecera.id_facultad);
        valores.put("tipo_publicacion", publicacionCabecera.tipo_publicacion);
        valores.put("descripcion", publicacionCabecera.descripcion);
        valores.put("contador_like", publicacionCabecera.contador_like);
        valores.put("fecha_evento", publicacionCabecera.fecha_evento.toString());
        valores.put("hora_inicio_evento", publicacionCabecera.hora_inicio_evento.toString());
        valores.put("hora_fin_evento", publicacionCabecera.hora_fin_evento.toString());
        valores.put("usuario_creacion", publicacionCabecera.usuario_creacion);
        valores.put("fecha_creacion", publicacionCabecera.fecha_creacion.toString());
        db.insert(TABLA_PUBLICACIONES_CABECERA, null, valores);
        db.close();
    }
    public void insertarPublicacionDetalle(int id_publicacion, List<Uri> imagenes_list) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valores = new ContentValues();
        for (int i = 0; i < imagenes_list.size(); i++) {
            valores.put("id_publicacion", id_publicacion);
            valores.put("secuencial", i+1);
            valores.put("imagen_documento", imagenes_list.toString());
            db.insert(TABLA_PUBLICACIONES_DETALLE, null, valores);

        }

        db.close();
    }
    public List<PublicacionCabecera> obtenerPublicacionCabecera() {
        List<PublicacionCabecera> lista = new ArrayList<>();
        List<PublicacionCabecera.PublicacionDetalle> lista_detalle = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLA_PUBLICACIONES_CABECERA, null);

        if (cursor.moveToFirst()) {
            do {
                int id_publicacion = cursor.getInt(cursor.getColumnIndexOrThrow("id_publicacion"));
                int id_empresa = cursor.getInt(cursor.getColumnIndexOrThrow("id_empresa"));
                int id_facultad = cursor.getInt(cursor.getColumnIndexOrThrow("id_facultad"));
                int tipo_publicacion = cursor.getInt(cursor.getColumnIndexOrThrow("tipo_publicacion"));
                String descripcion = cursor.getString(cursor.getColumnIndexOrThrow("descripcion"));
                int contador_like = cursor.getInt(cursor.getColumnIndexOrThrow("contador_like"));
                String fecha_evento = cursor.getString(cursor.getColumnIndexOrThrow("fecha_evento"));
                String hora_inicio_evento = cursor.getString(cursor.getColumnIndexOrThrow("hora_inicio_evento"));
                String hora_fin_evento = cursor.getString(cursor.getColumnIndexOrThrow("hora_fin_evento"));
                String usuario_creacion = cursor.getString(cursor.getColumnIndexOrThrow("usuario_creacion"));
                String fecha_creacion = cursor.getString(cursor.getColumnIndexOrThrow("fecha_creacion"));
                lista.add(new PublicacionCabecera(id_publicacion,id_empresa,id_facultad,tipo_publicacion,descripcion,contador_like,fecha_evento,hora_inicio_evento,hora_fin_evento,usuario_creacion,fecha_creacion));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return lista;
    }

    public List<PublicacionCabecera.PublicacionDetalle> obtenerPublicacionDetalle() {
        List<PublicacionCabecera.PublicacionDetalle> lista = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLA_PUBLICACIONES_DETALLE, null);
        if (cursor.moveToFirst()) {
            do {
                int id_publicacion = cursor.getInt(cursor.getColumnIndexOrThrow("id_publicacion"));
                int secuencial = cursor.getInt(cursor.getColumnIndexOrThrow("secuencial"));
                String  imagen_documento = cursor.getString(cursor.getColumnIndexOrThrow("imagen_documento"));

                lista.add(new PublicacionCabecera.PublicacionDetalle(id_publicacion,secuencial,imagen_documento));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return lista;
    }
}
