package com.mulapp.passwordapp.Modelo;

public class Password {
    String id, titulo, cuenta, nombreUsuario, password, sitioWeb, nota, tiempoRegistro, tiempoActualizacion;

    public Password(String id, String titulo, String cuenta, String nombreUsuario, String password, String sitioWeb, String nota, String tiempoRegistro, String tiempoActualizacion) {
        this.id = id;
        this.titulo = titulo;
        this.cuenta = cuenta;
        this.nombreUsuario = nombreUsuario;
        this.password = password;
        this.sitioWeb = sitioWeb;
        this.nota = nota;
        this.tiempoRegistro = tiempoRegistro;
        this.tiempoActualizacion = tiempoActualizacion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSitioWeb() {
        return sitioWeb;
    }

    public void setSitioWeb(String sitioWeb) {
        this.sitioWeb = sitioWeb;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public String getTiempoRegistro() {
        return tiempoRegistro;
    }

    public void setTiempoRegistro(String tiempoRegistro) {
        this.tiempoRegistro = tiempoRegistro;
    }

    public String getTiempoActualizacion() {
        return tiempoActualizacion;
    }

    public void setTiempoActualizacion(String tiempoActualizacion) {
        this.tiempoActualizacion = tiempoActualizacion;
    }
}
