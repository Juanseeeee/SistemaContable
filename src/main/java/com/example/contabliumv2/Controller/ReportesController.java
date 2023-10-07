package com.example.contabliumv2.Controller;


import com.example.contabliumv2.Model.Cuenta;
import com.example.contabliumv2.Model.Detalle;
import com.example.contabliumv2.Repository.CuentaRepository;
import com.example.contabliumv2.Repository.DetalleRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String generarLibroMayor(Model model){
        //Necesito los detalles.
        List<Detalle> detalles = detalleRepository.findAll();
        //Necesito una cuenta
        List<Cuenta> cuentas = cuentaRepository.findAll();
        model.addAttribute("cuentas",cuentas);

        model.addAttribute("detalles",detalles);


        return "generar_libro_mayor";
    }

    @PostMapping("/generar_libro_mayor")
    public String generarLibroPost(@PathVariable("id_cuenta") Integer id_cuenta, Model model){
        Cuenta cuentaFiltro = cuentaRepository.findByIdCuenta(id_cuenta);
        List<Detalle> detalles = detalleRepository.findAllByCuenta(cuentaFiltro);
        model.addAttribute("detalles",detalles);

        return "redirect:/generar_libro_mayor?cuenta/id_cuenta";
    }
}
