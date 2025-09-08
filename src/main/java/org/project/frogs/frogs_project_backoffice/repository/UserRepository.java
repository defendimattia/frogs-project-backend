package org.project.frogs.frogs_project_backoffice.repository;

import java.util.Optional;

import org.project.frogs.frogs_project_backoffice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);

}
