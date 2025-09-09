package org.project.frogs.frogs_project_backend.controller;

import org.project.frogs.frogs_project_backend.model.Habitat;
import org.project.frogs.frogs_project_backend.service.FrogService;
import org.project.frogs.frogs_project_backend.service.HabitatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/habitats")
public class HabitatController {

    @Autowired
    HabitatService habitatService;

    @Autowired
    FrogService frogService;

    @GetMapping
    public String index(Model model) {

        model.addAttribute("habitats", habitatService.getAllHabitats());

        return "habitats/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable Integer id, Model model) {

        model.addAttribute("selectedHabitat", habitatService.getHabitatById(id));

        return "habitats/habitatDetails";
    }

    @GetMapping("/create")
    public String create(Model model) {

        model.addAttribute("isNew", true);
        model.addAttribute("habitat", new Habitat());

        return "habitats/create-or-edit";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("habitat") Habitat formHabitat, BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {

            model.addAttribute("isNew", true);
            model.addAttribute("frogs", frogService.getAllFrogs());
            return "habitats/create-or-edit";
        }

        habitatService.saveHabitat(formHabitat);

        return "redirect:/habitats";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {

        model.addAttribute("habitat", habitatService.getHabitatById(id));

        return "habitats/create-or-edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("habitat") Habitat formHabitat, BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {

            model.addAttribute("frogs", frogService.getAllFrogs());
            return "habitats/create-or-edit";
        }

        habitatService.saveHabitat(formHabitat);
        return "redirect:/habitats";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {

        habitatService.deleteHabitat(id);

        return "redirect:/habitats";
    }

}
