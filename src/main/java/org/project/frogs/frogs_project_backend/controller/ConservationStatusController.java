package org.project.frogs.frogs_project_backend.controller;

import org.project.frogs.frogs_project_backend.model.ConservationStatus;
import org.project.frogs.frogs_project_backend.service.ConservationStatusService;
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
    ConservationStatusService conservationStatusService;

    @GetMapping
    public String index(Model model) {

        model.addAttribute("conservationStatuses", conservationStatusService.getAllConservationStatuses());

        return "conservationStatuses/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable Integer id, Model model) {

        model.addAttribute("selectedConservationStatus", conservationStatusService.getConservationStatusById(id));

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

        conservationStatusService.saveConservationStatus(formConservationStatus);

        return "redirect:/conservationStatuses";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {

        model.addAttribute("selectedConservationStatus", conservationStatusService.getConservationStatusById(id));

        return "conservationStatuses/create-or-edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("conservationStatus") ConservationStatus formConservationStatus,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {

            return "conservationStatuses/create-or-edit";
        }

        conservationStatusService.saveConservationStatus(formConservationStatus);

        return "redirect:/conservationStatuses";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {

        conservationStatusService.deleteConservationStatus(id);

        return "redirect:/conservationStatuses";
    }

}
