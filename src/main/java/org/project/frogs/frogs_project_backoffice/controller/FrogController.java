package org.project.frogs.frogs_project_backoffice.controller;

import org.project.frogs.frogs_project_backoffice.repository.FrogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequestMapping("/frogs")
public class FrogController {

    @Autowired
    FrogRepository frogsRepository;
    
    @GetMapping("")
    public String index(Model model) {

        model.addAttribute("frogs", frogsRepository.findAll());

        return "frogs/index";
    }
    
}
