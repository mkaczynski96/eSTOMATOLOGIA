package com.estomatologia.estomatologia.controller.doctor;

import com.estomatologia.estomatologia.model.User;
import com.estomatologia.estomatologia.repository.DoctorRepository;
import com.estomatologia.estomatologia.repository.MedicamentRepository;
import com.estomatologia.estomatologia.repository.PatientRepository;
import com.estomatologia.estomatologia.repository.VisitRepository;
import com.estomatologia.estomatologia.service.AuthorizationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.security.RolesAllowed;

@Controller
public class AccountDoctorController {

    private final DoctorRepository doctorRepository;
    private final MedicamentRepository medicamentRepository;
    private final PatientRepository patientRepository;
    private final VisitRepository visitRepository;

    private final AuthorizationService authorizationService;

    public AccountDoctorController(DoctorRepository doctorRepository, MedicamentRepository medicamentRepository, PatientRepository patientRepository, VisitRepository visitRepository, AuthorizationService authorizationService) {
        this.doctorRepository = doctorRepository;
        this.medicamentRepository = medicamentRepository;
        this.patientRepository = patientRepository;
        this.visitRepository = visitRepository;
        this.authorizationService = authorizationService;
    }


    @RolesAllowed("DOCTOR")
    @GetMapping("/myaccount/mypatients")
    public String myPatients(Model model) {
        User loggedUser = authorizationService.getLoggedUser();

        if (loggedUser == null) {
            return "error";
        } else {
            Long loggedUserId = loggedUser.getId();
            if (doctorRepository.findById(loggedUserId).isPresent()) {
                {
                    model.addAttribute("patients", visitRepository.findVisitByDoctorId(loggedUserId));
                    return "account/doctor/mypatients";
                }
            } else {
                return "error";
            }
        }
    }

    @RolesAllowed("DOCTOR")
    @GetMapping("/myaccount/availablemedicaments")
    public String availableMedicaments(Model model) {

        User loggedUser = authorizationService.getLoggedUser();

        if (loggedUser == null) {
            return "error";
        } else {
            Long loggedUserId = loggedUser.getId();
            if (doctorRepository.findById(loggedUserId).isPresent()) {
                model.addAttribute("medicaments", medicamentRepository.findAll());
                return "account/doctor/availablemedicaments";
            } else {
                return "error";
            }
        }

    }

}
