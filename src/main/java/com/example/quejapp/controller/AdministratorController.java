package com.example.quejapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Controller
@RequestMapping("/administrator")
public class AdministratorController {
    @GetMapping("/dashboard")
    public String admin() {
        return "DashboardAdministrator";
    }
}
