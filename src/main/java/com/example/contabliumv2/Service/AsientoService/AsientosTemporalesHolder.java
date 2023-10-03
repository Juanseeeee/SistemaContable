package com.example.contabliumv2.Service.AsientoService;

import com.example.contabliumv2.Model.Detalle;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
public class AsientosTemporalesHolder implements Serializable {
    private List<Detalle> asientosTemporales = new ArrayList<>();

    public List<Detalle> getAsientosTemporales() {
        return asientosTemporales;
    }

    public void add(Detalle detalle){
        asientosTemporales.add(detalle);
    }

    public void clear(){
        asientosTemporales.clear();
    }
}
