package com.example.contabliumv2.Service;


import com.example.contabliumv2.Model.Cuenta;


public interface CuentaService {

    Cuenta findByAccountName(String name);

    Cuenta save(Cuenta cuentaDto);
}
