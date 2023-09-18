package com.example.contabliumv2.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AsientoContableController {

    @GetMapping("/registro")
    public String registro_asiento(){
        return "registro_asiento";
    }

    @PostMapping("/registrar-asiento")
    public String registrarAsiento(@RequestParam("fecha") String fecha,
                                   @RequestParam("cuenta") String cuenta,
                                   @RequestParam("descripcion") String descripcion,
                                   @RequestParam("monto") Double monto) {
        // Aquí debes implementar la lógica para registrar el asiento contable
        // Puedes utilizar servicios y repositorios para interactuar con la base de datos

        // Por ejemplo, puedes llamar a un servicio para registrar el asiento
        // asientoService.registrarAsiento(fecha, cuenta, descripcion, monto);

        return "redirect:/asientos"; // Redirecciona a la página de lista de asientos contables
    }
}
