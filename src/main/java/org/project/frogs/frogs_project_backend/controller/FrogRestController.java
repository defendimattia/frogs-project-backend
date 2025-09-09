package org.project.frogs.frogs_project_backend.controller;

import java.util.List;
import java.util.Optional;

import org.project.frogs.frogs_project_backend.model.Frog;
import org.project.frogs.frogs_project_backend.service.FrogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/frogs")
public class FrogRestController {

    @Autowired
    private FrogService frogService;

    @GetMapping
    public List<Frog> index() {
        List<Frog> frogs = frogService.getAllFrogs();
        return frogs;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Frog> show(@PathVariable Integer id) {

        Optional<Frog> frogTry = frogService.findById(id);

        if (frogTry.isEmpty()) {
            return new ResponseEntity<Frog>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Frog>(frogTry.get(), HttpStatus.OK);
    }

    // @PostMapping()
    // public ResponseEntity<Frog> store(@Valid @RequestBody Frog frog) {
    //     return new ResponseEntity<Frog>(frogService.saveFrog(frog), HttpStatus.CREATED);
    // }

    // @PutMapping("/{id}")
    // public ResponseEntity<Frog> update(@Valid @RequestBody Frog frog, @PathVariable Integer id) {

    //     if (frogService.findById(id).isEmpty()) {
    //         return new ResponseEntity<Frog>(HttpStatus.NOT_FOUND);
    //     }

    //     frog.setId(id);
    //     return new ResponseEntity<Frog>(frogService.saveFrog(frog), HttpStatus.OK);
    // }

    // @DeleteMapping("/{id}")
    // public ResponseEntity<Frog> delete(@PathVariable Integer id) {

    //     if (frogService.findById(id).isEmpty()) {
    //         return new ResponseEntity<Frog>(HttpStatus.NOT_FOUND);
    //     }

    //     frogService.deleteFrog(id);
    //     return new ResponseEntity<Frog>(HttpStatus.NO_CONTENT);
    // }

}
