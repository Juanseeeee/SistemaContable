package com.example.contabliumv2.Controller;

import com.example.contabliumv2.Dto.CuentaDto;
import com.example.contabliumv2.Model.Cuenta;
import com.example.contabliumv2.Service.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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
        return Arrays.asList("Activo", "Pasivo", "Patrimonio neto", "Resultados");
    }

    @GetMapping("/agregar_cuenta")
    public String agregar_cuenta(Model model) {
        model.addAttribute("cuenta", new Cuenta()); // Crea una nueva instancia de Cuenta para el formulario
        return "agregar_cuenta";
    }

    @PostMapping("/agregar_cuenta")
    public String guardarCuenta(@ModelAttribute("cuenta") CuentaDto cuentaDto,Model model) {
        // Lógica para guardar la cuenta en la base de datos a través del servicio

        Cuenta cuentaChk = cuentaService.findByAccountName(cuentaDto.getNombre());
        if (cuentaChk.getNombre().equals(cuentaDto.getNombre())) {
            model.addAttribute("cuentaExistenteError","La cuenta ya esta registrada.");
            return "redirect:/agregar_cuenta?failed";
        }
        cuentaService.save(cuentaDto);
        return "redirect:/agregar_cuenta?success"; // Redirige a la página de lista de cuentas después de guardar
    }
}
