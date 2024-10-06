package com.example.quejapp.controller;

import com.example.quejapp.model.Rol;
import com.example.quejapp.model.Usuario;
import com.example.quejapp.model.repositories.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PublicController {
    private final UsuarioRepository repositorio;
    private final PasswordEncoder passwordEncoder;

    public PublicController(UsuarioRepository repository, PasswordEncoder passwordEncoder) {
        this.repositorio = repository;
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
        registro.setRol(Rol.USER.name());
        registro.setPassword(passwordEncoder.encode(registro.getPassword()));
        Usuario nuevoUsuario = repositorio.save(registro);
        model.addAttribute("registro", nuevoUsuario);
        return "RegistroCompleto";
    }
}
