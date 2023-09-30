package com.example.contabliumv2.Controller;

import com.example.contabliumv2.Dto.AsientoDTO;
import com.example.contabliumv2.Dto.DetalleDTO;
import com.example.contabliumv2.Model.Asiento;
import com.example.contabliumv2.Model.Cuenta;
import com.example.contabliumv2.Model.Detalle;
import com.example.contabliumv2.Repository.AsientoRepository;
import com.example.contabliumv2.Repository.CuentaRepository;
import com.example.contabliumv2.Service.AsientoService.AsientoServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AsientoController {

    private final AsientoRepository asientoRepository;
    private final CuentaRepository cuentaRepository;
    private final AsientoServiceImpl asientoService;
    private List<Detalle> asientosTemporales = new ArrayList<Detalle>();

    public AsientoController(AsientoRepository asientoRepository, CuentaRepository cuentaRepository, AsientoServiceImpl asientoService) {
        this.asientoRepository = asientoRepository;
        this.cuentaRepository = cuentaRepository;
        this.asientoService = asientoService;
    }

    // ... Otros métodos de controlador ...

    @GetMapping("/agregar_detalle")
    public String agregar_detalle(Model model) {
        model.addAttribute("detalle", new Detalle()); // Crea una nueva instancia de Cuenta para el formulario
        return "redirect:/registrar_asiento";
    }


    @PostMapping("/registrar_asiento")
    public String guardarDetalle(@ModelAttribute DetalleDTO detalleDTO, Model model) {
        List<Cuenta> cuentas = cuentaRepository.findAll();
        // Agrega la lista de cuentas al modelo
        model.addAttribute("cuentas", cuentas);
            // Se seleccionó "Debe"
        // Verifica el tipo de asiento
        if ("Debe".equals(detalleDTO.getTipoAsiento())) {
            detalleDTO.setDebe(detalleDTO.getMonto());
            detalleDTO.setHaber(0.0);
        } else if ("Haber".equals(detalleDTO.getTipoAsiento())) {
            detalleDTO.setHaber(detalleDTO.getMonto());
            detalleDTO.setDebe(0.0);
        } else {
            // Manejar el caso en el que no se haya seleccionado nada
        }

        // Creo una lista en el modelo para almacenar detalles de asientos temporales.
            Detalle detalle = new Detalle(detalleDTO.getAsiento(),
                    detalleDTO.getCuenta(),
                    detalleDTO.getDebe(),
                    detalleDTO.getHaber());


        asientosTemporales.add(detalle);

        // Agrega la lista de asientos temporales al modelo para que esté disponible en la vista.
        model.addAttribute("asientosTemporales", asientosTemporales);
        // Redirige nuevamente a la vista de entrada de asientos.
        return "registrar_asiento";
    }


    @PostMapping("/guardar_asiento")
    public String guardar_asiento(@ModelAttribute AsientoDTO asientoDTO, Model model){
        List<Cuenta> cuentas = cuentaRepository.findAll();
        model.addAttribute("cuentas", cuentas);

        List<Asiento> asientos = asientoRepository.findAll();
        model.addAttribute("asientos",asientos);

        Asiento asiento = new Asiento();
        asiento.setDescripcion(asientoDTO.getDescripcion());
        asiento.setFecha(asientoDTO.getFecha());
        asiento.setDetalle(asientoDTO.getDetalle());
        asiento.setId_usuario(asientoDTO.getId_usuario());

        asientoService.save(asiento);
        asientosTemporales.clear();

        return "redirect:/home";
    }
}
