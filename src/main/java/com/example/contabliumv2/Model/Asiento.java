package com.example.contabliumv2.Model;

import com.example.contabliumv2.Dto.DetalleDTO;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "asiento")
public class Asiento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_asiento")
    private Integer idAsiento;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date fecha;

    private String descripcion;
    private Long idUsuario;




    public Asiento(Date fecha, String descripcion, Long id_usuario, List<Detalle> detalle) {
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.idUsuario = id_usuario;
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
        return idUsuario;
    }

    public void setId_usuario(Long id_usuario) {
        this.idUsuario = id_usuario;
    }

    public void setId_asiento(Integer id_asiento) {
        this.idAsiento = id_asiento;
    }

    public Integer getId_asiento() {
        return idAsiento;
    }


}
