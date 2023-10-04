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
import jakarta.servlet.http.HttpSession;
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
@SessionAttributes({"cuenta", "asientoId"})
public class AsientoController {

    private final DetalleRepository detalleRepository;
    private final AsientoRepository asientoRepository;
    private final CuentaRepository cuentaRepository;
    private final AsientoServiceImpl asientoService;
    private List<Detalle> detallesTemporales = new ArrayList<Detalle>();

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
    public String guardarDetalle(@ModelAttribute DetalleDTO detalleDTO, Model model, HttpSession session) {
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

        //Cuando entre en registrar asiento busca el ultimo asiento que cree en preparar_asiento
        //Si no es el ultimo que cree en preparar asiento es el ultimo que cree que lo quiero modificar por algun error
        Asiento asiento = asientoRepository.findByIdAsiento(asientoService.obtenerUltimoAsientoId());


        // Crear un nuevo Detalle
        Detalle detalle = new Detalle();
        detalle.setAsiento(asiento); // Establecer la referencia al Asiento en el Detalle
        detalle.setCuenta(cuentaRepository.findByIdCuenta(detalleDTO.getCuenta().getId_cuenta()));
        detalle.setDebe(detalleDTO.getDebe());
        detalle.setHaber(detalleDTO.getHaber());
        //detalleRepository.save(detalle);

        detallesTemporales.add(detalle);
        //Agrego detalles temporales a la vista para que se visualize
        model.addAttribute("ListaDetallesTemporales",detallesTemporales);
        // Redirige nuevamente a la vista de entrada de asientos.
        return "registrar_asiento";
    }


    @Transactional
    @PostMapping("/guardar_asiento")
    public String guardar_asiento(@ModelAttribute AsientoDTO asientoDTO, Model model) throws Exception{
        List<Cuenta> cuentas = cuentaRepository.findAll();
        model.addAttribute("cuentas", cuentas);


        model.addAttribute("detalle",detallesTemporales);



        Asiento asiento = asientoRepository.findByIdAsiento(asientoService.obtenerUltimoAsientoId());
        //Si el debe es distinto que el haber el asiento no se guarda.
        Double debe = 0.0;
        Double haber = 0.0;
        for(Detalle detalle : detallesTemporales){
            debe += detalle.getDebe();
            haber += detalle.getHaber();
        }
        if(!debe.equals(haber)){
            //throw new Exception("El debe no coincide con el haber. Es incorrecto");
            detallesTemporales.clear();
            model.addAttribute("errorDebeHaber","El debe no coincide con el haber. Es incorrecto");
            return "registrar_asiento";
        }

        asiento.setDescripcion(asientoDTO.getDescripcion());
        asiento.setFecha(asientoDTO.getFecha());
        asiento.setId_usuario(asientoDTO.getId_usuario());

        for(Detalle detalle : detallesTemporales){
            detalleRepository.save(detalle);
        }

        detallesTemporales.clear();

        asientoService.save(asiento);

        return "redirect:/home";
    }

    //Este metodo solo crea un asiento y le pasa el control a registrar_asiento que registra detalles
    @GetMapping("/preparar_asiento")
    public String preparar_asiento(Model model){

        //Creo el asiento y lo guardo para que persista
        Asiento asiento = new Asiento();
        asiento.setDescripcion("prueba1");
        asiento.setFecha(new Date());
        asientoRepository.save(asiento);

        detallesTemporales.clear();

        //Redirecciono y le doy al control con el asiento creado a que los detalles se cargen con ese id_asiento
        //nuevo
        return "redirect:/registrar_asiento";
    }

    @GetMapping("/detalle/{id}")
    public String verDetalle(@PathVariable("id") Long id, Model model) {
        // Lógica para cargar el detalle del asiento con el ID proporcionado
        // y agregarlo al modelo
        Asiento asiento = asientoRepository.findByIdAsiento(asientoService.obtenerUltimoAsientoId());
        model.addAttribute("asiento", asiento);
        List<Detalle> detalles = detalleRepository.findAllByAsiento(asiento);
        model.addAttribute("ListaDetallesTemporales",detalles);
        return "registrar_asiento"; // Nombre de la página de detalle (detalle.html)
    }


}

