package com.example.contabliumv2.Controller;


import com.example.contabliumv2.Model.Cuenta;
import com.example.contabliumv2.Model.Detalle;
import com.example.contabliumv2.Repository.CuentaRepository;
import com.example.contabliumv2.Repository.DetalleRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ReportesController {

    private DetalleRepository detalleRepository ;
    private CuentaRepository cuentaRepository;

    public ReportesController(DetalleRepository detalleRepository, CuentaRepository cuentaRepository) {
        this.detalleRepository = detalleRepository;
        this.cuentaRepository = cuentaRepository;
    }

    @GetMapping("/generar_libro_diario")
    public String generarLibroDiario(Model model){
        //Necesito los detalles.
        List<Detalle> detalles = detalleRepository.findAll();


        model.addAttribute("detalles",detalles);


        return "generar_libro_diario";
    }

    @GetMapping("/generar_libro_mayor")
    public String generarLibroMayor(@RequestParam(name = "cuenta", required = false) Integer idCuenta, Model model) {
        // Si idCuenta es null, muestra todos los detalles, de lo contrario, filtra por el ID de la cuenta.
        Cuenta cuentaFiltro = cuentaRepository.findByIdCuenta(idCuenta);
        List<Detalle> detalles;
        if (idCuenta != null) {
            detalles = detalleRepository.findAllByCuenta(cuentaFiltro);
        } else {
            detalles = detalleRepository.findAll();
        }

        List<Cuenta> cuentas = cuentaRepository.findAll();
        model.addAttribute("cuentas", cuentas);
        model.addAttribute("detalles", detalles);

        return "generar_libro_mayor";
    }


}
