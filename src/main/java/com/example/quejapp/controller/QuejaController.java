package com.example.quejapp.controller;

import com.example.quejapp.model.Queja;
import com.example.quejapp.model.repositories.QuejaRepository;
import com.example.quejapp.model.repositories.UsuarioRepository;
import com.example.quejapp.util.UsuarioModelAssembler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class QuejaController {
    private final QuejaRepository repositorio;

    public QuejaController(QuejaRepository repository) {
        this.repositorio = repository;
    }

    @ModelAttribute("todasLasQuejas")
    public List<Queja> inicializarQuejas() {
        return this.repositorio.findAll();
    }

    @RequestMapping("/complaint/list")
    public String listarQuejas() {
        return "QuejasListado";
    }


    @GetMapping("/complaint")
    public String crearQueja(Model model) {
        model.addAttribute("queja", new Queja());
        return "QuejaFormulario";
    }

    @PostMapping("/complaint")
    public String guardarQueja(@ModelAttribute Queja queja, Model model) {
        Queja nuevaQueja = repositorio.save(queja);
        model.addAttribute("queja", nuevaQueja);
        return "QuejaCompletada";
    }
}
