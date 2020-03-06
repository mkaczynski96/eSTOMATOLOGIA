package com.estomatologia.estomatologia.controller.administrator;

import com.estomatologia.estomatologia.model.*;
import com.estomatologia.estomatologia.repository.*;
import com.estomatologia.estomatologia.service.AuthenticationService;
import com.estomatologia.estomatologia.service.AuthorizationService;
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
    private final AuthenticationService authenticationService;

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
                                          AuthenticationService authenticationService, AdministratorRepository administratorRepository, DoctorRepository doctorRepository, DoctorSpecializationRepository doctorSpecializationRepository, PatientRepository patientRepository, VisitRepository visitRepository, ReceptionistRepository receptionistRepository, UserRepository userRepository, SpecializationRepository specializationRepository, PasswordEncoder passwordEncoder, AuthorityRepository authorityRepository) {
        this.authorizationService = authorizationService;
        this.authenticationService = authenticationService;
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
    @GetMapping("/myaccount/manageadmin")
    public String manageAdmin(Model model) {

        if (authenticationService.validUser("admin")) {
            model.addAttribute("admins", administratorRepository.findAll());
            return "account/admin/manageadministration";
        } else {
            return "error";
        }
    }

    @RolesAllowed("ADMIN")
    @GetMapping("/myaccount/manageadmin/add")
    public String addAdmin(Model model) {

        if (authenticationService.validUser("admin")) {
            model.addAttribute("admin", new Administrator());
            return "account/admin/addadmin";
        } else {
            return "error";
        }
    }


    @RolesAllowed("ADMIN")
    @PostMapping("/myaccount/manageadmin/add")
    public String addAdmin(@ModelAttribute Administrator administrator) {

        if (authenticationService.validUser("admin")) {
            Administrator newDoctor = new Administrator();
            newDoctor.setName(administrator.getName());
            newDoctor.setSurname(administrator.getSurname());
            newDoctor.setAddress(administrator.getAddress());
            newDoctor.setPhoneNumber(administrator.getPhoneNumber());
            newDoctor.setPesel(administrator.getPesel());

            User newUserDoctor = new User();
            newUserDoctor.setUsername(administrator.getUserAdministrator().getUsername());
            newUserDoctor.setPassword(passwordEncoder.encode(administrator.getUserAdministrator().getPassword()));
            newUserDoctor.setEnabled(true);
            newUserDoctor.setAdministrator(newDoctor);
            newDoctor.setUserAdministrator(newUserDoctor);

            Authorities doctorAuthorities = new Authorities();
            doctorAuthorities.setUsername(newUserDoctor.getUsername());
            doctorAuthorities.setAuthority("ROLE_ADMIN");

            authorityRepository.save(doctorAuthorities);
            userRepository.save(newUserDoctor);

            return "success";
        } else {
            return "error";
        }
    }


    @RolesAllowed("ADMIN")
    @GetMapping("/myaccount/manageadmin/edit")
    public String editAdmin(@RequestParam Long id, Model model) {
        if (authenticationService.validUser("admin")) {
            if (administratorRepository.findById(id).isPresent()) {
                Administrator doctor = administratorRepository.findById(id).orElse(null);
                model.addAttribute("admin", doctor);

                return "account/admin/editadmin";
            }
            return "error";
        } else {
            return "error";
        }
    }


    @RolesAllowed("ADMIN")
    @PostMapping("/myaccount/manageadmin/edit")
    public String editAdmin(@ModelAttribute Administrator doctor, @RequestParam Long id) {

        if (authenticationService.validUser("admin")) {
            Administrator editedDoctor = administratorRepository.findById(doctor.getId()).orElse(null);
            if (editedDoctor != null) {
                editedDoctor.setId(id);
                editedDoctor.setName(doctor.getName());
                editedDoctor.setSurname(doctor.getSurname());
                editedDoctor.setAddress(doctor.getAddress());
                editedDoctor.setPesel(doctor.getPesel());
                editedDoctor.setPhoneNumber(doctor.getPhoneNumber());
                administratorRepository.save(editedDoctor);

                return "success";
            }
            return "error";
        } else {
            return "error";
        }
    }


    @RolesAllowed("ADMIN")
    @GetMapping("/myaccount/manageadmin/delete")
    public String deleteAdmin(@RequestParam Long id) {

        if (authenticationService.validUser("admin")) {
            if (administratorRepository.findById(id).isPresent()) {
                authorityRepository.deleteAllByUsername(administratorRepository.findById(id).get().getName());
                administratorRepository.deleteById(id);
                return "success";
            }
            return "error";
        } else {
            return "error";
        }
    }

    @RolesAllowed("ADMIN")
    @GetMapping("/myaccount/manageadmin/admins")
    public String adminDetails(@RequestParam Long id, Model model) {

        if (authenticationService.validUser("admin")) {
            model.addAttribute("admin", administratorRepository.findById(id));
            return "account/admin/admins";
        } else {
            return "error";
        }
    }


    @RolesAllowed("ADMIN")
    @GetMapping("/myaccount/managedoctor")
    public String manageDoctor(Model model) {

        if (authenticationService.validUser("admin")) {
            model.addAttribute("doctors", doctorRepository.findAll());
            model.addAttribute("specializations", doctorSpecializationRepository.findAll());
            return "account/admin/managedoctors";
        } else {
            return "error";
        }
    }


    @RolesAllowed("ADMIN")
    @GetMapping("/myaccount/managedoctor/add")
    public String addDoctor(Model model) {

        if (authenticationService.validUser("admin")) {
            model.addAttribute("doctorspecializations", new DoctorSpecialization());
            model.addAttribute("specializations", specializationRepository.findAll());
            return "account/admin/adddoctor";
        } else {
            return "error";
        }
    }


    @RolesAllowed("ADMIN")
    @PostMapping("/myaccount/managedoctor/add")
    public String addDoctor(@ModelAttribute DoctorSpecialization doctorSpecialization) {

        if (authenticationService.validUser("admin")) {
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


    @RolesAllowed("ADMIN")
    @GetMapping("/myaccount/managedoctor/edit")
    public String editDoctor(@RequestParam Long id, Model model) {

        if (authenticationService.validUser("admin")) {
            if (doctorRepository.findById(id).isPresent()) {
                Doctor doctor = doctorRepository.findById(id).orElse(null);
                model.addAttribute("doctor", doctor);

                return "account/admin/editdoctor";
            }
            return "error";
        } else {
            return "error";
        }
    }


    @RolesAllowed("ADMIN")
    @PostMapping("/myaccount/managedoctor/edit")
    public String editDoctor(@ModelAttribute Doctor doctor, @RequestParam Long id) {

        if (authenticationService.validUser("admin")) {
            Doctor editedDoctor = doctorRepository.findById(doctor.getId()).orElse(null);
            if (editedDoctor != null) {
                editedDoctor.setId(id);
                editedDoctor.setName(doctor.getName());
                editedDoctor.setSurname(doctor.getSurname());
                editedDoctor.setAddress(doctor.getAddress());
                editedDoctor.setPesel(doctor.getPesel());
                editedDoctor.setPhoneNumber(doctor.getPhoneNumber());
                doctorRepository.save(editedDoctor);

                return "success";
            }
            return "error";
        } else {
            return "error";
        }
    }


    @RolesAllowed("ADMIN")
    @GetMapping("/myaccount/managedoctor/delete")
    public String deleteDoctor(@RequestParam Long id) {

        if (authenticationService.validUser("admin")) {
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
                return "success";
            }
            return "error";
        } else {
            return "error";
        }
    }

    @RolesAllowed("ADMIN")
    @GetMapping("/myaccount/managedoctor/doctors")
    public String doctorDetails(@RequestParam Long id, Model model) {

        if (authenticationService.validUser("admin")) {
            model.addAttribute("doctor", doctorRepository.findById(id));
            model.addAttribute("specializations", doctorSpecializationRepository.findAllByDoctorId(id));
            return "account/admin/doctors";
        } else {
            return "error";
        }
    }


    @RolesAllowed("ADMIN")
    @GetMapping("/myaccount/managedoctor/addspecialization")
    public String addChronicDiseases(@RequestParam Long id, Model model) {

        if (authenticationService.validUser("admin")) {
            if (doctorRepository.findById(id).isPresent()) {
                Doctor p1 = doctorRepository.findById(id).orElse(null);
                model.addAttribute("doctor", p1);
                model.addAttribute("doctorspecializations", new DoctorSpecialization());
                model.addAttribute("specializations", specializationRepository.findAll());
                return "account/admin/addspecialization";
            }
            return "error";
        } else {
            return "error";
        }
    }


    @RolesAllowed("ADMIN")
    @PostMapping("/myaccount/managedoctor/addspecialization")
    public String addSpecialization(@RequestParam Long doctorid, @ModelAttribute DoctorSpecialization doctorSpecialization) {

        if (authenticationService.validUser("admin")) {
            Doctor d1 = doctorRepository.findById(doctorid).orElse(null);

            DoctorSpecialization doctorSpecialization1 = new DoctorSpecialization();
            doctorSpecialization1.setDoctor(d1);
            doctorSpecialization1.setSpecialization(doctorSpecialization.getSpecialization());
            doctorSpecialization1.setLicense(doctorSpecialization.getLicense());
            doctorSpecialization1.setId(new DoctorSpecializationKey(d1.getId(), doctorSpecialization.getSpecialization().getId()));

            doctorSpecializationRepository.save(doctorSpecialization1);
            return "success";
        } else {
            return "error";
        }
    }


    @RolesAllowed("ADMIN")
    @GetMapping("/myaccount/managedoctor/deletespecialization")
    public String deleteSpecialization(@RequestParam Long id, Model model) {

        if (authenticationService.validUser("admin")) {
            if (doctorRepository.findById(id).isPresent()) {
                Doctor p1 = doctorRepository.findById(id).orElse(null);
                model.addAttribute("doctor", p1);
                model.addAttribute("doctorspecializations", doctorSpecializationRepository.findAllByDoctorId(p1.getId()));
                model.addAttribute("specializations", specializationRepository.findAll());
                return "account/admin/deletespecialization";
            }
            return "error";
        } else {
            return "error";
        }
    }


    @RolesAllowed("ADMIN")
    @PostMapping("/myaccount/managedoctor/deletespecialization")
    public String deleteSpecialization(@RequestParam Long doctorid, @RequestParam("specid") Long specid) {

        if (authenticationService.validUser("admin")) {
            doctorSpecializationRepository.deleteByDoctorIdAndSpecializationId(doctorid, specid);

            return "success";
        } else {
            return "error";
        }
    }

    @RolesAllowed("ADMIN")
    @GetMapping("/myaccount/managepatient")
    public String managePatient(Model model) {

        if (authenticationService.validUser("admin")) {
            model.addAttribute("patients", patientRepository.findAll());
            return "account/admin/managepatients";
        } else {
            return "error";
        }
    }

    @RolesAllowed("ADMIN")
    @GetMapping("/myaccount/managepatient/add")
    public String addPatient(Model model) {

        if (authenticationService.validUser("admin")) {
            model.addAttribute("patients", new Patient());
            return "account/admin/addpatient";
        } else {
            return "error";
        }
    }


    @RolesAllowed("ADMIN")
    @PostMapping("/myaccount/managepatient/add")
    public String addPatient(@ModelAttribute Patient patient) {

        if (authenticationService.validUser("admin")) {
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


    @RolesAllowed("ADMIN")
    @GetMapping("/myaccount/managepatient/patients")
    public String patientDetails(@RequestParam Long id, Model model) {

        if (authenticationService.validUser("admin")) {
            model.addAttribute("patients", patientRepository.findById(id));
            model.addAttribute("visits", visitRepository.findAllByPatientId(id));
            return "account/admin/patients";
        } else {
            return "error";
        }
    }


    @RolesAllowed("ADMIN")
    @GetMapping("/myaccount/managepatient/edit")
    public String editPatient(@RequestParam Long id, Model model) {

        if (authenticationService.validUser("admin")) {
            if (patientRepository.findById(id).isPresent()) {
                Patient patient = patientRepository.findById(id).orElse(null);
                model.addAttribute("patient", patient);

                return "account/admin/editpatient";
            }
            return "error";
        } else {
            return "error";
        }
    }


    @RolesAllowed("ADMIN")
    @PostMapping("/myaccount/managepatient/edit")
    public String editPatient(@ModelAttribute Patient patient, @RequestParam Long id) {

        if (authenticationService.validUser("admin")) {
            Patient editedPatient = patientRepository.findById(patient.getId()).orElse(null);
            if (editedPatient != null) {
                editedPatient.setId(id);
                editedPatient.setName(patient.getName());
                editedPatient.setSurname(patient.getSurname());
                editedPatient.setAddress(patient.getAddress());
                editedPatient.setPesel(patient.getPesel());
                editedPatient.setPhoneNumber(patient.getPhoneNumber());
                editedPatient.setMedicamentsTakenPermamently(patient.getMedicamentsTakenPermamently());
                editedPatient.setChronicDiseases(patient.getChronicDiseases());
                patientRepository.save(editedPatient);

                return "success";
            }
            return "error";
        } else {
            return "error";
        }
    }


    @RolesAllowed("ADMIN")
    @GetMapping("/myaccount/managepatient/delete")
    public String deletePatient(@RequestParam Long id) {

        if (authenticationService.validUser("admin")) {
            if (patientRepository.findById(id).isPresent()) {
                authorityRepository.deleteAllByUsername(patientRepository.findById(id).get().getName());
                userRepository.findById(id).get().setEnabled(false);
                String name = "X - " + patientRepository.findAllByUserPatientId(id).getName();
                patientRepository.findAllByUserPatientId(id).setName(name);
                patientRepository.findAllByUserPatientId(id).setAddress(null);
                patientRepository.findAllByUserPatientId(id).setPesel(null);
                patientRepository.findAllByUserPatientId(id).setPhoneNumber(null);
                patientRepository.findAllByUserPatientId(id).setChronicDiseases(null);
                patientRepository.findAllByUserPatientId(id).setMedicamentsTakenPermamently(null);
                patientRepository.findAllByUserPatientId(id).setProposedVisits(null);
                patientRepository.findAllByUserPatientId(id);
                return "success";
            }
            return "error";
        } else {
            return "error";
        }
    }

    @RolesAllowed("ADMIN")
    @GetMapping("/myaccount/managereception")
    public String manageReceptionist(Model model) {

        if (authenticationService.validUser("admin")) {
            model.addAttribute("receptionists", receptionistRepository.findAll());
            return "account/admin/managereceptionist";
        } else {
            return "error";
        }
    }


    @RolesAllowed("ADMIN")
    @GetMapping("/myaccount/managereception/add")
    public String addReceptionist(Model model) {

        if (authenticationService.validUser("admin")) {
            model.addAttribute("receptionists", new Receptionist());
            return "account/admin/addreceptionist";
        } else {
            return "error";
        }
    }

    @RolesAllowed("ADMIN")
    @PostMapping("/myaccount/managereception/add")
    public String addReceptionist(@ModelAttribute Receptionist receptionist) {

        if (authenticationService.validUser("admin")) {
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


    @RolesAllowed("ADMIN")
    @GetMapping("/myaccount/managereception/receptionists")
    public String receptionistDetails(@RequestParam Long id, Model model) {

        if (authenticationService.validUser("admin")) {
            model.addAttribute("receptionists", receptionistRepository.findById(id));
            return "account/admin/receptionists";
        } else {
            return "error";
        }
    }


    @RolesAllowed("ADMIN")
    @GetMapping("/myaccount/managereception/edit")
    public String editReceptionist(@RequestParam Long id, Model model) {

        if (authenticationService.validUser("admin")) {
            if (receptionistRepository.findById(id).isPresent()) {
                Receptionist receptionist = receptionistRepository.findById(id).orElse(null);
                model.addAttribute("receptionist", receptionist);

                return "account/admin/editreceptionist";
            }
            return "error";
        } else {
            return "error";
        }
    }


    @RolesAllowed("ADMIN")
    @PostMapping("/myaccount/managereception/edit")
    public String editReceptionist(@ModelAttribute Receptionist receptionist, @RequestParam Long id) {

        if (authenticationService.validUser("admin")) {
            Receptionist editedReceptionist = receptionistRepository.findById(receptionist.getId()).orElse(null);
            if (editedReceptionist != null) {
                editedReceptionist.setId(id);
                editedReceptionist.setName(receptionist.getName());
                editedReceptionist.setSurname(receptionist.getSurname());
                editedReceptionist.setAddress(receptionist.getAddress());
                editedReceptionist.setPesel(receptionist.getPesel());
                editedReceptionist.setPhoneNumber(receptionist.getPhoneNumber());
                receptionistRepository.save(editedReceptionist);

                return "success";
            }
            return "error";
        } else {
            return "error";
        }
    }


    @RolesAllowed("ADMIN")
    @GetMapping("/myaccount/managereception/delete")
    public String deleteReceptionist(@RequestParam Long id) {

        if (authenticationService.validUser("admin")) {
            if (receptionistRepository.findById(id).isPresent()) {
                authorityRepository.deleteAllByUsername(receptionistRepository.findById(id).get().getName());
                userRepository.findById(id).get().setEnabled(false);
                String name = "X - " + receptionistRepository.findAllByUserReceptionistId(id).getName();
                receptionistRepository.findAllByUserReceptionistId(id).setName(name);
                receptionistRepository.findAllByUserReceptionistId(id).setAddress(null);
                receptionistRepository.findAllByUserReceptionistId(id).setPesel(null);
                receptionistRepository.findAllByUserReceptionistId(id).setPhoneNumber(null);
                receptionistRepository.findAllByUserReceptionistId(id);
                return "success";
            }
            return "error";
        } else {
            return "error";
        }
    }


    @RolesAllowed("ADMIN")
    @GetMapping("/myaccount/managespecializations/addspecialization")
    public String addSpec(Model model) {

        if (authenticationService.validUser("admin")) {
            model.addAttribute("specializations", new Specialization());
            return "account/admin/addspec";
        } else {
            return "error";
        }
    }


    @RolesAllowed("ADMIN")
    @PostMapping("/myaccount/managespecializations/addspecialization")
    public String addSpec(@ModelAttribute Specialization specialization) {

        if (authenticationService.validUser("admin")) {
            specializationRepository.save(specialization);
            return "success";
        } else {
            return "error";
        }
    }


    @RolesAllowed("ADMIN")
    @GetMapping("/myaccount/managespecializations/deletespecialization")
    public String delSpec(@RequestParam Long specid) {

        if (authenticationService.validUser("admin")) {
            doctorSpecializationRepository.deleteAllBySpecializationId(specid);
            specializationRepository.deleteById(specid);
            return "success";
        } else {
            return "error";
        }
    }


    @RolesAllowed("ADMIN")
    @GetMapping("/myaccount/managespecializations")
    public String manageSpecializations(Model model) {

        if (authenticationService.validUser("admin")) {
            model.addAttribute("specializations", specializationRepository.findAll());
            return "account/admin/managespecializations";
        } else {
            return "error";
        }
    }


}
