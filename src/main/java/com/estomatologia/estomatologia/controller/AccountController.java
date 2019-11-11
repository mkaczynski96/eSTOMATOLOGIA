package com.estomatologia.estomatologia.controller;

import com.estomatologia.estomatologia.model.User;
import com.estomatologia.estomatologia.repository.*;
import com.estomatologia.estomatologia.service.security.AuthorizationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccountController {

    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final AdministratorRepository administratorRepository;
    private final ReceptionistRepository receptionistRepository;
    private final VisitRepository visitRepository;

    private final AuthorizationService authorizationService;

    public AccountController(PatientRepository patientRepository, DoctorRepository doctorRepository, AdministratorRepository administratorRepository, ReceptionistRepository receptionistRepository, VisitRepository visitRepository, AuthorizationService authorizationService) {
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
        this.administratorRepository = administratorRepository;
        this.receptionistRepository = receptionistRepository;
        this.visitRepository = visitRepository;
        this.authorizationService = authorizationService;
    }


    @GetMapping("/myaccount")
    public String myAccount() {
        return "account/account";
    }


    @GetMapping("/myaccount/mydata")
    public String myData(Model model) {
        User loggedUser = authorizationService.getLoggedUser();

        if (loggedUser == null) {
            return "error";
        } else {
            Long loggedUserId = loggedUser.getId();
            if (doctorRepository.findById(loggedUserId).isPresent()) {

                model.addAttribute("account", doctorRepository.findById(loggedUserId).get());
                return "account/mydata";
            } else if (patientRepository.findById(loggedUserId).isPresent()) {

                model.addAttribute("account", patientRepository.findById(loggedUserId).get());
                return "account/mydata";
            } else if (receptionistRepository.findById(loggedUserId).isPresent()) {

                model.addAttribute("account", receptionistRepository.findById(loggedUserId).get());
                return "account/mydata";
            } else if (administratorRepository.findById(loggedUserId).isPresent()) {

                model.addAttribute("account", administratorRepository.findById(loggedUserId).get());
                return "account/mydata";
            } else {
                return "error";
            }

        }
    }


    @GetMapping("/myaccount/calendarofvisits")
    public String calendarOfVisits(Model model) {
        User loggedUser = authorizationService.getLoggedUser();

        if (loggedUser == null) {
            return "error";
        } else {
            Long loggedUserId = loggedUser.getId();
            if (doctorRepository.findById(loggedUserId).isPresent()) {

                return "account/calendarofvisits";
            } else if (patientRepository.findById(loggedUserId).isPresent()) {

                return "account/calendarofvisits";
            } else if (receptionistRepository.findById(loggedUserId).isPresent()) {

                model.addAttribute("visits", visitRepository.findAll());
                return "account/calendarofvisits";
            } else if (administratorRepository.findById(loggedUserId).isPresent()) {

                return "account/calendarofvisits";
            } else {
                return "error";
            }

        }
    }


}
