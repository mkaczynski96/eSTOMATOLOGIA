package com.estomatologia.estomatologia.bootstrap;

import com.estomatologia.estomatologia.model.*;
import com.estomatologia.estomatologia.service.orm.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataExample implements CommandLineRunner {

    private final DoctorService doctorService;
    private final SpecializationService specializationService;
    private final UserService userService;
    private final AuthoritiesService authoritiesService;
    private final MedicamentService medicamentService;
    private final AdministratorService administratorService;
    private final PatientService patientService;
    private final ReceptionistService receptionistService;
    private final EquipmentService equipmentService;
    private final PrescriptionService prescriptionService;
    private final ProposedVisitService proposedVisit;
    private final VisitService visitService;
    private final DoctorSpecializationService doctorSpecializationService;


    private PasswordEncoder passwordEncoder;

    public DataExample(DoctorService doctorService, SpecializationService specializationService,
                       UserService userService, AuthoritiesService authoritiesService, MedicamentService medicamentService,
                       AdministratorService administratorService, PatientService patientService, ReceptionistService receptionistService,
                       EquipmentService equipmentService, PrescriptionService prescriptionService, ProposedVisitService proposedVisit,
                       VisitService visitService, DoctorSpecializationService doctorSpecializationService, PasswordEncoder passwordEncoder) {
        this.doctorService = doctorService;
        this.specializationService = specializationService;
        this.userService = userService;
        this.authoritiesService = authoritiesService;
        this.medicamentService = medicamentService;
        this.administratorService = administratorService;
        this.patientService = patientService;
        this.receptionistService = receptionistService;
        this.equipmentService = equipmentService;
        this.prescriptionService = prescriptionService;
        this.proposedVisit = proposedVisit;
        this.visitService = visitService;
        this.doctorSpecializationService = doctorSpecializationService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {




        //User doctor//
        User doctorUser = new User();
        doctorUser.setUsername("doctor");
        doctorUser.setPassword(passwordEncoder.encode("password"));
        doctorUser.setEnabled(true);
        Doctor doctor = new Doctor();
        doctor.setName("Jan");
        doctor.setSurname("Abacki");
        doctor.setPesel("5755747474");
        doctor.setPhoneNumber("4363737734");
        doctor.setAddress("ul. Jana Pawla II 13/12, Warszawa");

        //Connection
        doctor.setUserDoctor(doctorUser);
        doctorUser.setDoctor(doctor);
        userService.save(doctorUser);

        //Doctors authorities
        Authorities roleDoctor = new Authorities();
        roleDoctor.setUsername("doctor");
        roleDoctor.setAuthority("ROLE_DOCTOR");
        authoritiesService.save(roleDoctor);


        //Specialization
        Specialization ginecologist = new Specialization();
        ginecologist.setName("Ginekolog");
        Specialization heartSurgeon = new Specialization();
        heartSurgeon.setName("Kardiochirurg");

        doctorService.save(doctor);
        specializationService.save(ginecologist);
        specializationService.save(heartSurgeon);

        //Doctor-specialization
        DoctorSpecialization doctorSpecialization = new DoctorSpecialization();
        doctorSpecialization.setDoctor(doctor);
        doctorSpecialization.setSpecialization(ginecologist);
        doctorSpecialization.setId(new DoctorSpecializationKey(doctor.getId(), ginecologist.getId()));
        doctorSpecialization.setLicense("4336");
        doctorSpecializationService.save(doctorSpecialization);

        DoctorSpecialization doctorSpecialization2 = new DoctorSpecialization();
        doctorSpecialization2.setDoctor(doctor);
        doctorSpecialization2.setSpecialization(heartSurgeon);
        doctorSpecialization2.setId(new DoctorSpecializationKey(doctor.getId(), heartSurgeon.getId()));
        doctorSpecialization2.setLicense("5332");
        doctorSpecializationService.save(doctorSpecialization2);

        //Medicaments
        Medicament bolprazol = new Medicament();
        bolprazol.setName("BALPROZOL");
        bolprazol.setNumber(3);
        Medicament apap = new Medicament();
        apap.setName("APAP");
        apap.setNumber(5);
        medicamentService.save(bolprazol);
        medicamentService.save(apap);

        //Administrator
        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("password"));
        admin.setEnabled(true);
        Administrator administrator = new Administrator();
        administrator.setName("Adam");
        administrator.setSurname("Swalczyk");
        administrator.setPesel("35353252");
        administrator.setPhoneNumber("25253252352");
        administrator.setAddress("ul. Mrozna 3/111, Warszawa");
        administrator.setUserAdministrator(admin);
        admin.setAdministrator(administrator);
        userService.save(admin);
        administratorService.save(administrator);

        //Admin authorities
        Authorities roleAdmin = new Authorities();
        roleAdmin.setUsername(admin.getUsername());
        roleAdmin.setAuthority("ROLE_ADMIN");
        authoritiesService.save(roleAdmin);


        //Patients
        User patient1User = new User();
        patient1User.setUsername("patient1");
        patient1User.setPassword(passwordEncoder.encode("password"));
        patient1User.setEnabled(true);
        Patient patient1 = new Patient();
        patient1.setName("Michal");
        patient1.setSurname("Ewanc");
        patient1.setPesel("4353453453");
        patient1.setPhoneNumber("3252353252");
        patient1.setChronicDiseases("Zapalenie spojowek");
        patient1.setAddress("ul. Bobra 1/2, Warszawa");
        patient1.setMedicamentsTakenPermamently(null);
        patient1.setUserPatient(patient1User);
        patient1User.setPatient(patient1);

        User patient2User = new User();
        patient2User.setUsername("patient2");
        patient2User.setPassword(passwordEncoder.encode("password"));
        patient2User.setEnabled(true);
        Patient patient2 = new Patient();
        patient2.setName("Kamil");
        patient2.setSurname("Babon");
        patient2.setPesel("1353434343");
        patient2.setPhoneNumber("3253666252");
        patient2.setChronicDiseases("Nadcisnienie");
        patient2.setAddress("ul. Skoczna 7/4, Warszawa");
        patient2.setMedicamentsTakenPermamently(null);
        patient2.setUserPatient(patient2User);
        patient2User.setPatient(patient2);

        userService.save(patient1User);
        userService.save(patient2User);
        patientService.save(patient1);
        patientService.save(patient2);


        //Patients authorities
        Authorities rolePatient1 = new Authorities();
        rolePatient1.setUsername(patient1User.getUsername());
        rolePatient1.setAuthority("ROLE_PATIENT");
        authoritiesService.save(rolePatient1);

        Authorities rolePatient2 = new Authorities();
        rolePatient2.setUsername(patient2User.getUsername());
        rolePatient2.setAuthority("ROLE_PATIENT");
        authoritiesService.save(rolePatient2);


        //Receptionist
        User receptionistUser = new User();
        receptionistUser.setUsername("receptionist");
        receptionistUser.setPassword(passwordEncoder.encode("password"));
        receptionistUser.setEnabled(true);
        Receptionist receptionist = new Receptionist();
        receptionist.setName("Barbara");
        receptionist.setSurname("Wanna");
        receptionist.setPesel("3543634634634643");
        receptionist.setPhoneNumber("12412412412");
        receptionist.setAddress("al. Jerozolimskie 45/322, Warszawa");
        receptionist.setUserReceptionist(receptionistUser);
        receptionistUser.setReceptionist(receptionist);

        userService.save(receptionistUser);
        receptionistService.save(receptionist);

        Authorities roleReceptionist = new Authorities();
        roleReceptionist.setUsername(receptionistUser.getUsername());
        roleReceptionist.setAuthority("ROLE_RECEPTION");
        authoritiesService.save(roleReceptionist);


        //Equipment
        Equipment chair = new Equipment();
        chair.setName("Krzeslo");
        chair.setNumber(5);
        equipmentService.save(chair);
        Equipment computer = new Equipment();
        computer.setName("COMPUTER20000");
        computer.setNumber(1);
        equipmentService.save(computer);


        //Visit and prescription
        Visit visit1 = new Visit();
        visit1.setPatient(patient1);
        visit1.setDoctor(doctor);
        visit1.setRecommendations("Mycie zebow 6 razy dziennie szczoteczka soniczną");
        visit1.setDiagnosis("Kamień nazębny");
        visit1.setDate("2019-03-10");

        Prescription prescription1 = new Prescription();
        prescription1.setMedicaments("LEK500");
        prescription1.setBranchOfNFZ(1);
        prescription1.setClinicName("ESTOMATOLOGIA");

        visitService.save(visit1);
        prescription1.setVisit(visit1);
        prescriptionService.save(prescription1);


        //Doctor2
        User doctorUser2 = new User();
        doctorUser2.setUsername("doctor2");
        doctorUser2.setPassword(passwordEncoder.encode("password"));
        doctorUser2.setEnabled(true);

        Doctor doctor2 = new Doctor();
        doctor2.setName("Paweł");
        doctor2.setSurname("Iwańczyk");
        doctor2.setPesel("32532525");
        doctor2.setAddress("ul. Kazimierza 3/15, Warszawa");
        doctor2.setPhoneNumber("3252627770");

        doctor2.setUserDoctor(doctorUser2);
        doctorUser2.setDoctor(doctor2);
        userService.save(doctorUser2);


        Authorities roleAdmin2 = new Authorities();
        roleAdmin2.setAuthority("ROLE_ADMIN");
        roleAdmin2.setUsername(doctorUser2.getUsername());
        authoritiesService.save(roleAdmin2);

        doctorService.save(doctor2);

        DoctorSpecialization doctor2Specialization1 = new DoctorSpecialization();
        doctor2Specialization1.setDoctor(doctor2);
        doctor2Specialization1.setSpecialization(ginecologist);
        doctor2Specialization1.setId(new DoctorSpecializationKey(doctor2.getId(), ginecologist.getId()));
        doctor2Specialization1.setLicense("1337");
        doctorSpecializationService.save(doctor2Specialization1);

    }

}
