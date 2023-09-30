package com.example.contabliumv2.Model;

import com.example.contabliumv2.Dto.DetalleDTO;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "asiento")
public class Asiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_asiento;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    public Date fecha;
    public String descripcion;
    public Long id_usuario;
    @OneToMany
    @JoinColumn(name = "id_detalle")
    public List<Detalle> detalle ;



    public Asiento(Date fecha, String descripcion, Long id_usuario, List<Detalle> detalle) {
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.id_usuario = id_usuario;
        this.detalle = detalle;
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

    public void setId_asiento(Integer id_asiento) {
        this.id_asiento = id_asiento;
    }

    public Integer getId_asiento() {
        return id_asiento;
    }

    public List<Detalle> getDetalle() {
        return detalle;
    }

    public void setDetalle(List<Detalle> detalle) {
        this.detalle = detalle;
    }
}
