package org.project.frogs.frogs_project_backend.controller;

import org.project.frogs.frogs_project_backend.repository.ConservationStatusRepository;
import org.project.frogs.frogs_project_backend.repository.FrogRepository;
import org.project.frogs.frogs_project_backend.repository.HabitatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {

    @Autowired
    FrogRepository frogRepository;

    @Autowired
    HabitatRepository habitatRepository;

    @Autowired
    ConservationStatusRepository conservationStatusRepository;

    @GetMapping("/")
    public String home(Model model) {

        model.addAttribute("frogsCount", frogRepository.count());
        model.addAttribute("habitatsCount", habitatRepository.count());
        model.addAttribute("statusesCount", conservationStatusRepository.count());
        return "home";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/logout")
    public String logout() {
        return "logout";
    }
    
}
