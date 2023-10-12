package com.example.contabliumv2.Controller;

import com.example.contabliumv2.Dto.CuentaDto;
import com.example.contabliumv2.Model.Cuenta;
import com.example.contabliumv2.Service.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class CuentaController {

    @Autowired
    private CuentaService cuentaService;

    public CuentaController(CuentaService cuentaService) {
        this.cuentaService = cuentaService;
    }

    @ModelAttribute("categorias")
    public List<String> getCategorias() {
        // Retorna una lista de las categorías que deseas mostrar
        return Arrays.asList("Activo", "Pasivo", "Patrimonio neto", "Resultados+","Resultados-");
    }

    @GetMapping("/agregar_cuenta")
    public String agregar_cuenta(Model model) {
        model.addAttribute("cuenta", new Cuenta()); // Crea una nueva instancia de Cuenta para el formulario
        List<Cuenta> cuentas = cuentaService.findAll();
        List<Cuenta> cuentasPadre = new ArrayList<>();
        for (Cuenta cuenta : cuentas){
            if (cuenta.getRecibe_saldo() == 0){
                cuentasPadre.add(cuenta);
            }
        }
        System.out.println(cuentasPadre);
        model.addAttribute("cuentas_padre",cuentasPadre);
        return "agregar_cuenta";
    }

    @PostMapping("/agregar_cuenta")
    public String guardarCuenta(@ModelAttribute("cuenta") CuentaDto cuentaDto,Model model) {
        List<Cuenta> cuentas = cuentaService.findAll();
        List<Cuenta> cuentasPadre = new ArrayList<>();
        for (Cuenta cuenta : cuentas){
            if (cuenta.getRecibe_saldo() == 0){
                cuentasPadre.add(cuenta);
            }
        }
        System.out.println(cuentasPadre);
        model.addAttribute("cuentas_padre",cuentasPadre);
        //Chequeo si la cuenta es valida y si no esta registrada.
        Cuenta cuentaChk = cuentaService.findByAccountName(cuentaDto.getNombre());
        if (cuentaChk != null && cuentaChk.getNombre().equals(cuentaDto.getNombre())) {
            model.addAttribute("cuentaExistenteError","La cuenta ya esta registrada.");
            return "redirect:/agregar_cuenta?failed";
        }
        //Si no esta registrada miro si es una cuenta padre o no
        if (cuentaDto.getRecibe_saldo() == 0){
            //Si es padre entonces
        }
        cuentaService.save(cuentaDto);
        return "redirect:/agregar_cuenta?success"; // Redirige a la página de lista de cuentas después de guardar
    }

    @GetMapping("/plan_de_cuentas")
    public String planDeCuentas(Model model){
        model.addAttribute("cuentas",cuentaService.findAll());
        return "plan_de_cuentas";
    }
}
