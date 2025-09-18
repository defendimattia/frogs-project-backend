package org.project.frogs.frogs_project_backend.repository;

import java.util.List;

import org.project.frogs.frogs_project_backend.model.Frog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FrogRepository extends JpaRepository<Frog, Integer> {

    public Page<Frog> findByCommonNameIgnoreCaseContainingOrScientificNameIgnoreCaseContaining(String commonName,
            String scientificName,
            Pageable pageable);

    public Page<Frog> findByHabitatsId(Integer habitatId, Pageable pageable);

    public Page<Frog> findByConservationStatusId(Integer statusId, Pageable pageable);

    public List<Frog> findByCommonNameIgnoreCaseContainingOrScientificNameIgnoreCaseContaining(String commonName,
            String scientificName);

    public List<Frog> findByHabitatsId(Integer habitatId);

    public List<Frog> findByConservationStatusId(Integer statusId);
}
