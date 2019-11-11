package com.estomatologia.estomatologia.controller;

import com.estomatologia.estomatologia.model.Authorities;
import com.estomatologia.estomatologia.model.Patient;
import com.estomatologia.estomatologia.model.User;
import com.estomatologia.estomatologia.repository.AuthorityRepository;
import com.estomatologia.estomatologia.repository.UserRepository;
import com.estomatologia.estomatologia.service.AuthorizationService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private PasswordEncoder passwordEncoder;
    private final AuthorizationService authorizationService;

    public MainController(UserRepository userRepository, AuthorityRepository authorityRepository, PasswordEncoder passwordEncoder, AuthorizationService authorizationService) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
        this.passwordEncoder = passwordEncoder;
        this.authorizationService = authorizationService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login/login";
    }

    @GetMapping("/services")
    public String services() {
        return "services/services";
    }

    @GetMapping("/tariff")
    public String tariff() {
        return "tariff/tariff";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact/contact";
    }

    @GetMapping("/register")
    public String register(Model model) {
        User loggedUser = authorizationService.getLoggedUser();

        if (loggedUser == null) {
            model.addAttribute("patients", new Patient());
            return "register";
        } else {
            return "error";
        }
    }

    @PostMapping("/register")
    public String register(@ModelAttribute Patient patient) {
        User loggedUser = authorizationService.getLoggedUser();
        if (loggedUser == null) {
            if (userRepository.findByUsername(patient.getUserPatient().getUsername()).isEmpty()) {
                Patient patient1 = new Patient();
                patient1.setName(patient.getName());
                patient1.setSurname(patient.getSurname());
                patient1.setAddress(patient.getAddress());
                patient1.setPesel(patient.getPesel());
                patient1.setPhoneNumber(patient.getPhoneNumber());
                patient1.setMedicamentsTakenPermamently(patient.getMedicamentsTakenPermamently());
                patient1.setChronicDiseases(patient.getChronicDiseases());

                User userPatient = new User();
                userPatient.setUsername(patient.getUserPatient().getUsername());
                userPatient.setPassword(passwordEncoder.encode(patient.getUserPatient().getPassword()));
                userPatient.setEnabled(true);
                userPatient.setPatient(patient1);

                patient1.setUserPatient(userPatient);
                userRepository.save(userPatient);

                Authorities authorities = new Authorities();
                authorities.setUsername(patient.getUserPatient().getUsername());
                authorities.setAuthority("ROLE_PATIENT");

                authorityRepository.save(authorities);
                return "success";
            } else {
                return "error";
            }
        } else {
            return "error";
        }
    }
}
