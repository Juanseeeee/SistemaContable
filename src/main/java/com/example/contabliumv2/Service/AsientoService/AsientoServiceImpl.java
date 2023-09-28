package com.example.contabliumv2.Service.AsientoService;


import com.example.contabliumv2.Dto.CuentaAsientoDTO;
import com.example.contabliumv2.Model.cuentas_asientos;
import com.example.contabliumv2.Repository.AsientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AsientoServiceImpl {

    @Autowired
    private AsientoRepository asientoRepository;

    public cuentas_asientos save(CuentaAsientoDTO cuentaAsientoDTO){
        cuentas_asientos detalle = new cuentas_asientos(cuentaAsientoDTO.getAsiento(),
                cuentaAsientoDTO.getCuenta(),
                cuentaAsientoDTO.getDebe(),
                cuentaAsientoDTO.getHaber(),
                cuentaAsientoDTO.getMonto());


        return asientoRepository.save(detalle);
    }

}
