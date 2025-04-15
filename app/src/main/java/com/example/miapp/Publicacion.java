package com.example.miapp;

public class Publicacion {
    private String nombre;
    private String textoPublicacion;
    private int imagenPerfil;
    private int imagenPublicacion;
    private int es_sqlite;
    private String imagenPublicacion_base64;
    public Publicacion(String nombre, String textoPublicacion, int imagenPerfil, int imagenPublicacion) {
        this.nombre = nombre;
        this.textoPublicacion = textoPublicacion;
        this.imagenPerfil = imagenPerfil;
        this.imagenPublicacion = imagenPublicacion;
    }
    public Publicacion(String nombre, String textoPublicacion, int imagenPerfil, String imagenPublicacion,int es_sqlite) {
        this.nombre = nombre;
        this.textoPublicacion = textoPublicacion;
        this.imagenPerfil = imagenPerfil;
        this.imagenPublicacion_base64 = imagenPublicacion;
        this.es_sqlite = es_sqlite;
    }

    public String getImagenPublicacion_base64() {
        return imagenPublicacion_base64;
    }

    public void setImagenPublicacion_base64(String imagenPublicacion_base64) {
        this.imagenPublicacion_base64 = imagenPublicacion_base64;
    }

    public int getEs_sqlite() {
        return es_sqlite;
    }

    public void setEs_sqlite(int es_sqlite) {
        this.es_sqlite = es_sqlite;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTextoPublicacion() {
        return textoPublicacion;
    }

    public int getImagenPerfil() {
        return imagenPerfil;
    }

    public int getImagenPublicacion() {
        return imagenPublicacion;
    }
}
