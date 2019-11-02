package com.estomatologia.estomatologia.controller.administrator;

import com.estomatologia.estomatologia.model.*;
import com.estomatologia.estomatologia.repository.*;
import com.estomatologia.estomatologia.service.security.AuthorizationService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.security.RolesAllowed;

@Controller
public class AccountAdministratorController {


    private final AuthorizationService authorizationService;
    private final AdministratorRepository administratorRepository;
    private final DoctorRepository doctorRepository;
    private final DoctorSpecializationRepository doctorSpecializationRepository;
    private final PatientRepository patientRepository;
    private final VisitRepository visitRepository;
    private final ReceptionistRepository receptionistRepository;
    private final UserRepository userRepository;
    private final SpecializationRepository specializationRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthorityRepository authorityRepository;

    public AccountAdministratorController(AuthorizationService authorizationService,
                                          AdministratorRepository administratorRepository, DoctorRepository doctorRepository, DoctorSpecializationRepository doctorSpecializationRepository, PatientRepository patientRepository, VisitRepository visitRepository, ReceptionistRepository receptionistRepository, UserRepository userRepository, SpecializationRepository specializationRepository, PasswordEncoder passwordEncoder, AuthorityRepository authorityRepository) {
        this.authorizationService = authorizationService;
        this.administratorRepository = administratorRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.doctorSpecializationRepository = doctorSpecializationRepository;
        this.visitRepository = visitRepository;
        this.receptionistRepository = receptionistRepository;
        this.userRepository = userRepository;
        this.specializationRepository = specializationRepository;
        this.passwordEncoder = passwordEncoder;
        this.authorityRepository = authorityRepository;
    }

    @RolesAllowed("ADMIN")
    @GetMapping("/myaccount/managedoctor")
    public String manageDoctor(Model model) {
        User loggedUser = authorizationService.getLoggedUser();

        if (loggedUser == null) {
            return "error";
        } else {
            Long loggedUserId = loggedUser.getId();
            if (administratorRepository.findById(loggedUserId).isPresent()) {

                model.addAttribute("doctors", doctorRepository.findAll());
                model.addAttribute("specializations", doctorSpecializationRepository.findAll());
                return "account/admin/managedoctors";
            } else {
                return "error";
            }
        }
    }


    @RolesAllowed("ADMIN")
    @GetMapping("/myaccount/managedoctor/add")
    public String addDoctor(Model model) {
        User loggedUser = authorizationService.getLoggedUser();

        if (loggedUser == null) {
            return "error";
        } else {
            Long loggedUserId = loggedUser.getId();
            if (administratorRepository.findById(loggedUserId).isPresent()) {
                model.addAttribute("doctorspecializations", new DoctorSpecialization());
                model.addAttribute("specializations", specializationRepository.findAll());
                return "account/admin/adddoctor";
            } else {
                return "error";
            }
        }
    }


    @RolesAllowed("ADMIN")
    @PostMapping("/myaccount/managedoctor/add")
    public String addDoctor(@ModelAttribute DoctorSpecialization doctorSpecialization) {
        User loggedUser = authorizationService.getLoggedUser();

        if (loggedUser == null) {
            return "error";
        } else {
            Long loggedUserId = loggedUser.getId();
            if (administratorRepository.findById(loggedUserId).isPresent()) {

                Doctor newDoctor = new Doctor();
                newDoctor.setName(doctorSpecialization.getDoctor().getName());
                newDoctor.setSurname(doctorSpecialization.getDoctor().getSurname());
                newDoctor.setAddress(doctorSpecialization.getDoctor().getAddress());
                newDoctor.setPhoneNumber(doctorSpecialization.getDoctor().getPhoneNumber());
                newDoctor.setPesel(doctorSpecialization.getDoctor().getPesel());

                User newUserDoctor = new User();
                newUserDoctor.setUsername(doctorSpecialization.getDoctor().getUserDoctor().getUsername());
                newUserDoctor.setPassword(passwordEncoder.encode(doctorSpecialization.getDoctor().getUserDoctor().getPassword()));
                newUserDoctor.setEnabled(true);
                newUserDoctor.setDoctor(newDoctor);
                newDoctor.setUserDoctor(newUserDoctor);

                Authorities doctorAuthorities = new Authorities();
                doctorAuthorities.setUsername(newUserDoctor.getUsername());
                doctorAuthorities.setAuthority("ROLE_DOCTOR");

                authorityRepository.save(doctorAuthorities);
                userRepository.save(newUserDoctor);

                DoctorSpecialization doctorSpecialization1 = new DoctorSpecialization();
                doctorSpecialization1.setDoctor(newDoctor);
                doctorSpecialization1.setSpecialization(doctorSpecialization.getSpecialization());
                doctorSpecialization1.setLicense(doctorSpecialization.getLicense());
                doctorSpecialization1.setId(new DoctorSpecializationKey(newDoctor.getId(), doctorSpecialization.getSpecialization().getId()));

                doctorSpecializationRepository.save(doctorSpecialization1);

                return "success";
            } else {
                return "error";
            }
        }
    }


    @RolesAllowed("ADMIN")
    @GetMapping("/myaccount/managedoctor/delete")
    public String deleteDoctor(@RequestParam Long id) {
        User loggedUser = authorizationService.getLoggedUser();

        if (loggedUser == null) {
            return "error";
        } else {
            Long loggedUserId = loggedUser.getId();
            if (administratorRepository.findById(loggedUserId).isPresent()) {
                if (doctorRepository.findById(id).isPresent()) {
                    authorityRepository.deleteAllByUsername(doctorRepository.findById(id).get().getName());
                    doctorSpecializationRepository.deleteAllByDoctorId(id);
                    userRepository.findById(id).get().setEnabled(false);
                    String name = "X - " + doctorRepository.findAllByUserDoctorId(id).getName();
                    doctorRepository.findAllByUserDoctorId(id).setName(name);
                    doctorRepository.findAllByUserDoctorId(id).setAddress(null);
                    doctorRepository.findAllByUserDoctorId(id).setPesel(null);
                    doctorRepository.findAllByUserDoctorId(id).setPhoneNumber(null);
                    doctorRepository.findAllByUserDoctorId(id);
                }
                return "success";
            } else {
                return "error";
            }
        }
    }

    @RolesAllowed("ADMIN")
    @GetMapping("/myaccount/managedoctor/doctors")
    public String doctorDetails(@RequestParam Long id, Model model) {
        User loggedUser = authorizationService.getLoggedUser();

        if (loggedUser == null) {
            return "error";
        } else {
            Long loggedUserId = loggedUser.getId();
            if (administratorRepository.findById(loggedUserId).isPresent()) {

                model.addAttribute("doctor", doctorRepository.findById(id));
                model.addAttribute("specializations", doctorSpecializationRepository.findAllByDoctorId(id));
                return "account/admin/doctors";
            } else {
                return "error";
            }
        }
    }

    @RolesAllowed("ADMIN")
    @GetMapping("/myaccount/managepatient")
    public String managePatient(Model model) {
        User loggedUser = authorizationService.getLoggedUser();

        if (loggedUser == null) {
            return "error";
        } else {
            Long loggedUserId = loggedUser.getId();
            if (administratorRepository.findById(loggedUserId).isPresent()) {

                model.addAttribute("patients", patientRepository.findAll());
                return "account/admin/managepatients";
            } else {
                return "error";
            }
        }
    }


    @RolesAllowed("ADMIN")
    @GetMapping("/myaccount/managepatient/add")
    public String addPatient(Model model) {
        User loggedUser = authorizationService.getLoggedUser();

        if (loggedUser == null) {
            return "error";
        } else {
            Long loggedUserId = loggedUser.getId();
            if (administratorRepository.findById(loggedUserId).isPresent()) {
                model.addAttribute("patients", new Patient());
                return "account/admin/addpatient";
            } else {
                return "error";
            }
        }
    }


    @RolesAllowed("ADMIN")
    @PostMapping("/myaccount/managepatient/add")
    public String addPatient(@ModelAttribute Patient patient) {
        User loggedUser = authorizationService.getLoggedUser();

        if (loggedUser == null) {
            return "error";
        } else {
            Long loggedUserId = loggedUser.getId();
            if (administratorRepository.findById(loggedUserId).isPresent()) {

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
        }
    }


    @RolesAllowed("ADMIN")
    @GetMapping("/myaccount/managepatient/patients")
    public String patientDetails(@RequestParam Long id, Model model) {
        User loggedUser = authorizationService.getLoggedUser();

        if (loggedUser == null) {
            return "error";
        } else {
            Long loggedUserId = loggedUser.getId();
            if (administratorRepository.findById(loggedUserId).isPresent()) {

                model.addAttribute("patients", patientRepository.findById(id));
                model.addAttribute("visits", visitRepository.findAllByPatientId(id));
                return "account/admin/patients";
            } else {
                return "error";
            }
        }
    }

    @RolesAllowed("ADMIN")
    @GetMapping("/myaccount/managereception")
    public String manageReceptionist(Model model) {
        User loggedUser = authorizationService.getLoggedUser();

        if (loggedUser == null) {
            return "error";
        } else {
            Long loggedUserId = loggedUser.getId();
            if (administratorRepository.findById(loggedUserId).isPresent()) {

                model.addAttribute("receptionists", receptionistRepository.findAll());
                return "account/admin/managereceptionist";
            } else {
                return "error";
            }
        }
    }


    @RolesAllowed("ADMIN")
    @GetMapping("/myaccount/managereception/add")
    public String addReceptionist(Model model) {
        User loggedUser = authorizationService.getLoggedUser();

        if (loggedUser == null) {
            return "error";
        } else {
            Long loggedUserId = loggedUser.getId();
            if (administratorRepository.findById(loggedUserId).isPresent()) {
                model.addAttribute("receptionists", new Receptionist());
                return "account/admin/addreceptionist";
            } else {
                return "error";
            }
        }
    }

    @RolesAllowed("ADMIN")
    @PostMapping("/myaccount/managereception/add")
    public String addReceptionist(@ModelAttribute Receptionist receptionist) {
        User loggedUser = authorizationService.getLoggedUser();

        if (loggedUser == null) {
            return "error";
        } else {
            Long loggedUserId = loggedUser.getId();
            if (administratorRepository.findById(loggedUserId).isPresent()) {


                Receptionist receptionist1 = new Receptionist();
                receptionist1.setName(receptionist.getName());
                receptionist1.setSurname(receptionist.getSurname());
                receptionist1.setAddress(receptionist.getAddress());
                receptionist1.setPesel(receptionist.getPesel());
                receptionist1.setPhoneNumber(receptionist.getPhoneNumber());

                User userReceptionist = new User();
                userReceptionist.setUsername(receptionist.getUserReceptionist().getUsername());
                userReceptionist.setPassword(passwordEncoder.encode(receptionist.getUserReceptionist().getPassword()));
                userReceptionist.setEnabled(true);
                userReceptionist.setReceptionist(receptionist1);

                receptionist1.setUserReceptionist(userReceptionist);
                userRepository.save(userReceptionist);

                Authorities authorities = new Authorities();
                authorities.setUsername(receptionist.getUserReceptionist().getUsername());
                authorities.setAuthority("ROLE_RECEPTION");

                authorityRepository.save(authorities);

                return "success";
            } else {
                return "error";
            }
        }
    }



    @RolesAllowed("ADMIN")
    @GetMapping("/myaccount/managereception/receptionists")
    public String receptionistDetails(@RequestParam Long id, Model model) {
        User loggedUser = authorizationService.getLoggedUser();

        if (loggedUser == null) {
            return "error";
        } else {
            Long loggedUserId = loggedUser.getId();
            if (administratorRepository.findById(loggedUserId).isPresent()) {

                model.addAttribute("receptionists", receptionistRepository.findById(id));
                return "account/admin/receptionists";
            } else {
                return "error";
            }
        }
    }

}
