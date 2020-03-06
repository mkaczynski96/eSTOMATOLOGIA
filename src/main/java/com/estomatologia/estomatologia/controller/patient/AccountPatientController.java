package com.estomatologia.estomatologia.controller.patient;

import com.estomatologia.estomatologia.model.Prescription;
import com.estomatologia.estomatologia.model.ProposedVisit;
import com.estomatologia.estomatologia.model.Visit;
import com.estomatologia.estomatologia.repository.*;
import com.estomatologia.estomatologia.service.AuthenticationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.security.RolesAllowed;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AccountPatientController {

    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final VisitRepository visitRepository;
    private final DoctorSpecializationRepository doctorSpecializationRepository;
    private final PrescriptionRepository prescriptionRepository;
    private final SpecializationRepository specializationRepository;
    private final ProposedVisitRepository proposedVisitRepository;

    private final AuthenticationService authenticationService;

    public AccountPatientController(PatientRepository patientRepository, DoctorRepository doctorRepository,
                                    VisitRepository visitRepository, DoctorSpecializationRepository doctorSpecializationRepository,
                                    PrescriptionRepository prescriptionRepository, SpecializationRepository specializationRepository,
                                    ProposedVisitRepository proposedVisitRepository, AuthenticationService authenticationService) {
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
        this.visitRepository = visitRepository;
        this.doctorSpecializationRepository = doctorSpecializationRepository;
        this.prescriptionRepository = prescriptionRepository;
        this.specializationRepository = specializationRepository;
        this.proposedVisitRepository = proposedVisitRepository;
        this.authenticationService = authenticationService;
    }


    @RolesAllowed("PATIENT")
    @GetMapping("/myaccount/historyofvisits")
    public String historyOfVisits(Model model) {

        if (authenticationService.validUser("patient")) {
            model.addAttribute("visits", visitRepository.findAllByPatientId(authenticationService.getUserId()));
            model.addAttribute("proposedvisits", proposedVisitRepository.findAllByPatientId(authenticationService.getUserId()));
            return "account/patient/historyofvisits";
        } else {
            return "error";
        }
    }

    @RolesAllowed("PATIENT")
    @GetMapping("/myaccount/historyofvisits/deleteproposition")
    public String deleteProposition(@RequestParam Long id) {

        if (authenticationService.validUser("patient")) {
            if (proposedVisitRepository.findById(id).isPresent()) {
                proposedVisitRepository.deleteById(id);
                return "success";
            }
            return "error";
        } else {
            return "error";
        }
    }


    @RolesAllowed("PATIENT")
    @GetMapping("/myaccount/historyofvisits/deletevisit")
    public String deleteVisit(@RequestParam Long id) {

        if (authenticationService.validUser("patient")) {
            if (visitRepository.findById(id).isPresent()) {
                visitRepository.deleteById(id);
                return "success";
            }
            return "error";
        } else {
            return "error";
        }
    }

    @RolesAllowed("PATIENT")
    @GetMapping("/myaccount/makevisit")
    public String makeVisit(Model model) {

        if (authenticationService.validUser("patient")) {
            model.addAttribute("patient", authenticationService.getUserId());
            model.addAttribute("proposedvisit", new ProposedVisit());
            model.addAttribute("actuallydate", LocalDate.now());
            model.addAttribute("doctors", doctorRepository.findAll());
            return "account/patient/makevisit";
        } else {
            return "error";
        }
    }

    @RolesAllowed("PATIENT")
    @PostMapping("/myaccount/makevisit")
    public String makevisit(@ModelAttribute ProposedVisit proposedVisit, Model model) {

        if (authenticationService.validUser("patient")) {
            proposedVisit.setPatient(patientRepository.findById(authenticationService.getUserId()).orElse(null));
            if (proposedVisitRepository.findAllByPatientId(authenticationService.getUserId()).size() >= 5
                    || visitRepository.findAllByPatientIdAndFinished(authenticationService.getUserId(), false).size() >= 5) {
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


    @RolesAllowed("PATIENT")
    @GetMapping("/myaccount/availabledoctors")
    public String availabledoctors(Model model) {

        if (authenticationService.validUser("patient")) {
            model.addAttribute("specializations", specializationRepository.findAll());
            model.addAttribute("doctorspecializations", doctorSpecializationRepository.findAll());
            model.addAttribute("doctors", doctorRepository.findAll());
            return "account/patient/availabledoctors";
        } else {
            return "error";
        }
    }

    @RolesAllowed("PATIENT")
    @GetMapping("/myaccount/myprescriptions")
    public String myRecipes(Model model) {

        if (authenticationService.validUser("patient")) {
            List<Visit> visit = visitRepository.findAllByPatientIdAndFinished(authenticationService.getUserId(), true);
            List<Prescription> prescriptions = new ArrayList<>();
            visit.forEach(e -> prescriptions.add(prescriptionRepository.findByVisitId(e.getId())));
            model.addAttribute("recipes", prescriptions);
            return "account/patient/myprescriptions";
        } else {
            return "error";
        }
    }

}
