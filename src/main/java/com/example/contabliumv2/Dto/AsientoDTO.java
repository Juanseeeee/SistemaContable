package com.example.contabliumv2.Dto;

import com.example.contabliumv2.Model.Detalle;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

public class AsientoDTO {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecha;
    private String descripcion;
    private Long id_usuario;
    private List<Detalle> detalle;


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

    public List<Detalle> getDetalle() {
        return detalle;
    }

    public void setDetalle(List<Detalle> detalle) {
        this.detalle = detalle;
    }
}



