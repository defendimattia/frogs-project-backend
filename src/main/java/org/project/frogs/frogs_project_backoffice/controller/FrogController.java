package org.project.frogs.frogs_project_backoffice.controller;

import java.util.List;

import org.project.frogs.frogs_project_backoffice.model.Frog;
import org.project.frogs.frogs_project_backoffice.repository.FrogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequestMapping("/frogs")
public class FrogController {

    @Autowired
    FrogRepository frogsRepository;

    @GetMapping
    public String index(Model model) {

        model.addAttribute("frogs", frogsRepository.findAll());

        return "frogs/index";
    }

    @GetMapping("/search")
    public String search(@RequestParam(name = "query") String query, Model model) {

        List<Frog> filteredFrogs = frogsRepository.findByCommonNameIgnoreCaseContainingOrScientificNameIgnoreCaseContaining(query, query);

        model.addAttribute("frogs", filteredFrogs);

        return "frogs/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable Integer id, Model model) {

        Frog frogDetails = frogsRepository.findById(id).get();

        model.addAttribute("selectedFrog", frogDetails);

        return "frogs/frogDetails";
    }

}
