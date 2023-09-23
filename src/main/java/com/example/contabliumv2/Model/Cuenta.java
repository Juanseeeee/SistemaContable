package com.example.contabliumv2.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "cuenta")
public class Cuenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cuenta")
    private Long id_cuenta;
    @Column(unique = true)
    private String nombre;
    private String tipo_cuenta;
    private Double saldo_actual;
    private int codigo;

    public Cuenta(String nombre, String tipo_cuenta, Double saldo_actual, int codigo) {
        this.nombre = nombre;
        this.tipo_cuenta = tipo_cuenta;
        this.saldo_actual = saldo_actual;
        this.codigo = codigo;
    }

    public Cuenta() {

    }

    public void setId_cuenta(Long id_cuenta) {
        this.id_cuenta = id_cuenta;
    }

    public Long getId_cuenta() {
        return id_cuenta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo_cuenta() {
        return tipo_cuenta;
    }

    public void setTipo_cuenta(String tipo_cuenta) {
        this.tipo_cuenta = tipo_cuenta;
    }

    public Double getSaldo_actual() {
        return saldo_actual;
    }

    public void setSaldo_actual(Double saldo_actual) {
        this.saldo_actual = saldo_actual;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
}
