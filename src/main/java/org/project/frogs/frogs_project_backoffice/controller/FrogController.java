package org.project.frogs.frogs_project_backoffice.controller;

import org.project.frogs.frogs_project_backoffice.model.Frog;
import org.project.frogs.frogs_project_backoffice.service.ConservationStatusService;
import org.project.frogs.frogs_project_backoffice.service.FrogService;
import org.project.frogs.frogs_project_backoffice.service.HabitatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/frogs")
public class FrogController {

    @Autowired
    FrogService frogService;

    @Autowired
    HabitatService habitatService;

    @Autowired
    ConservationStatusService conservationStatusService;

    @GetMapping
    public String index(Model model) {

        model.addAttribute("frogs", frogService.getAllFrogs());

        return "frogs/index";
    }

    // cerca per nome comune o nome scientifico
    @GetMapping("/search")
    public String search(@RequestParam(name = "query") String query, Model model) {

        model.addAttribute("frogs", frogService.searchFrogs(query));

        return "frogs/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable Integer id, Model model) {

        model.addAttribute("selectedFrog", frogService.getFrogById(id));

        return "frogs/frogDetails";
    }

    @GetMapping("/create")
    public String create(Model model) {

        model.addAttribute("isNew", true);
        model.addAttribute("frog", new Frog());
        model.addAttribute("habitats", habitatService.getAllHabitats());
        model.addAttribute("conservationStatuses", conservationStatusService.getAllConservationStatuses());

        return "frogs/create-or-edit";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("frog") Frog formFrog, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {

            model.addAttribute("isNew", true);
            model.addAttribute("habitats", habitatService.getAllHabitats());
            model.addAttribute("conservationStatuses", conservationStatusService.getAllConservationStatuses());
            return "frogs/create-or-edit";
        }

        frogService.saveFrog(formFrog);
        return "redirect:/frogs";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {

        model.addAttribute("habitats", habitatService.getAllHabitats());
        model.addAttribute("conservationStatuses", conservationStatusService.getAllConservationStatuses());

        model.addAttribute("frog", frogService.getFrogById(id));
        return "frogs/create-or-edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("frog") Frog formFrog, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("habitats", habitatService.getAllHabitats());
            model.addAttribute("conservationStatuses", conservationStatusService.getAllConservationStatuses());
            return "frogs/create-or-edit";
        }

        frogService.saveFrog(formFrog);
        return "redirect:/frogs";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {

        frogService.deleteFrog(id);
        return "redirect:/frogs";
    }

}
