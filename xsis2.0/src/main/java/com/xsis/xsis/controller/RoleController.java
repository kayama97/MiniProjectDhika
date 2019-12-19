package com.xsis.xsis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * RoleController
 */
@Controller
@RequestMapping("/role")
public class RoleController {

    @GetMapping("/")
    public String showVacancy(Model model) {
        return "pages/role";
    }
    
}