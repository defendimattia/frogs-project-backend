package org.project.frogs.frogs_project_backoffice.service;

import java.util.List;
import java.util.Optional;

import org.project.frogs.frogs_project_backoffice.model.Frog;
import org.project.frogs.frogs_project_backoffice.model.Habitat;
import org.project.frogs.frogs_project_backoffice.repository.FrogRepository;
import org.project.frogs.frogs_project_backoffice.repository.HabitatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class HabitatService {

    @Autowired
    private HabitatRepository habitatRepository;

    @Autowired
    private FrogRepository frogRepository;

    public List<Habitat> getAllHabitats() {
        return habitatRepository.findAll();
    }

    public Habitat getHabitatById(Integer id) {
        Optional<Habitat> habitatTry = habitatRepository.findById(id);

        if (habitatTry.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "habitat not found with id " + id);
        }

        return habitatTry.get();
    }

    public Habitat saveHabitat(Habitat habitat) {
        return habitatRepository.save(habitat);
    }

    public Habitat updateHabitat(Habitat habitat) {
        Optional<Habitat> habitatTry = habitatRepository.findById(habitat.getId());

        if (habitatTry.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "habitat not found with id " + habitat.getId());
        }

        return habitatRepository.save(habitat);
    }

    public void deleteHabitat(Integer id) {
        Optional<Habitat> habitatTry = habitatRepository.findById(id);

        if (habitatTry.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "habitat not found with id " + id);
        }

        Habitat habitatToDelete = habitatTry.get();

        for (Frog frog : habitatToDelete.getFrogs()) {
            frog.getHabitats().remove(habitatToDelete);
        }

        habitatRepository.deleteById(id);
    }

    public List<Frog> getAllFrogs() {
        return frogRepository.findAll();
    }
}
