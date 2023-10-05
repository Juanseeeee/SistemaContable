package com.example.contabliumv2.Model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "cuenta")
public class Cuenta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cuenta")
    private Integer idCuenta;
    @Column(unique = true)
    private String nombre;
    private String tipo_cuenta;
    private Integer recibe_saldo;
    private Double saldo_actual;
    private int codigo;

    public Cuenta(String nombre, String tipo_cuenta, Double saldo_actual, int codigo, int recibe_saldo) {
        this.nombre = nombre;
        this.tipo_cuenta = tipo_cuenta;
        this.saldo_actual = saldo_actual;
        this.codigo = codigo;
        this.recibe_saldo = recibe_saldo;
    }

    public Cuenta(String nombre, String tipo_cuenta, Double saldo_actual, int codigo) {
        this.nombre = nombre;
        this.tipo_cuenta = tipo_cuenta;
        this.saldo_actual = saldo_actual;
        this.codigo = codigo;
    }

    public Cuenta() {

    }

    public void setId_cuenta(Integer id_cuenta) {
        this.idCuenta = id_cuenta;
    }

    public Integer getId_cuenta() {
        return idCuenta;
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

    @Override
    public String toString() {
        return nombre;
    }

    public Integer getRecibe_saldo() {
        return recibe_saldo;
    }

    public void setRecibe_saldo(Integer recibe_saldo) {
        this.recibe_saldo = recibe_saldo;
    }

    public void imputarCuenta(Double monto){
        this.saldo_actual += monto;
    }
}
