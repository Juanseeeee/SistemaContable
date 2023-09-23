package com.example.contabliumv2.Controller;


import com.example.contabliumv2.Dto.UserDto;
import com.example.contabliumv2.Model.Cuenta;
import com.example.contabliumv2.Model.User;
import com.example.contabliumv2.Repository.CuentaRepository;
import com.example.contabliumv2.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserDetailsService userDetailsService;
    private CuentaRepository cuentaRepository;

    private UserService userService;

    public UserController(CuentaRepository cuentaRepository, UserService userService) {
        this.cuentaRepository = cuentaRepository;
        this.userService = userService;
    }

    @GetMapping("/home")
    public String home(Model model, Principal principal){
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("userdetail",userDetails);
        List<Cuenta> cuentas = cuentaRepository.findAll();

        // Agrega la lista de cuentas al modelo
        model.addAttribute("cuentas", cuentas);

        return "home";
    }

    @GetMapping("/registrar_asiento")
    public String mostrarRegistrarAsiento(Model model) {
        model.addAttribute("showRegistrarAsiento", true);
        model.addAttribute("showGenerarReportes", false);
        model.addAttribute("showVerAsientos", false);
        return "home"; // Redirige a la página principal para mostrar el fragmento actualizado
    }

    @GetMapping("/generar_reportes")
    public String mostrarGenerarReportes(Model model) {
        model.addAttribute("showRegistrarAsiento", false);
        model.addAttribute("showGenerarReportes", true);
        model.addAttribute("showVerAsientos", false);
        return "home"; // Redirige a la página principal para mostrar el fragmento actualizado
    }

    @GetMapping("/ver_asientos")
    public String mostrarVerAsientos(Model model) {
        model.addAttribute("showRegistrarAsiento", false);
        model.addAttribute("showGenerarReportes", false);
        model.addAttribute("showVerAsientos", true);
        return "home"; // Redirige a la página principal para mostrar el fragmento actualizado
    }

    @GetMapping("/login")
    public String login(Model model , UserDto userDto){
        model.addAttribute("user",userDto);
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model , UserDto userDto){
        model.addAttribute("user",userDto);
        return "register";
    }

    @PostMapping("/register")
    public String registerSave(@ModelAttribute("user") UserDto userDto ,Model model){
        User user = userService.findByUsername(userDto.getUsername());
        if (user != null){
            model.addAttribute("userexist",user);
            return "register";
        }
        userService.save(userDto);
        return "redirect:/register?success";
    }


}
