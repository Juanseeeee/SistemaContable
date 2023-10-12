package com.example.contabliumv2.Service;


import com.example.contabliumv2.Dto.CuentaDto;
import com.example.contabliumv2.Model.Cuenta;

import java.util.List;


public interface CuentaService {

    Cuenta findByAccountName(String name);

    Cuenta save(CuentaDto cuentaDto);

    List<Cuenta> findAll();

}
