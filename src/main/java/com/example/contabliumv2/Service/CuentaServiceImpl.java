package com.example.contabliumv2.Service;

import com.example.contabliumv2.Dto.CuentaDto;
import com.example.contabliumv2.Model.Cuenta;
import com.example.contabliumv2.Repository.CuentaRepository;
import org.springframework.stereotype.Service;

@Service
public class CuentaServiceImpl implements CuentaService{

    private CuentaRepository cuentaRepository;

    public CuentaServiceImpl(CuentaRepository cuentaRepository) {
        this.cuentaRepository = cuentaRepository;
    }

    @Override
    public Cuenta findByAccountName(String name) {
        return cuentaRepository.findByNombre(name);
    }


    @Override
    public Cuenta save(CuentaDto cuentaDto) {
        Cuenta cuenta = new Cuenta(cuentaDto.getNombre(),cuentaDto.getTipo_cuenta(), cuentaDto.getSaldo_actual(),cuentaDto.getCodigo());
        return cuentaRepository.save(cuenta);
    }



}
