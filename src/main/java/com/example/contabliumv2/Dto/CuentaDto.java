package com.example.contabliumv2.Dto;


public class CuentaDto {

    private String nombre;
    private String tipo_cuenta;
    private Double saldo_actual;
    private int codigo;
    private Integer recibe_saldo;

    public CuentaDto() {
    }

    public CuentaDto(String nombre, String tipo_cuenta, Double saldo_actual, int codigo, Integer recibe_saldo) {
        this.nombre = nombre;
        this.tipo_cuenta = tipo_cuenta;
        this.saldo_actual = saldo_actual;
        this.codigo = codigo;
        this.recibe_saldo = recibe_saldo;
    }


    public Integer getRecibe_saldo() {
        return recibe_saldo;
    }

    public void setRecibe_saldo(Integer recibe_saldo) {
        this.recibe_saldo = recibe_saldo;
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
