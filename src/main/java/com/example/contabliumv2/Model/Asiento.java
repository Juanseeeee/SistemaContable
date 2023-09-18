package com.example.contabliumv2.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class Asiento {

    public Date fecha;
    public String descripcion;
    public Long id_usuario;
    private Long id_asiento;

    public Asiento(Date fecha, String descripcion, Long id_usuario) {
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.id_usuario = id_usuario;
    }

    public Asiento() {

    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Long getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Long id_usuario) {
        this.id_usuario = id_usuario;
    }

    public void setId_asiento(Long id_asiento) {
        this.id_asiento = id_asiento;
    }

    @Id
    public Long getId_asiento() {
        return id_asiento;
    }
}
