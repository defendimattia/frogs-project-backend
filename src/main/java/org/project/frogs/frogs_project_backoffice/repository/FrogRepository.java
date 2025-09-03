package org.project.frogs.frogs_project_backoffice.repository;

import java.util.List;

import org.project.frogs.frogs_project_backoffice.model.Frog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FrogRepository extends JpaRepository<Frog, Integer> {

    public List<Frog> findByCommonNameIgnoreCaseContainingOrScientificNameIgnoreCaseContaining(String commonName, String scientificName);
}
