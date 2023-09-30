package com.example.contabliumv2.Service.DetalleService;


import com.example.contabliumv2.Dto.DetalleDTO;
import com.example.contabliumv2.Model.Detalle;
import com.example.contabliumv2.Repository.DetalleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetalleImpl {

    @Autowired
    private DetalleRepository asientoRepository;

    public Detalle save(DetalleDTO detalleDTO){
        Detalle detalle = new Detalle(detalleDTO.getAsiento(),
                detalleDTO.getCuenta(),
                detalleDTO.getDebe(),
                detalleDTO.getHaber());


        return asientoRepository.save(detalle);
    }


}
