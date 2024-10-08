package com.example.quejapp.controller;

import com.example.quejapp.model.Queja;
import com.example.quejapp.model.repositories.QuejaRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/complaint")
public class ComplaintController {
    private final QuejaRepository repositorio;

    public ComplaintController(QuejaRepository repository) {
        this.repositorio = repository;
    }

    @ModelAttribute("todasLasQuejas")
    public List<Queja> inicializarQuejas() {
        return this.repositorio.findAll();
    }

    @RequestMapping("/list")
    public String listarQuejas() {
        return "QuejasListado";
    }


    @GetMapping("/new")
    public String crearQueja(Model model) {
        model.addAttribute("queja", new Queja());
        return "QuejaFormulario";
    }

    @PostMapping("/save")
    public String guardarQueja(@Valid Queja queja, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "QuejaFormulario";
        }
        Queja nuevaQueja = repositorio.save(queja);
        model.addAttribute("queja", nuevaQueja);
        return "QuejaCompletada";
    }

    @GetMapping("/edit/{id}")
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

    @PostMapping("/edit/{id}")
    public String modificarQueja(@PathVariable Long id,@Valid Queja queja, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "QuejaModificar";
        }
        Queja nuevaQueja = repositorio.save(queja);
        model.addAttribute("queja", nuevaQueja);
        return "QuejaCompletada";
    }

    @GetMapping("/delete/{id}")
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
