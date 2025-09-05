package org.project.frogs.frogs_project_backoffice.controller;

import org.project.frogs.frogs_project_backoffice.model.Frog;
import org.project.frogs.frogs_project_backoffice.model.Habitat;
import org.project.frogs.frogs_project_backoffice.repository.FrogRepository;
import org.project.frogs.frogs_project_backoffice.repository.HabitatRepository;
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
    HabitatRepository habitatRepository;

    @Autowired
    FrogRepository frogRepository;

    @GetMapping
    public String index(Model model) {

        model.addAttribute("habitats", habitatRepository.findAll());

        return "habitats/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable Integer id, Model model) {

        Habitat habitatDetails = habitatRepository.findById(id).get();
        model.addAttribute("selectedHabitat", habitatDetails);

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
            model.addAttribute("frogs", frogRepository.findAll());
            return "habitats/create-or-edit";
        }

        habitatRepository.save(formHabitat);

        return "redirect:/habitats";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {

        model.addAttribute("habitat", habitatRepository.findById(id).get());
        return "habitats/create-or-edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("habitat") Habitat formHabitat, BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {

            model.addAttribute("frogs", frogRepository.findAll());
            return "habitats/create-or-edit";
        }

        habitatRepository.save(formHabitat);
        return "redirect:/habitats";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {

        Habitat habitatToDelete = habitatRepository.findById(id).get();

        for (Frog frog : habitatToDelete.getFrogs()) {
            frog.getHabitats().remove(habitatToDelete);
        }

        habitatRepository.deleteById(id);

        return "redirect:/habitats";
    }

}
