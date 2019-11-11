package com.estomatologia.estomatologia.service;

import com.estomatologia.estomatologia.model.User;
import com.estomatologia.estomatologia.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {

    private final UserRepository userRepository;

    public AuthorizationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getLoggedUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            String loggedUsername = ((UserDetails) principal).getUsername();

            if (userRepository.findByUsername(loggedUsername).isPresent()) {
                return userRepository.findByUsername(loggedUsername).get();
            } else {
                return null;
            }
        } else {
            return null;
        }

    }
}
