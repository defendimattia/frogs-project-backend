package org.project.frogs.frogs_project_backoffice.security;

import java.util.Optional;

import org.project.frogs.frogs_project_backoffice.model.User;
import org.project.frogs.frogs_project_backoffice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DatabaseUserDetailsService implements UserDetailsService{

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> userTry = userRepository.findByUsername(username);
        if (userTry.isEmpty()) {
            throw new UsernameNotFoundException("username not found");
        }

        return new DatabaseUserDetails(userTry.get());
    }
    
}
