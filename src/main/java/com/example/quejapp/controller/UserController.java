package com.example.quejapp.controller;

import com.example.quejapp.DTOs.ReportDTO;
import com.example.quejapp.model.Queja;
import com.example.quejapp.services.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService servicio;

    public UserController(UserService servicio) {
        this.servicio = servicio;
    }

    @ModelAttribute("quejas")
    public List<ReportDTO> listarQuejas(Principal principal) {
        return servicio.listarQuejas(principal.getName());
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "DashboardUser";
    }

    @GetMapping("/complaint")
    public String crearQueja(Model model) {
        model.addAttribute("queja", new Queja());
        return "QuejaFormulario";
    }

    @PostMapping("/complaint")
    public String guardarQueja(
            Principal principal,
            @Valid  @ModelAttribute("queja") Queja queja,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            return "QuejaFormulario";
        }

        Queja nuevaQueja = servicio.createComplaintForUser(queja, principal.getName());
        model.addAttribute("queja", nuevaQueja);
        return "QuejaCompletada";
    }

    @GetMapping("/details/{id}")
    public String buscarQueja(@PathVariable Long id, Model model) {
        ReportDTO queja = servicio.buscarQueja(id);
        model.addAttribute("reporte", queja);
        return "QuejaDetalles";
    }

}
