package com.estomatologia.estomatologia.controller.patient;

import com.estomatologia.estomatologia.model.Doctor;
import com.estomatologia.estomatologia.model.User;
import com.estomatologia.estomatologia.repository.DoctorRepository;
import com.estomatologia.estomatologia.repository.DoctorSpecializationRepository;
import com.estomatologia.estomatologia.repository.PatientRepository;
import com.estomatologia.estomatologia.repository.VisitRepository;
import com.estomatologia.estomatologia.service.security.AuthorizationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.security.RolesAllowed;

@Controller
public class AccountPatientController {

    private final AuthorizationService authorizationService;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final VisitRepository visitRepository;
    private final DoctorSpecializationRepository doctorSpecializationRepository;

    public AccountPatientController(AuthorizationService authorizationService, PatientRepository patientRepository, DoctorRepository doctorRepository, VisitRepository visitRepository, DoctorSpecializationRepository doctorSpecializationRepository) {
        this.authorizationService = authorizationService;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
        this.visitRepository = visitRepository;
        this.doctorSpecializationRepository = doctorSpecializationRepository;
    }


    @RolesAllowed("PATIENT")
    @GetMapping("/myaccount/historyofvisits")
    public String historyOfVisits(Model model) {
        User loggedUser = authorizationService.getLoggedUser();

        if (loggedUser == null) {
            return "error";
        } else {
            Long loggedUserId = loggedUser.getId();
            if (patientRepository.findById(loggedUserId).isPresent()) {
                model.addAttribute("visits", visitRepository.findAllByPatientId(loggedUserId));
                return "account/patient/historyofvisits";
            } else {
                return "error";
            }
        }
    }

    @RolesAllowed("PATIENT")
    @GetMapping("/myaccount/makevisit")
    public String makeVisit() {
        return "account/account";
    }

    @RolesAllowed("PATIENT")
    @GetMapping("/myaccount/availabledoctors")
    public String availabledoctors(Model model) {
        User loggedUser = authorizationService.getLoggedUser();

        if (loggedUser == null) {
            return "error";
        } else {
            Long loggedUserId = loggedUser.getId();
            if (patientRepository.findById(loggedUserId).isPresent()) {

                model.addAttribute("doctors", doctorRepository.findAll());

                Iterable<Doctor> doctors = doctorRepository.findAll();
                doctors.forEach(e -> model.addAttribute("specializations", doctorSpecializationRepository.findAllByDoctorId(e.getId())));

                return "account/patient/availabledoctors";
            } else {
                return "error";
            }

        }
    }

    @RolesAllowed("PATIENT")
    @GetMapping("/myaccount/myrecipes")
    public String myRecipes(Model model) {

        User loggedUser = authorizationService.getLoggedUser();

        if (loggedUser == null) {
            return "error";
        } else {
            Long loggedUserId = loggedUser.getId();
            if (patientRepository.findById(loggedUserId).isPresent()) {

                model.addAttribute("recipes:", patientRepository.findById(loggedUserId).get().getVisits());
                return "account/patient/myrecipes";
            } else {
                return "error";
            }

        }
    }

}
