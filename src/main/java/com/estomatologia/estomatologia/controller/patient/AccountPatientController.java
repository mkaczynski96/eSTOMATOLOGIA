package com.estomatologia.estomatologia.controller.patient;

import com.estomatologia.estomatologia.model.Prescription;
import com.estomatologia.estomatologia.model.ProposedVisit;
import com.estomatologia.estomatologia.model.User;
import com.estomatologia.estomatologia.model.Visit;
import com.estomatologia.estomatologia.repository.*;
import com.estomatologia.estomatologia.service.security.AuthorizationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.security.RolesAllowed;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AccountPatientController {

    private final AuthorizationService authorizationService;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final VisitRepository visitRepository;
    private final DoctorSpecializationRepository doctorSpecializationRepository;
    private final PrescriptionRepository prescriptionRepository;
    private final SpecializationRepository specializationRepository;
    private final ProposedVisitRepository proposedVisitRepository;

    public AccountPatientController(AuthorizationService authorizationService, PatientRepository patientRepository, DoctorRepository doctorRepository, VisitRepository visitRepository, DoctorSpecializationRepository doctorSpecializationRepository, PrescriptionRepository prescriptionRepository, SpecializationRepository specializationRepository, ProposedVisitRepository proposedVisitRepository) {
        this.authorizationService = authorizationService;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
        this.visitRepository = visitRepository;
        this.doctorSpecializationRepository = doctorSpecializationRepository;
        this.prescriptionRepository = prescriptionRepository;
        this.specializationRepository = specializationRepository;
        this.proposedVisitRepository = proposedVisitRepository;
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
                model.addAttribute("proposedvisits", proposedVisitRepository.findAllByPatientId(loggedUserId));
                return "account/patient/historyofvisits";
            } else {
                return "error";
            }
        }
    }

    @RolesAllowed("PATIENT")
    @GetMapping("/myaccount/makevisit")
    public String makeVisit(Model model) {
        User loggedUser = authorizationService.getLoggedUser();

        if (loggedUser == null) {
            return "error";
        } else {
            Long loggedUserId = loggedUser.getId();
            if (patientRepository.findById(loggedUserId).isPresent()) {
                model.addAttribute("patient", loggedUser);
                model.addAttribute("proposedvisit", new ProposedVisit());
                model.addAttribute("actuallydate", LocalDate.now());
                model.addAttribute("doctors", doctorRepository.findAll());
                return "account/patient/makevisit";
            } else {
                return "error";
            }
        }
    }

    @RolesAllowed("RECEPTION")
    @PostMapping("/myaccount/makevisit")
    public String makevisit(@ModelAttribute ProposedVisit proposedVisit, Model model) {
        User loggedUser = authorizationService.getLoggedUser();

        if (loggedUser == null) {
            return "error";
        } else {
            Long loggedUserId = loggedUser.getId();
            if (patientRepository.findById(loggedUserId).isPresent()) {
                proposedVisit.setPatient(patientRepository.findById(loggedUserId).orElse(null));
                if (proposedVisitRepository.findAllByPatientId(loggedUserId).size() >= 5) {
                    model.addAttribute("failed", "proposedvisit");
                    return "failed";
                } else {
                    proposedVisitRepository.save(proposedVisit);
                    model.addAttribute("success", "proposedvisit");
                    return "success";
                }
            } else {
                return "error";
            }
        }
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

                model.addAttribute("specializations", specializationRepository.findAll());
                model.addAttribute("doctorspecializations", doctorSpecializationRepository.findAll());
                model.addAttribute("doctors", doctorRepository.findAll());
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
                List<Visit> visit = visitRepository.findAllByPatientId(loggedUserId);
                List<Prescription> prescriptions = new ArrayList<>();
                visit.forEach(e -> prescriptions.add(prescriptionRepository.findByVisitId(e.getId())));
                model.addAttribute("recipes", prescriptions);
                return "account/patient/myrecipes";
            } else {
                return "error";
            }

        }
    }

}
