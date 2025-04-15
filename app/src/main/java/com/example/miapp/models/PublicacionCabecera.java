package com.example.miapp.models;


import java.util.List;

public class PublicacionCabecera {
    public int id_publicacion;
    public int id_empresa;
    public int id_facultad;
    public int tipo_publicacion;
    public String descripcion;
    public int contador_like;
    public String fecha_evento;
    public String hora_inicio_evento;
    public String hora_fin_evento;
    public String usuario_creacion;
    public String fecha_creacion;
    public List<PublicacionDetalle> detalle;

    public PublicacionCabecera(int id_empresa, int id_facultad, int tipo_publicacion, String descripcion, int contador_like, String fecha_evento, String hora_inicio_evento, String hora_fin_evento, String usuario_creacion, String fecha_creacion) {
        this.id_empresa = id_empresa;
        this.id_facultad = id_facultad;
        this.tipo_publicacion = tipo_publicacion;
        this.descripcion = descripcion;
        this.contador_like = contador_like;
        this.fecha_evento = fecha_evento;
        this.hora_inicio_evento = hora_inicio_evento;
        this.hora_fin_evento = hora_fin_evento;
        this.usuario_creacion = usuario_creacion;
        this.fecha_creacion = fecha_creacion;
    }

    public PublicacionCabecera(int id_publicacion, int id_empresa, int id_facultad, int tipo_publicacion, String descripcion, int contador_like, String fecha_evento, String hora_inicio_evento, String hora_fin_evento, String usuario_creacion, String fecha_creacion) {
        this.id_publicacion = id_publicacion;
        this.id_empresa = id_empresa;
        this.id_facultad = id_facultad;
        this.tipo_publicacion = tipo_publicacion;
        this.descripcion = descripcion;
        this.contador_like = contador_like;
        this.fecha_evento = fecha_evento;
        this.hora_inicio_evento = hora_inicio_evento;
        this.hora_fin_evento = hora_fin_evento;
        this.usuario_creacion = usuario_creacion;
        this.fecha_creacion = fecha_creacion;
    }

    public int getId_publicacion() {
        return id_publicacion;
    }

    public void setId_publicacion(int id_publicacion) {
        this.id_publicacion = id_publicacion;
    }

    public int getId_empresa() {
        return id_empresa;
    }

    public void setId_empresa(int id_empresa) {
        this.id_empresa = id_empresa;
    }

    public int getId_facultad() {
        return id_facultad;
    }

    public void setId_facultad(int id_facultad) {
        this.id_facultad = id_facultad;
    }

    public int getTipo_publicacion() {
        return tipo_publicacion;
    }

    public void setTipo_publicacion(int tipo_publicacion) {
        this.tipo_publicacion = tipo_publicacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getContador_like() {
        return contador_like;
    }

    public void setContador_like(int contador_like) {
        this.contador_like = contador_like;
    }

    public String getFecha_evento() {
        return fecha_evento;
    }

    public void setFecha_evento(String fecha_evento) {
        this.fecha_evento = fecha_evento;
    }

    public String getHora_inicio_evento() {
        return hora_inicio_evento;
    }

    public void setHora_inicio_evento(String hora_inicio_evento) {
        this.hora_inicio_evento = hora_inicio_evento;
    }

    public String getHora_fin_evento() {
        return hora_fin_evento;
    }

    public void setHora_fin_evento(String hora_fin_evento) {
        this.hora_fin_evento = hora_fin_evento;
    }

    public String getUsuario_creacion() {
        return usuario_creacion;
    }

    public void setUsuario_creacion(String usuario_creacion) {
        this.usuario_creacion = usuario_creacion;
    }

    public String getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(String fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public List<PublicacionDetalle> getDetalle() {
        return detalle;
    }

    public void setDetalle(List<PublicacionDetalle> detalle) {
        this.detalle = detalle;
    }

    public static class PublicacionDetalle{
        public int id_publicacion;
        public int secuencial;
        public int id_empresa;
        public String imagen_documento;

        public int getId_empresa() {
            return id_empresa;
        }

        public void setId_empresa(int id_empresa) {
            this.id_empresa = id_empresa;
        }

        public PublicacionDetalle() {
        }

        public PublicacionDetalle(int id_publicacion, int id_empresa, int secuencial, String imagen_documento) {
            this.id_publicacion = id_publicacion;
            this.secuencial = secuencial;
            this.id_empresa=id_empresa;
            this.imagen_documento = imagen_documento;
        }

        public int getId_publicacion() {
            return id_publicacion;
        }

        public void setId_publicacion(int id_publicacion) {
            this.id_publicacion = id_publicacion;
        }

        public int getSecuencial() {
            return secuencial;
        }

        public void setSecuencial(int secuencial) {
            this.secuencial = secuencial;
        }

        public String getImagen_documento() {
            return imagen_documento;
        }

        public void setImagen_documento(String imagen_documento) {
            this.imagen_documento = imagen_documento;
        }
    }
}
