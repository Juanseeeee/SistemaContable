package com.example.contabliumv2.Model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "asiento")
public class Asiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idasiento;

    public Date fecha;
    public String descripcion;
    public Long id_usuario;



    public Asiento(Date fecha, String descripcion, Long id_usuario, List<Cuenta> cuentas) {
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
        this.idasiento = id_asiento;
    }

    public Long getId_asiento() {
        return idasiento;
    }
}
