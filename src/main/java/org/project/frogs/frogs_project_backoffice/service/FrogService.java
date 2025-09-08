package org.project.frogs.frogs_project_backoffice.service;

import java.util.List;
import java.util.Optional;

import org.project.frogs.frogs_project_backoffice.model.ConservationStatus;
import org.project.frogs.frogs_project_backoffice.model.Frog;
import org.project.frogs.frogs_project_backoffice.model.Habitat;
import org.project.frogs.frogs_project_backoffice.repository.ConservationStatusRepository;
import org.project.frogs.frogs_project_backoffice.repository.FrogRepository;
import org.project.frogs.frogs_project_backoffice.repository.HabitatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class FrogService {

    @Autowired
    private FrogRepository frogRepository;

    @Autowired
    private ConservationStatusRepository conservationStatusRepository;

    @Autowired
    private HabitatRepository habitatRepository;

    public List<Frog> getAllFrogs() {
        return frogRepository.findAll();
    }

    public List<Frog> searchFrogs(String query) {
        return frogRepository.findByCommonNameIgnoreCaseContainingOrScientificNameIgnoreCaseContaining(query, query);
    }

    public Frog getFrogById(Integer id) {
        Optional<Frog> frogTry = frogRepository.findById(id);

        if (frogTry.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "frog not found with id " + id);
        }

        return frogTry.get();
    }

    public Frog saveFrog(Frog frog) {
        return frogRepository.save(frog);
    }

    public Frog updateFrog(Frog frog) {

        Optional<Frog> frogTry = frogRepository.findById(frog.getId());

        if (frogTry.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "frog not found with id " + frog.getId());
        }

        return frogRepository.save(frog);
    }

    public void deleteFrog(Integer id) {

        Optional<Frog> frogTry = frogRepository.findById(id);

        if (frogTry.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "frog not found with id " + id);
        }

        frogRepository.deleteById(id);
    }

    public List<Habitat> getAllHabitats() {
        return habitatRepository.findAll();
    }

    public List<ConservationStatus> getAllConservationStatuses() {
        return conservationStatusRepository.findAll();
    }
}
