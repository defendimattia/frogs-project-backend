package org.project.frogs.frogs_project_backoffice.controller;

import java.util.List;

import org.project.frogs.frogs_project_backoffice.model.Frog;
import org.project.frogs.frogs_project_backoffice.repository.ConservationStatusRepository;
import org.project.frogs.frogs_project_backoffice.repository.FrogRepository;
import org.project.frogs.frogs_project_backoffice.repository.HabitatRepository;
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
    FrogRepository frogRepository;

    @Autowired
    ConservationStatusRepository conservationStatusRepository;

    @Autowired
    HabitatRepository habitatRepository;

    @GetMapping
    public String index(Model model) {

        model.addAttribute("frogs", frogRepository.findAll());

        return "frogs/index";
    }

    // cerca per nome comune o nome scientifico
    @GetMapping("/search")
    public String search(@RequestParam(name = "query") String query, Model model) {

        List<Frog> filteredFrogs = frogRepository
                .findByCommonNameIgnoreCaseContainingOrScientificNameIgnoreCaseContaining(query, query);

        model.addAttribute("frogs", filteredFrogs);

        return "frogs/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable Integer id, Model model) {

        Frog frogDetails = frogRepository.findById(id).get();

        model.addAttribute("selectedFrog", frogDetails);

        return "frogs/frogDetails";
    }

    @GetMapping("/create")
    public String create(Model model) {

        model.addAttribute("isNew", true);
        model.addAttribute("frog", new Frog());
        model.addAttribute("conservationStatuses", conservationStatusRepository.findAll());
        model.addAttribute("habitats", habitatRepository.findAll());

        return "frogs/create-or-edit";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("frog") Frog formFrog, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {

            model.addAttribute("isNew", true);
            model.addAttribute("conservationStatuses", conservationStatusRepository.findAll());
            model.addAttribute("habitats", habitatRepository.findAll());
            return "frogs/create-or-edit";
        }

        frogRepository.save(formFrog);
        return "redirect:/frogs";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {

        model.addAttribute("conservationStatuses", conservationStatusRepository.findAll());
        model.addAttribute("habitats", habitatRepository.findAll());

        model.addAttribute("frog", frogRepository.findById(id).get());
        return "frogs/create-or-edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("frog") Frog formFrog, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("conservationStatuses", conservationStatusRepository.findAll());
            model.addAttribute("habitats", habitatRepository.findAll());
            return "frogs/create-or-edit";
        }

        frogRepository.save(formFrog);
        return "redirect:/frogs";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {

        frogRepository.deleteById(id);
        return "redirect:/frogs";
    }

}
