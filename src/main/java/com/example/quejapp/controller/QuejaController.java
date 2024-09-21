package com.example.quejapp.controller;

import com.example.quejapp.model.Queja;
import com.example.quejapp.model.repositories.QuejaRepository;
import com.example.quejapp.model.repositories.UsuarioRepository;
import com.example.quejapp.util.UsuarioModelAssembler;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.thymeleaf.spring6.util.FieldUtils.hasErrors;

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
    public String guardarQueja(@Valid Queja queja, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "QuejaFormulario";
        }
        Queja nuevaQueja = repositorio.save(queja);
        model.addAttribute("queja", nuevaQueja);
        return "QuejaCompletada";
    }

    @GetMapping("/complaint/{id}")
    public String buscarQueja(@PathVariable Long id, Model model) {
        Optional<Queja> optionalQueja = repositorio.findById(id);
        if (!optionalQueja.isPresent()) {
            model.addAttribute("identificador",id);
            return "QuejaNoEncontrada";
        }
        Queja queja = optionalQueja.get();
        model.addAttribute("queja", queja);
        return "QuejaModificar";
    }

    @PostMapping("/complaint/{id}")
    public String modificarQueja(@PathVariable Long id,@Valid Queja queja, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "QuejaModificar";
        }
        Queja nuevaQueja = repositorio.save(queja);
        model.addAttribute("queja", nuevaQueja);
        return "QuejaCompletada";
    }

    @GetMapping("/complaint/delete/{id}")
    public String eliminarQueja(@PathVariable Long id, Model model) {
        Optional<Queja> optionalQueja = repositorio.findById(id);
        if (!optionalQueja.isPresent()) {
            model.addAttribute("identificador",id);
            return "QuejaNoEncontrada";
        }
        repositorio.delete(optionalQueja.get());
        return "redirect:/complaint/list";
    }
}
