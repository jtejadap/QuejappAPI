package com.example.quejapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/moderator")
public class ModeratorController {
    @GetMapping("/dashboard")
    public String admin() {
        return "DashboardModerator";
    }
}
