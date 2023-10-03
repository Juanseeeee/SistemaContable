package com.example.contabliumv2.Controller;

import com.example.contabliumv2.Dto.AsientoDTO;
import com.example.contabliumv2.Dto.DetalleDTO;
import com.example.contabliumv2.Model.Asiento;
import com.example.contabliumv2.Model.Cuenta;
import com.example.contabliumv2.Model.Detalle;
import com.example.contabliumv2.Repository.AsientoRepository;
import com.example.contabliumv2.Repository.CuentaRepository;
import com.example.contabliumv2.Repository.DetalleRepository;
import com.example.contabliumv2.Service.AsientoService.AsientoServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.auditing.CurrentDateTimeProvider;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@SessionAttributes("cuenta")
public class AsientoController {

    private final DetalleRepository detalleRepository;
    private final AsientoRepository asientoRepository;
    private final CuentaRepository cuentaRepository;
    private final AsientoServiceImpl asientoService;
    private List<Detalle> detallesTemporales = new ArrayList<Detalle>();
    private Asiento asientoAct = new Asiento();

    public AsientoController(AsientoRepository asientoRepository, CuentaRepository cuentaRepository, AsientoServiceImpl asientoService, ObjectMapper objectMapper, DetalleRepository detalleRepository, DetalleRepository detalleRepository1) {
        this.asientoRepository = asientoRepository;
        this.cuentaRepository = cuentaRepository;
        this.asientoService = asientoService;
        this.detalleRepository = detalleRepository1;
    }

    // ... Otros métodos de controlador ...

    @GetMapping("/agregar_detalle")
    public String agregar_detalle(Model model) {
        model.addAttribute("detalle", new Detalle()); // Crea una nueva instancia de Cuenta para el formulario
        return "redirect:/registrar_asiento";
    }


    @Transactional
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

        // Crear un nuevo Asiento
        Asiento asiento = asientoAct;
        asiento.setDescripcion("prueba1");
        asiento.setFecha(new Date());

// Crear un nuevo Detalle
        Detalle detalle = new Detalle();
        detalle.setAsiento(asiento); // Establecer la referencia al Asiento en el Detalle
        detalle.setCuenta(cuentaRepository.findByIdCuenta(detalleDTO.getCuenta().getId_cuenta()));
        detalle.setDebe(detalleDTO.getDebe());
        detalle.setHaber(detalleDTO.getHaber());

// Agregar el Detalle al Asiento
        asiento.getDetalles().add(detalle);

// Guardar el Asiento (esto también persistirá el Detalle debido a CascadeType.ALL)
        asientoRepository.save(asiento);


        detallesTemporales.add(detalle);

        // Agrega la lista de asientos temporales al modelo para que esté disponible en la vista.
        model.addAttribute("asientosTemporales", detallesTemporales);
        // Redirige nuevamente a la vista de entrada de asientos.
        return "registrar_asiento";
    }


    @Transactional
    @PostMapping("/guardar_asiento")
    public String guardar_asiento(@ModelAttribute AsientoDTO asientoDTO, Model model){
        List<Cuenta> cuentas = cuentaRepository.findAll();
        model.addAttribute("cuentas", cuentas);


        model.addAttribute("asientos",asientoAct);

        model.addAttribute("detalle",detallesTemporales);


        Asiento asiento = asientoAct;
        asiento.setDescripcion(asientoDTO.getDescripcion());
        asiento.setFecha(asientoDTO.getFecha());
        asiento.setDetalles(detallesTemporales);
        asiento.setId_usuario(asientoDTO.getId_usuario());

        for(Detalle asientoDet : detallesTemporales){
            asientoDet.setAsiento(asientoDet.getAsiento());
        }

        asientoService.save(asiento);
        detallesTemporales.clear();

        return "redirect:/home";
    }
}
