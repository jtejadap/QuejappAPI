package com.example.quejapp.controller;

import com.example.quejapp.model.Usuario;
import com.example.quejapp.services.UserService;
import jakarta.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class PublicController {
    private final UserService servicio;
    private final PasswordEncoder passwordEncoder;

    public PublicController(UserService servicio, PasswordEncoder passwordEncoder) {
        this.servicio = servicio;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/login")
    public String login() {
        return "Login";
    }
    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("registro", new Usuario());
        return "SignUp";
    }

    @PostMapping("/signup")
    public String saveNewUser (@Valid Usuario registro, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "SignUp";
        }

        Usuario nuevoUsuario = servicio.CreateUser(registro);
        model.addAttribute("registro", nuevoUsuario);
        return "RegistroCompleto";
    }

    @GetMapping("/access-denied")
    public String error(Principal principal, Model model) {
        model.addAttribute("link",  servicio.returnHomeLinkByRole(principal));
        return "ErrorAccessDenied";
    }

    @GetMapping("/test")
    public String dashboard() {
        throw new RuntimeException("Excepción controlada");
    }
}
