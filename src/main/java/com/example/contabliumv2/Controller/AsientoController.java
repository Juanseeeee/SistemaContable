package com.example.contabliumv2.Controller;

import com.example.contabliumv2.Dto.CuentaAsientoDTO;
import com.example.contabliumv2.Model.Asiento;
import com.example.contabliumv2.Model.Cuenta;
import com.example.contabliumv2.Model.cuentas_asientos;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AsientoController {

    // ... Otros métodos de controlador ...

    @GetMapping("/agregar_detalle")
    public String agregar_detalle(Model model) {
        model.addAttribute("detalle", new cuentas_asientos()); // Crea una nueva instancia de Cuenta para el formulario
        return "redirect:/registrar_asiento";
    }

    @PostMapping("/registrar_asiento")
    public String guardarAsiento(@ModelAttribute CuentaAsientoDTO asientoDTO, Model model) {
        Asiento asiento = new Asiento();
        asiento.setDescripcion(asientoDTO.getDescripcion());

        Double debe = asientoDTO.getDebe();
        Double haber = asientoDTO.getHaber();
            // Se seleccionó "Debe"
        if (debe != null && debe > 0) {
            asientoDTO.setDebe(asientoDTO.getMonto());
            asientoDTO.setHaber(0.0);
            // Se seleccionó "Haber"
        } else if (haber != null && haber > 0) {
            asientoDTO.setHaber(asientoDTO.getMonto());
            asientoDTO.setDebe(0.0);

        } else {
            // Manejar el caso en el que no se haya seleccionado nada
        }

        // Creo una lista en el modelo para almacenar detalles de asientos temporales.
        List<CuentaAsientoDTO> asientosTemporales = new ArrayList<>();
        asientosTemporales.add(asientoDTO);

        // Agrega la lista de asientos temporales al modelo para que esté disponible en la vista.
        model.addAttribute("asientosTemporales", asientosTemporales);
        // Redirige nuevamente a la vista de entrada de asientos.
        return "registrar_asiento";
    }
}
