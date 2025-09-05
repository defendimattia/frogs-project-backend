package org.project.frogs.frogs_project_backoffice.controller;

import org.project.frogs.frogs_project_backoffice.model.ConservationStatus;
import org.project.frogs.frogs_project_backoffice.model.Frog;
import org.project.frogs.frogs_project_backoffice.model.Habitat;
import org.project.frogs.frogs_project_backoffice.repository.ConservationStatusRepository;
import org.project.frogs.frogs_project_backoffice.repository.FrogRepository;
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
@RequestMapping("/conservationStatuses")
public class ConservationStatusController {

    @Autowired
    ConservationStatusRepository conservationStatusRepository;

    @GetMapping
    public String index(Model model) {

        model.addAttribute("conservationStatuses", conservationStatusRepository.findAll());

        return "conservationStatuses/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable Integer id, Model model) {

        ConservationStatus conservationStatusDetails = conservationStatusRepository.findById(id).get();
        model.addAttribute("selectedConservationStatus", conservationStatusDetails);

        return "conservationStatuses/conservationStatusDetails";
    }

    @GetMapping("/create")
    public String create(Model model) {

        model.addAttribute("isNew", true);
        model.addAttribute("conservationStatus", new ConservationStatus());

        return "conservationStatuses/create-or-edit";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("conservationStatus") ConservationStatus formConservationStatus,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {

            model.addAttribute("isNew", true);
            return "conservationStatuses/create-or-edit";
        }

        conservationStatusRepository.save(formConservationStatus);

        return "redirect:/conservationStatuses";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {

        model.addAttribute("conservationStatus", conservationStatusRepository.findById(id).get());
        return "conservationStatuses/create-or-edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("conservationStatus") ConservationStatus formConservationStatus,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {

            return "conservationStatuses/create-or-edit";
        }

        conservationStatusRepository.save(formConservationStatus);
        return "redirect:/conservationStatuses";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {

        ConservationStatus conservationStatusToDelete = conservationStatusRepository.findById(id).get();

        for (Frog frog : conservationStatusToDelete.getFrogs()) {
            frog.setConservationStatus(conservationStatusRepository.findById(13).get());
        }

        conservationStatusRepository.deleteById(id);

        return "redirect:/conservationStatuses";
    }

}
