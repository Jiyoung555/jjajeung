package com.example.jjajeung.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StaticController {
    @GetMapping("/privacy")
    public String privacy(Model model){
        model.addAttribute("pri", "개인정보");
        return "statics/privacy";
    }

    @GetMapping("/terms")
    public String terms(Model model){
        model.addAttribute("ter", "약관");
        return "statics/terms";
    }
}
