package com.estomatologia.estomatologia.controller.receptionist;

import com.estomatologia.estomatologia.model.*;
import com.estomatologia.estomatologia.repository.*;
import com.estomatologia.estomatologia.service.security.AuthorizationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.security.RolesAllowed;

@Controller
public class AccountReceptionistController {

    private ReceptionistRepository receptionistRepository;
    private PatientRepository patientRepository;
    private DoctorRepository doctorRepository;
    private DoctorSpecializationRepository doctorSpecializationRepository;
    private MedicamentRepository medicamentRepository;
    private EquipmentRepository equipmentRepository;
    private VisitRepository visitRepository;
    private ProposedVisitRepository proposedVisitRepository;

    private AuthorizationService authorizationService;

    public AccountReceptionistController(ReceptionistRepository receptionistRepository, PatientRepository patientRepository, DoctorRepository doctorRepository, DoctorSpecializationRepository doctorSpecializationRepository, MedicamentRepository medicamentRepository, EquipmentRepository equipmentRepository, VisitRepository visitRepository, ProposedVisitRepository proposedVisitRepository, AuthorizationService authorizationService) {
        this.receptionistRepository = receptionistRepository;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
        this.doctorSpecializationRepository = doctorSpecializationRepository;
        this.medicamentRepository = medicamentRepository;
        this.equipmentRepository = equipmentRepository;
        this.visitRepository = visitRepository;
        this.proposedVisitRepository = proposedVisitRepository;
        this.authorizationService = authorizationService;
    }

    @RolesAllowed("RECEPTION")
    @GetMapping("/myaccount/patients")
    public String patients(Model model) {
        User loggedUser = authorizationService.getLoggedUser();

        if (loggedUser == null) {
            return "error";
        } else {
            Long loggedUserId = loggedUser.getId();
            if (receptionistRepository.findById(loggedUserId).isPresent()) {
                model.addAttribute("patients", patientRepository.findAll());
                return "account/receptionist/patients";
            } else {
                return "error";
            }
        }
    }

    @RolesAllowed("RECEPTION")
    @GetMapping("/myaccount/patients/patientdetails")
    public String morePatientDetails(@RequestParam Long id, Model model) {
        User loggedUser = authorizationService.getLoggedUser();

        if (loggedUser == null) {
            return "error";
        } else {
            Long loggedUserId = loggedUser.getId();
            if (receptionistRepository.findById(loggedUserId).isPresent()) {

                model.addAttribute("patients", patientRepository.findById(id));
                model.addAttribute("visits", visitRepository.findAllByPatientId(id));
                return "account/receptionist/patientdetails";
            } else {
                return "error";
            }
        }
    }

    @RolesAllowed("RECEPTION")
    @GetMapping("/myaccount/doctors")
    public String doctors(Model model) {
        User loggedUser = authorizationService.getLoggedUser();

        if (loggedUser == null) {
            return "error";
        } else {
            Long loggedUserId = loggedUser.getId();
            if (receptionistRepository.findById(loggedUserId).isPresent()) {

                model.addAttribute("doctors", doctorRepository.findAll());
                model.addAttribute("specializations", doctorSpecializationRepository.findAll());
                return "account/receptionist/doctors";
            } else {
                return "error";
            }
        }
    }

    @RolesAllowed("RECEPTION")
    @GetMapping("/myaccount/doctors/doctordetails")
    public String moreDoctorDetails(@RequestParam Long id, Model model) {
        User loggedUser = authorizationService.getLoggedUser();

        if (loggedUser == null) {
            return "error";
        } else {
            Long loggedUserId = loggedUser.getId();
            if (receptionistRepository.findById(loggedUserId).isPresent()) {

                model.addAttribute("doctor", doctorRepository.findById(id));
                model.addAttribute("specializations", doctorSpecializationRepository.findAllByDoctorId(id));
                return "account/receptionist/doctordetails";
            } else {
                return "error";
            }
        }
    }

    @RolesAllowed("RECEPTION")
    @GetMapping("/myaccount/createdvisits")
    public String createdVisits(Model model) {
        User loggedUser = authorizationService.getLoggedUser();

        if (loggedUser == null) {
            return "error";
        } else {
            Long loggedUserId = loggedUser.getId();
            if (receptionistRepository.findById(loggedUserId).isPresent()) {
                model.addAttribute("proposedVisits",proposedVisitRepository.findAll());
                return "account/receptionist/createdvisits";
            } else {
                return "error";
            }
        }
    }


    @RolesAllowed("RECEPTION")
    @GetMapping("/myaccount/createdvisits/add")
    public String addToVisit(@RequestParam Long id) {
        User loggedUser = authorizationService.getLoggedUser();

        if (loggedUser == null) {
            return "error";
        } else {
            Long loggedUserId = loggedUser.getId();
            if (receptionistRepository.findById(loggedUserId).isPresent()) {

                //TODO ADD STATUS TO VISIT - ZAKONCZONA/AKTYWNA.


                if (proposedVisitRepository.findById(id).isPresent()) {
                    Visit visit = new Visit();
                    ProposedVisit proposedVisit = proposedVisitRepository.findById(id).orElse(null);
                    visit.setPatient(proposedVisit.getPatient());
                    visit.setDoctor(proposedVisit.getDoctor());
                    visit.setDate(proposedVisit.getDate());
                    visitRepository.save(visit);
                    proposedVisitRepository.deleteById(id);
                }
                return "success";
            } else {
                return "error";
            }
        }
    }





    @RolesAllowed("RECEPTION")
    @GetMapping("/myaccount/manageequipment")
    public String manageEquipment(Model model) {
        User loggedUser = authorizationService.getLoggedUser();

        if (loggedUser == null) {
            return "error";
        } else {
            Long loggedUserId = loggedUser.getId();
            if (receptionistRepository.findById(loggedUserId).isPresent()) {

                model.addAttribute("equipments", equipmentRepository.findAll());
                return "account/receptionist/manageequipments";
            } else {
                return "error";
            }
        }
    }


    @RolesAllowed("RECEPTION")
    @GetMapping("/myaccount/manageequipment/add")
    public String addEquipment(Model model) {
        User loggedUser = authorizationService.getLoggedUser();

        if (loggedUser == null) {
            return "error";
        } else {
            Long loggedUserId = loggedUser.getId();
            if (receptionistRepository.findById(loggedUserId).isPresent()) {
                model.addAttribute("equipment", new Equipment());
                return "account/receptionist/addequipment";
            } else {
                return "error";
            }
        }
    }

    @RolesAllowed("RECEPTION")
    @PostMapping("/myaccount/manageequipment/add")
    public String addEquipment(@ModelAttribute Equipment equipment) {
        User loggedUser = authorizationService.getLoggedUser();

        if (loggedUser == null) {
            return "error";
        } else {
            Long loggedUserId = loggedUser.getId();
            if (receptionistRepository.findById(loggedUserId).isPresent()) {
                equipmentRepository.save(equipment);
                return "success";
            } else {
                return "error";
            }
        }
    }


    @RolesAllowed("RECEPTION")
    @GetMapping("/myaccount/manageequipment/edit")
    public String editEquipment(@RequestParam Long id, Model model) {
        User loggedUser = authorizationService.getLoggedUser();

        if (loggedUser == null) {
            return "error";
        } else {
            Long loggedUserId = loggedUser.getId();
            if (receptionistRepository.findById(loggedUserId).isPresent()) {
                Equipment equipment = equipmentRepository.findById(id).orElse(null);
                model.addAttribute("equipment", equipment);
                return "account/receptionist/editequipment";
            } else {
                return "error";
            }
        }
    }

    @RolesAllowed("RECEPTION")
    @PostMapping("/myaccount/manageequipment/edit")
    public String editEquipment(@ModelAttribute Equipment equipment, @RequestParam Long id, Model model) {
        User loggedUser = authorizationService.getLoggedUser();

        if (loggedUser == null) {
            return "error";
        } else {
            Long loggedUserId = loggedUser.getId();
            if (receptionistRepository.findById(loggedUserId).isPresent()) {

                Equipment equipmentEdited = equipmentRepository.findById(id).orElse(null);
                if (equipmentEdited != null) {
                    equipmentEdited.setId(id);
                    equipmentEdited.setName(equipment.getName());
                    equipmentEdited.setNumber(equipment.getNumber());
                    equipmentRepository.save(equipmentEdited);
                }
                model.addAttribute("success", "equipment");
                return "success";
            } else {
                return "error";
            }
        }
    }


    @RolesAllowed("RECEPTION")
    @GetMapping("/myaccount/manageequipment/delete")
    public String deleteEquipment(@RequestParam Long id, Model model) {
        User loggedUser = authorizationService.getLoggedUser();

        if (loggedUser == null) {
            return "error";
        } else {
            Long loggedUserId = loggedUser.getId();
            if (receptionistRepository.findById(loggedUserId).isPresent()) {
                equipmentRepository.deleteById(id);
                model.addAttribute("success", "equipment");
                return "success";
            } else {
                return "error";
            }
        }
    }


    @RolesAllowed("RECEPTION")
    @GetMapping("/myaccount/managemedicament")
    public String manageMedicament(Model model) {
        User loggedUser = authorizationService.getLoggedUser();

        if (loggedUser == null) {
            return "error";
        } else {
            Long loggedUserId = loggedUser.getId();
            if (receptionistRepository.findById(loggedUserId).isPresent()) {

                model.addAttribute("medicaments", medicamentRepository.findAll());
                return "account/receptionist/managemedicaments";
            } else {
                return "error";
            }
        }
    }


    @RolesAllowed("RECEPTION")
    @GetMapping("/myaccount/managemedicament/add")
    public String addMedicament(Model model) {
        User loggedUser = authorizationService.getLoggedUser();

        if (loggedUser == null) {
            return "error";
        } else {
            Long loggedUserId = loggedUser.getId();
            if (receptionistRepository.findById(loggedUserId).isPresent()) {
                model.addAttribute("medicament", new Medicament());
                return "account/receptionist/addmedicament";
            } else {
                return "error";
            }
        }
    }

    @RolesAllowed("RECEPTION")
    @PostMapping("/myaccount/managemedicament/add")
    public String submitMedicament(@ModelAttribute Medicament medicament, Model model) {
        User loggedUser = authorizationService.getLoggedUser();

        if (loggedUser == null) {
            return "error";
        } else {
            Long loggedUserId = loggedUser.getId();
            if (receptionistRepository.findById(loggedUserId).isPresent()) {
                medicamentRepository.save(medicament);
                model.addAttribute("success", "medicament");
                return "success";
            } else {
                return "error";
            }
        }
    }

    @RolesAllowed("RECEPTION")
    @GetMapping("/myaccount/managemedicament/edit")
    public String editMedicament(@RequestParam Long id, Model model) {

        User loggedUser = authorizationService.getLoggedUser();

        if (loggedUser == null) {
            return "error";
        } else {
            Long loggedUserId = loggedUser.getId();
            if (receptionistRepository.findById(loggedUserId).isPresent()) {
                Medicament medicament = medicamentRepository.findById(id).orElse(null);
                model.addAttribute("medicament", medicament);
                return "account/receptionist/editmedicament";
            } else {
                return "error";
            }
        }
    }

    @RolesAllowed("RECEPTION")
    @PostMapping("/myaccount/managemedicament/edit")
    public String editMedicament(@ModelAttribute Medicament medicament, @RequestParam Long id, Model model) {
        User loggedUser = authorizationService.getLoggedUser();

        if (loggedUser == null) {
            return "error";
        } else {
            Long loggedUserId = loggedUser.getId();
            if (receptionistRepository.findById(loggedUserId).isPresent()) {

                Medicament medicamentEdited = medicamentRepository.findById(id).orElse(null);
                if (medicamentEdited != null) {
                    medicamentEdited.setId(id);
                    medicamentEdited.setName(medicament.getName());
                    medicamentEdited.setNumber(medicament.getNumber());
                    medicamentRepository.save(medicamentEdited);
                }
                model.addAttribute("success", "medicament");
                return "success";
            } else {
                return "error";
            }
        }
    }

    @RolesAllowed("RECEPTION")
    @GetMapping("/myaccount/managemedicament/delete")
    public String deleteMedicament(@RequestParam Long id, Model model) {
        User loggedUser = authorizationService.getLoggedUser();

        if (loggedUser == null) {
            return "error";
        } else {
            Long loggedUserId = loggedUser.getId();
            if (receptionistRepository.findById(loggedUserId).isPresent()) {
                medicamentRepository.deleteById(id);
                model.addAttribute("success", "medicament");
                return "success";
            } else {
                return "error";
            }
        }
    }


}
