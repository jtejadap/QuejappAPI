package com.example.quejapp.controller;

import com.example.quejapp.DTOs.AttendDTO;
import com.example.quejapp.DTOs.ReportDTO;
import com.example.quejapp.model.Queja;
import com.example.quejapp.model.repositories.QuejaRepository;
import com.example.quejapp.services.AttendantService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Controller
@RequestMapping("/attendant")
public class AdministratorController {
    private final AttendantService servicio;

    public AdministratorController(AttendantService servicio) {
        this.servicio = servicio;
    }

    @ModelAttribute("reportes")
    public List<ReportDTO> listarQuejas() {
        return servicio.listarQuejas();
    }

    @GetMapping("/dashboard")
    public String admin() {
        return "DashboardAdministrator";
    }

    @GetMapping("/manage/{id}")
    public String buscarQueja(@PathVariable Long id, Model model) {
        ReportDTO reporte = servicio.buscarQuejaPorId(id);
        if (reporte == null) {
            model.addAttribute("queja",id);
            return "QuejaNoEncontrada";
        }
        model.addAttribute("reporte", reporte);
        model.addAttribute("atender", new AttendDTO(reporte.getEstado(),reporte.getRespuesta()));
        return "AtenderQueja";
    }

    @PostMapping("/manage/{id}")
    public String modificarQueja(
            Principal principal,
            @PathVariable Long id,
            @Valid @ModelAttribute("atender") AttendDTO atender,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            ReportDTO reporte = servicio.buscarQuejaPorId(id);
            model.addAttribute("reporte", reporte);
            return "AtenderQueja";
        }
        servicio.actualizarQueja(id, atender, principal.getName());
        return "redirect:/attendant/dashboard";
    }


}
