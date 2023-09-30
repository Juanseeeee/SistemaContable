package com.example.contabliumv2.Service.AsientoService;

import com.example.contabliumv2.Dto.AsientoDTO;
import com.example.contabliumv2.Model.Asiento;
import com.example.contabliumv2.Repository.AsientoRepository;
import org.springframework.stereotype.Service;

@Service
public class AsientoServiceImpl {

    private AsientoRepository asientoRepository;

    public AsientoServiceImpl(AsientoRepository asientoRepository) {
        this.asientoRepository = asientoRepository;
    }

    public Asiento save(AsientoDTO asientoDTO){
        Asiento asiento = new Asiento(asientoDTO.getFecha(),asientoDTO.getDescripcion(),asientoDTO.getId_usuario(),asientoDTO.getDetalle());
        return asientoRepository.save(asiento);
    }

    public Asiento save(Asiento asiento){
        return asientoRepository.save(asiento);
    }


}
