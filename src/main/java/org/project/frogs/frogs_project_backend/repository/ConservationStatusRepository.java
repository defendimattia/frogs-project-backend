package org.project.frogs.frogs_project_backend.repository;

import org.project.frogs.frogs_project_backend.model.ConservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConservationStatusRepository extends JpaRepository<ConservationStatus, Integer> {

}
