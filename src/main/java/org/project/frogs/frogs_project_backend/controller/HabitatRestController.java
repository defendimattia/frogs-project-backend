package org.project.frogs.frogs_project_backend.controller;

import java.util.List;
import java.util.Optional;

import org.project.frogs.frogs_project_backend.model.Habitat;
import org.project.frogs.frogs_project_backend.service.HabitatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/habitats")
public class HabitatRestController {

    @Autowired
    private HabitatService habitatService;

    @GetMapping
    public List<Habitat> index() {
        List<Habitat> habitats = habitatService.getAllHabitats();
        return habitats;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Habitat> show(@PathVariable Integer id) {

        Optional<Habitat> habitatTry = habitatService.findById(id);

        if (habitatTry.isEmpty()) {
            return new ResponseEntity<Habitat>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Habitat>(habitatTry.get(), HttpStatus.OK);
    }

    // @PostMapping()
    // public ResponseEntity<Habitat> store(@Valid @RequestBody Habitat habitat) {
    //     return new ResponseEntity<Habitat>(habitatService.saveHabitat(habitat), HttpStatus.CREATED);
    // }

    // @PutMapping("/{id}")
    // public ResponseEntity<Habitat> update(@Valid @RequestBody Habitat habitat, @PathVariable Integer id) {

    //     if (habitatService.findById(id).isEmpty()) {
    //         return new ResponseEntity<Habitat>(HttpStatus.NOT_FOUND);
    //     }

    //     habitat.setId(id);
    //     return new ResponseEntity<Habitat>(habitatService.saveHabitat(habitat), HttpStatus.OK);
    // }

    // @DeleteMapping("/{id}")
    // public ResponseEntity<Habitat> delete(@PathVariable Integer id) {

    //     if (habitatService.findById(id).isEmpty()) {
    //         return new ResponseEntity<Habitat>(HttpStatus.NOT_FOUND);
    //     }

    //     habitatService.deleteHabitat(id);
    //     return new ResponseEntity<Habitat>(HttpStatus.NO_CONTENT);
    // }
}
