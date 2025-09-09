package org.project.frogs.frogs_project_backoffice.controller;

import java.util.List;
import java.util.Optional;

import org.project.frogs.frogs_project_backoffice.model.ConservationStatus;
import org.project.frogs.frogs_project_backoffice.service.ConservationStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/conservationStatuses")
public class ConservationStatusRestController {

    @Autowired
    private ConservationStatusService conservationStatusService;

    @GetMapping
    public List<ConservationStatus> index() {
        List<ConservationStatus> statuses = conservationStatusService.getAllConservationStatuses();
        return statuses;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConservationStatus> show(@PathVariable Integer id) {

        Optional<ConservationStatus> statusTry = conservationStatusService.findById(id);

        if (statusTry.isEmpty()) {
            return new ResponseEntity<ConservationStatus>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<ConservationStatus>(statusTry.get(), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<ConservationStatus> store(@Valid @RequestBody ConservationStatus status) {
        return new ResponseEntity<ConservationStatus>(conservationStatusService.saveConservationStatus(status), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConservationStatus> update(@Valid @RequestBody ConservationStatus status,
            @PathVariable Integer id) {

        if (conservationStatusService.findById(id).isEmpty()) {
            return new ResponseEntity<ConservationStatus>(HttpStatus.NOT_FOUND);
        }

        status.setId(id);
        return new ResponseEntity<ConservationStatus>(conservationStatusService.saveConservationStatus(status), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ConservationStatus> delete(@PathVariable Integer id) {

        if (conservationStatusService.findById(id).isEmpty()) {
            return new ResponseEntity<ConservationStatus>(HttpStatus.NOT_FOUND);
        }

        conservationStatusService.deleteConservationStatus(id);;
        return new ResponseEntity<ConservationStatus>(HttpStatus.NO_CONTENT);
    }
}
