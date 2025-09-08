package org.project.frogs.frogs_project_backoffice.service;

import java.util.List;
import java.util.Optional;

import org.project.frogs.frogs_project_backoffice.model.ConservationStatus;
import org.project.frogs.frogs_project_backoffice.model.Frog;
import org.project.frogs.frogs_project_backoffice.repository.ConservationStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ConservationStatusService {

    @Autowired
    private ConservationStatusRepository conservationStatusRepository;

    public List<ConservationStatus> getAllConservationStatuses() {
        return conservationStatusRepository.findAll();
    }

    public ConservationStatus getConservationStatusById(Integer id) {
        Optional<ConservationStatus> statusTry = conservationStatusRepository.findById(id);

        if (statusTry.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "conservation status not found with id " + id);
        }

        return statusTry.get();
    }

    public ConservationStatus saveConservationStatus(ConservationStatus conservationStatus) {
        return conservationStatusRepository.save(conservationStatus);
    }

    public ConservationStatus updateConservationStatus(ConservationStatus conservationStatus) {
        Optional<ConservationStatus> statusTry = conservationStatusRepository.findById(conservationStatus.getId());

        if (statusTry.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "conservation status not found with id " + conservationStatus.getId());
        }

        return conservationStatusRepository.save(conservationStatus);
    }

    public void deleteConservationStatus(Integer id) {
        Optional<ConservationStatus> statusTry = conservationStatusRepository.findById(id);

        if (statusTry.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "conservation status not found with id " + id);
        }

        ConservationStatus conservationStatusToDelete = statusTry.get();

        for (Frog frog : conservationStatusToDelete.getFrogs()) {
            frog.setConservationStatus(conservationStatusRepository.findById(13).get());
        }

        conservationStatusRepository.deleteById(id);
    }
}
