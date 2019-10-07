package com.estomatologia.estomatologia.controller;

import com.estomatologia.estomatologia.model.User;
import com.estomatologia.estomatologia.repository.AdministratorRepository;
import com.estomatologia.estomatologia.repository.DoctorRepository;
import com.estomatologia.estomatologia.repository.PatientRepository;
import com.estomatologia.estomatologia.repository.ReceptionistRepository;
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

    private final AuthorizationService authorizationService;

    public AccountController(PatientRepository patientRepository, DoctorRepository doctorRepository, AdministratorRepository administratorRepository, ReceptionistRepository receptionistRepository, AuthorizationService authorizationService) {
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
        this.administratorRepository = administratorRepository;
        this.receptionistRepository = receptionistRepository;
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
                String name = doctorRepository.findById(loggedUserId).get().getName();
                String surname = doctorRepository.findById(loggedUserId).get().getSurname();
                String pesel = doctorRepository.findById(loggedUserId).get().getPesel();
                String phoneNumber = doctorRepository.findById(loggedUserId).get().getPhoneNumber();

                model.addAttribute("name", name);
                model.addAttribute("surname", surname);
                model.addAttribute("pesel", pesel);
                model.addAttribute("phoneNumber", phoneNumber);

                return "account/mydata";
            } else if (patientRepository.findById(loggedUserId).isPresent()) {
                String name = patientRepository.findById(loggedUserId).get().getName();
                String surname = patientRepository.findById(loggedUserId).get().getSurname();
                String pesel = patientRepository.findById(loggedUserId).get().getPesel();
                String phoneNumber = patientRepository.findById(loggedUserId).get().getPhoneNumber();

                model.addAttribute("name", name);
                model.addAttribute("surname", surname);
                model.addAttribute("pesel", pesel);
                model.addAttribute("phoneNumber", phoneNumber);

                return "account/mydata";
            } else if (receptionistRepository.findById(loggedUserId).isPresent()) {
                String name = receptionistRepository.findById(loggedUserId).get().getName();
                String surname = receptionistRepository.findById(loggedUserId).get().getSurname();
                String pesel = receptionistRepository.findById(loggedUserId).get().getPesel();
                String phoneNumber = receptionistRepository.findById(loggedUserId).get().getPhoneNumber();

                model.addAttribute("name", name);
                model.addAttribute("surname", surname);
                model.addAttribute("pesel", pesel);
                model.addAttribute("phoneNumber", phoneNumber);

                return "account/mydata";
            } else if (administratorRepository.findById(loggedUserId).isPresent()) {
                String name = administratorRepository.findById(loggedUserId).get().getName();
                String surname = administratorRepository.findById(loggedUserId).get().getSurname();
                String pesel = administratorRepository.findById(loggedUserId).get().getPesel();
                String phoneNumber = administratorRepository.findById(loggedUserId).get().getPhoneNumber();

                model.addAttribute("name", name);
                model.addAttribute("surname", surname);
                model.addAttribute("pesel", pesel);
                model.addAttribute("phoneNumber", phoneNumber);
                return "account/mydata";
            } else {
                return "error";
            }

        }
    }


    @GetMapping("/myaccount/calendarofvisits")
    public String calendarOfVisits() {
        return "account/account";
    }


}
