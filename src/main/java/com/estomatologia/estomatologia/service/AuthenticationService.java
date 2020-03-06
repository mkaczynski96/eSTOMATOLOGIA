package com.estomatologia.estomatologia.service;

import com.estomatologia.estomatologia.model.User;
import com.estomatologia.estomatologia.repository.AdministratorRepository;
import com.estomatologia.estomatologia.repository.DoctorRepository;
import com.estomatologia.estomatologia.repository.PatientRepository;
import com.estomatologia.estomatologia.repository.ReceptionistRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final AuthorizationService authorizationService;
    private final DoctorRepository doctorRepository;
    private final AdministratorRepository administratorRepository;
    private final PatientRepository patientRepository;
    private final ReceptionistRepository receptionistRepository;

    public AuthenticationService(AuthorizationService authorizationService, DoctorRepository doctorRepository, AdministratorRepository administratorRepository, PatientRepository patientRepository, ReceptionistRepository receptionistRepository) {
        this.authorizationService = authorizationService;
        this.doctorRepository = doctorRepository;
        this.administratorRepository = administratorRepository;
        this.patientRepository = patientRepository;
        this.receptionistRepository = receptionistRepository;
    }

    public long getUserId() {
        User loggedUser = authorizationService.getLoggedUser();
        return loggedUser.getId();
    }

    public boolean validUser(String nameRepository) {

        User loggedUser = authorizationService.getLoggedUser();

        if (loggedUser == null) {
            return false;
        } else {
            Long loggedUserId = loggedUser.getId();

            if (nameRepository.equals("doctor") && doctorRepository.findById(loggedUserId).isPresent())
                return true;
            else if (nameRepository.equals("admin") && administratorRepository.findById(loggedUserId).isPresent())
                return true;
            else if (nameRepository.equals("patient") && patientRepository.findById(loggedUserId).isPresent())
                return true;
            else
                return nameRepository.equals("receptionist") && receptionistRepository.findById(loggedUserId).isPresent();
        }

    }
}
