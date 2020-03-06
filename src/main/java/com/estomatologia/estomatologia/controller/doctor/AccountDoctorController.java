package com.estomatologia.estomatologia.controller.doctor;

import com.estomatologia.estomatologia.model.Patient;
import com.estomatologia.estomatologia.model.Prescription;
import com.estomatologia.estomatologia.model.Visit;
import com.estomatologia.estomatologia.repository.MedicamentRepository;
import com.estomatologia.estomatologia.repository.PatientRepository;
import com.estomatologia.estomatologia.repository.PrescriptionRepository;
import com.estomatologia.estomatologia.repository.VisitRepository;
import com.estomatologia.estomatologia.service.AuthenticationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.security.RolesAllowed;

@Controller
public class AccountDoctorController {

    private final MedicamentRepository medicamentRepository;
    private final PatientRepository patientRepository;
    private final VisitRepository visitRepository;
    private final PrescriptionRepository prescriptionRepository;

    private final AuthenticationService authenticationService;

    public AccountDoctorController(MedicamentRepository medicamentRepository, PatientRepository patientRepository,
                                   VisitRepository visitRepository, PrescriptionRepository prescriptionRepository,
                                   AuthenticationService authenticationService) {
        this.medicamentRepository = medicamentRepository;
        this.patientRepository = patientRepository;
        this.visitRepository = visitRepository;
        this.prescriptionRepository = prescriptionRepository;
        this.authenticationService = authenticationService;
    }


    @RolesAllowed("DOCTOR")
    @GetMapping("/myaccount/mypatients")
    public String myPatients(Model model) {

        if (authenticationService.validUser("doctor")) {
            model.addAttribute("patients", visitRepository.findVisitByDoctorId(authenticationService.getUserId()));
            return "account/doctor/mypatients";
        } else {
            return "error";
        }
    }


    @RolesAllowed("DOCTOR")
    @GetMapping("/myaccount/mypatients/addchronicdiseases")
    public String addChronicDiseases(@RequestParam Long id, Model model) {

        if (authenticationService.validUser("doctor")) {
            if (patientRepository.findById(id).isPresent()) {
                Patient p1 = patientRepository.findById(id).orElse(null);
                model.addAttribute("patient", p1);
                return "account/doctor/addchronicdiseases";
            } else {
                return "error";
            }
        } else {
            return "error";
        }

    }


    @RolesAllowed("DOCTOR")
    @PostMapping("/myaccount/mypatients/addchronicdiseases")
    public String addChronicDiseases(@ModelAttribute Patient patient, @RequestParam Long id) {

        if (authenticationService.validUser("doctor")) {
            Patient v1 = patientRepository.findById(id).orElse(null);
            if (v1 != null) {
                v1.setChronicDiseases(patient.getChronicDiseases());
                patientRepository.save(v1);
                return "success";
            } else {
                return "error";
            }
        } else {
            return "error";
        }
    }


    @RolesAllowed("DOCTOR")
    @GetMapping("/myaccount/mypatients/editchronicdiseases")
    public String editChronicDiseases(@RequestParam Long id, Model model) {

        if (authenticationService.validUser("doctor")) {
            if (patientRepository.findById(id).isPresent()) {
                Patient p1 = patientRepository.findById(id).orElse(null);
                model.addAttribute("patient", p1);
                return "account/doctor/editchronicdiseases";
            } else return "error";
        } else return "error";
    }


    @RolesAllowed("DOCTOR")
    @PostMapping("/myaccount/mypatients/editchronicdiseases")
    public String editChronicDiseases(@ModelAttribute Patient patient, @RequestParam Long id) {

        if (authenticationService.validUser("doctor")) {
            Patient v1 = patientRepository.findById(id).orElse(null);
            if (v1 != null) {
                v1.setChronicDiseases(patient.getChronicDiseases());
                patientRepository.save(v1);
                return "success";
            } else return "error";

        } else return "error";

    }

    @RolesAllowed("DOCTOR")
    @GetMapping("/myaccount/mypatients/deletechronicdiseases")
    public String deleteChronicdiseases(@RequestParam Long id) {

        if (authenticationService.validUser("doctor")) {
            if (patientRepository.findById(id).isPresent()) {
                Patient v1 = patientRepository.findById(id).get();
                v1.setChronicDiseases("");
                patientRepository.save(v1);
                return "success";
            }
            return "error";
        } else {
            return "error";
        }

    }


    @RolesAllowed("DOCTOR")
    @GetMapping("/myaccount/mypatients/addmtp")
    public String addMtp(@RequestParam Long id, Model model) {
        if (authenticationService.validUser("doctor")) {
            if (patientRepository.findById(id).isPresent()) {
                Patient p1 = patientRepository.findById(id).orElse(null);
                model.addAttribute("patient", p1);
                return "account/doctor/addmtp";
            }
            return "error";
        } else {
            return "error";
        }
    }


    @RolesAllowed("DOCTOR")
    @PostMapping("/myaccount/mypatients/addmtp")
    public String addMtp(@ModelAttribute Patient patient, @RequestParam Long id) {
        if (authenticationService.validUser("doctor")) {

            Patient v1 = patientRepository.findById(id).orElse(null);
            if (v1 != null) {
                v1.setMedicamentsTakenPermamently(patient.getMedicamentsTakenPermamently());
                patientRepository.save(v1);
                return "success";
            } else {
                return "error";
            }
        } else {
            return "error";
        }

    }


    @RolesAllowed("DOCTOR")
    @GetMapping("/myaccount/mypatients/editmtp")
    public String editMtp(@RequestParam Long id, Model model) {
        if (authenticationService.validUser("doctor")) {
            if (patientRepository.findById(id).isPresent()) {
                Patient p1 = patientRepository.findById(id).orElse(null);
                model.addAttribute("patient", p1);
                return "account/doctor/editmtp";
            }
            return "error";
        } else {
            return "error";
        }

    }


    @RolesAllowed("DOCTOR")
    @PostMapping("/myaccount/mypatients/editmtp")
    public String editMtp(@ModelAttribute Patient patient, @RequestParam Long id) {
        if (authenticationService.validUser("doctor")) {

            Patient v1 = patientRepository.findById(id).orElse(null);
            if (v1 != null) {
                v1.setMedicamentsTakenPermamently(patient.getMedicamentsTakenPermamently());
                patientRepository.save(v1);
                return "success";
            } else {
                return "error";
            }
        } else {
            return "error";
        }

    }

    @RolesAllowed("DOCTOR")
    @GetMapping("/myaccount/mypatients/deletemtp")
    public String deleteMtp(@RequestParam Long id) {
        if (authenticationService.validUser("doctor")) {
            if (patientRepository.findById(id).isPresent()) {
                Patient v1 = patientRepository.findById(id).get();
                v1.setMedicamentsTakenPermamently("");
                patientRepository.save(v1);
                return "success";
            }
            return "error";
        } else {
            return "error";
        }

    }


    @RolesAllowed("DOCTOR")
    @GetMapping("/myaccount/availablemedicaments")
    public String availableMedicaments(Model model) {

        if (authenticationService.validUser("doctor")) {
            model.addAttribute("medicaments", medicamentRepository.findAll());
            return "account/doctor/availablemedicaments";
        } else {
            return "error";
        }


    }


    @RolesAllowed("DOCTOR")
    @GetMapping("/myaccount/calendarofvisits/finishvisit")
    public String finishVisit(@RequestParam Long id) {
        if (authenticationService.validUser("doctor")) {
            if (visitRepository.findById(id).isPresent()) {
                Visit v1 = visitRepository.findById(id).get();
                v1.setFinished(true);
                visitRepository.save(v1);
                return "success";
            }
            return "error";
        } else {
            return "error";
        }

    }


    @RolesAllowed("DOCTOR")
    @GetMapping("/myaccount/calendarofvisits/deletevisit")
    public String deleteVisit(@RequestParam Long id) {
        if (authenticationService.validUser("doctor")) {
            if (visitRepository.findById(id).isPresent()) {
                visitRepository.deleteById(id);
                return "success";
            }
            return "error";
        } else {
            return "error";
        }

    }


    @RolesAllowed("DOCTOR")
    @GetMapping("/myaccount/calendarofvisits/deleterecommendations")
    public String deleteRecommendations(@RequestParam Long id) {
        if (authenticationService.validUser("doctor")) {
            if (visitRepository.findById(id).isPresent()) {
                Visit v1 = visitRepository.findById(id).get();
                v1.setRecommendations("");
                visitRepository.save(v1);
                return "success";
            }
            return "error";
        } else {
            return "error";
        }

    }

    @RolesAllowed("DOCTOR")
    @GetMapping("/myaccount/calendarofvisits/deletediagnosis")
    public String deleteDiagnosis(@RequestParam Long id) {
        if (authenticationService.validUser("doctor")) {
            if (visitRepository.findById(id).isPresent()) {
                Visit v1 = visitRepository.findById(id).get();
                v1.setDiagnosis("");
                visitRepository.save(v1);
                return "success";
            }
            return "error";
        } else {
            return "error";
        }

    }


    @RolesAllowed("DOCTOR")
    @GetMapping("/myaccount/calendarofvisits/adddiagnosis")
    public String addDiagnosis(@RequestParam Long id, Model model) {
        if (authenticationService.validUser("doctor")) {
            if (visitRepository.findById(id).isPresent()) {
                Visit v1 = visitRepository.findById(id).orElse(null);
                model.addAttribute("visit", v1);
                model.addAttribute("action", "add");
                return "account/doctor/adddiagnosis";
            }
            return "error";
        } else {
            return "error";
        }

    }


    @RolesAllowed("DOCTOR")
    @PostMapping("/myaccount/calendarofvisits/adddiagnosis")
    public String addDiagnosis(@ModelAttribute Visit visit, @RequestParam Long id) {

        if (authenticationService.validUser("doctor")) {
            Visit v1 = visitRepository.findById(id).orElse(null);
            if (v1 != null) {
                v1.setDiagnosis(visit.getDiagnosis());
                visitRepository.save(v1);
                return "success";
            } else {
                return "error";
            }
        } else {
            return "error";
        }

    }


    @RolesAllowed("DOCTOR")
    @GetMapping("/myaccount/calendarofvisits/editdiagnosis")
    public String editDiagnosis(@RequestParam Long id, Model model) {

        if (authenticationService.validUser("doctor")) {
            if (visitRepository.findById(id).isPresent()) {
                Visit v1 = visitRepository.findById(id).orElse(null);
                model.addAttribute("visit", v1);
                model.addAttribute("action", "edit");
                return "account/doctor/editdiagnosis";
            }
            return "error";
        } else {
            return "error";
        }

    }


    @RolesAllowed("DOCTOR")
    @PostMapping("/myaccount/calendarofvisits/editdiagnosis")
    public String editDiagnosis(@ModelAttribute Visit visit, @RequestParam Long id) {

        if (authenticationService.validUser("doctor")) {
            Visit v1 = visitRepository.findById(id).orElse(null);
            if (v1 != null) {
                v1.setDiagnosis(visit.getDiagnosis());
                visitRepository.save(v1);
                return "success";
            } else {
                return "error";
            }
        } else {
            return "error";
        }

    }


    @RolesAllowed("DOCTOR")
    @GetMapping("/myaccount/calendarofvisits/addrecommendations")
    public String addRecommendations(@RequestParam Long id, Model model) {

        if (authenticationService.validUser("doctor")) {
            if (visitRepository.findById(id).isPresent()) {
                Visit v1 = visitRepository.findById(id).orElse(null);
                model.addAttribute("visit", v1);
                model.addAttribute("action", "add");
                return "account/doctor/addrecommendations";
            }
            return "error";
        } else {
            return "error";
        }

    }


    @RolesAllowed("DOCTOR")
    @PostMapping("/myaccount/calendarofvisits/addrecommendations")
    public String addRecommendations(@ModelAttribute Visit visit, @RequestParam Long id) {

        if (authenticationService.validUser("doctor")) {
            Visit v1 = visitRepository.findById(id).orElse(null);
            if (v1 != null) {
                v1.setRecommendations(visit.getRecommendations());
                visitRepository.save(v1);
                return "success";
            } else {
                return "error";
            }
        } else {
            return "error";
        }

    }


    @RolesAllowed("DOCTOR")
    @GetMapping("/myaccount/calendarofvisits/editrecommendations")
    public String editRecommendations(@RequestParam Long id, Model model) {

        if (authenticationService.validUser("doctor")) {
            if (visitRepository.findById(id).isPresent()) {
                Visit v1 = visitRepository.findById(id).orElse(null);
                model.addAttribute("visit", v1);
                model.addAttribute("action", "edit");
                return "account/doctor/editrecommendations";
            }
            return "error";
        } else {
            return "error";
        }

    }


    @RolesAllowed("DOCTOR")
    @PostMapping("/myaccount/calendarofvisits/editrecommendations")
    public String editRecommendations(@ModelAttribute Visit visit, @RequestParam Long id) {

        if (authenticationService.validUser("doctor")) {
            Visit v1 = visitRepository.findById(id).orElse(null);
            if (v1 != null) {
                v1.setRecommendations(visit.getRecommendations());
                visitRepository.save(v1);
                return "success";
            } else {
                return "error";
            }
        } else {
            return "error";
        }

    }


    @RolesAllowed("DOCTOR")
    @GetMapping("/myaccount/calendarofvisits/addprescription")
    public String addPrescription(@RequestParam Long id, Model model) {

        if (authenticationService.validUser("doctor")) {
            model.addAttribute("visit", id);
            model.addAttribute("prescription", new Prescription());
            return "account/doctor/addprescription";
        } else {
            return "error";
        }

    }


    @RolesAllowed("DOCTOR")
    @PostMapping("/myaccount/calendarofvisits/addprescription")
    public String addPrescription(@ModelAttribute Prescription prescription, @RequestParam Long id) {

        if (authenticationService.validUser("doctor")) {
            Prescription p = new Prescription();
            p.setClinicName(prescription.getClinicName());
            p.setBranchOfNFZ(prescription.getBranchOfNFZ());
            p.setMedicaments(prescription.getMedicaments());
            p.setVisit(visitRepository.findById(id).orElse(null));

            prescriptionRepository.save(p);
            return "success";
        } else {
            return "error";
        }

    }


}
