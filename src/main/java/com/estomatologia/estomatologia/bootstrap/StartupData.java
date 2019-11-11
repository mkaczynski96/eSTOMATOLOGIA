package com.estomatologia.estomatologia.bootstrap;

import com.estomatologia.estomatologia.model.*;
import com.estomatologia.estomatologia.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class StartupData implements CommandLineRunner {

    private final DoctorRepository doctorRepository;
    private final SpecializationRepository specializationRepository;
    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final MedicamentRepository medicamentRepository;
    private final AdministratorRepository administratorRepository;
    private final PatientRepository patientRepository;
    private final ReceptionistRepository receptionistRepository;
    private final EquipmentRepository equipmentRepository;
    private final PrescriptionRepository prescriptionRepository;
    private final VisitRepository visitRepository;
    private final DoctorSpecializationRepository doctorSpecializationRepository;


    private PasswordEncoder passwordEncoder;

    public StartupData(DoctorRepository doctorRepository, SpecializationRepository specializationRepository,
                       UserRepository userRepository, AuthorityRepository authorityRepository,
                       MedicamentRepository medicamentRepository, AdministratorRepository administratorRepository,
                       PatientRepository patientRepository, ReceptionistRepository receptionistRepository,
                       EquipmentRepository equipmentRepository, PrescriptionRepository prescriptionRepository,
                       VisitRepository visitRepository, DoctorSpecializationRepository doctorSpecializationRepository,
                       PasswordEncoder passwordEncoder) {
        this.doctorRepository = doctorRepository;
        this.specializationRepository = specializationRepository;
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
        this.medicamentRepository = medicamentRepository;
        this.administratorRepository = administratorRepository;
        this.patientRepository = patientRepository;
        this.receptionistRepository = receptionistRepository;
        this.equipmentRepository = equipmentRepository;
        this.prescriptionRepository = prescriptionRepository;
        this.visitRepository = visitRepository;
        this.doctorSpecializationRepository = doctorSpecializationRepository;
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
        userRepository.save(doctorUser);

        //Doctors authorities
        Authorities roleDoctor = new Authorities();
        roleDoctor.setUsername("doctor");
        roleDoctor.setAuthority("ROLE_DOCTOR");
        authorityRepository.save(roleDoctor);


        //Specialization
        Specialization ginecologist = new Specialization();
        ginecologist.setName("Chirurg stomatolog");
        Specialization heartSurgeon = new Specialization();
        heartSurgeon.setName("Ortodonta");

        doctorRepository.save(doctor);
        specializationRepository.save(ginecologist);
        specializationRepository.save(heartSurgeon);

        //Doctor-specialization
        DoctorSpecialization doctorSpecialization = new DoctorSpecialization();
        doctorSpecialization.setDoctor(doctor);
        doctorSpecialization.setSpecialization(ginecologist);
        doctorSpecialization.setId(new DoctorSpecializationKey(doctor.getId(), ginecologist.getId()));
        doctorSpecialization.setLicense("4336");
        doctorSpecializationRepository.save(doctorSpecialization);

        DoctorSpecialization doctorSpecialization2 = new DoctorSpecialization();
        doctorSpecialization2.setDoctor(doctor);
        doctorSpecialization2.setSpecialization(heartSurgeon);
        doctorSpecialization2.setId(new DoctorSpecializationKey(doctor.getId(), heartSurgeon.getId()));
        doctorSpecialization2.setLicense("5332");
        doctorSpecializationRepository.save(doctorSpecialization2);

        //Medicaments
        Medicament bolprazol = new Medicament();
        bolprazol.setName("BALPROZOL");
        bolprazol.setNumber(3);
        Medicament apap = new Medicament();
        apap.setName("APAP");
        apap.setNumber(5);
        medicamentRepository.save(bolprazol);
        medicamentRepository.save(apap);

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
        userRepository.save(admin);
        administratorRepository.save(administrator);

        //Admin authorities
        Authorities roleAdmin = new Authorities();
        roleAdmin.setUsername(admin.getUsername());
        roleAdmin.setAuthority("ROLE_ADMIN");
        authorityRepository.save(roleAdmin);


        //Patients
        User patient1User = new User();
        patient1User.setUsername("patient1");
        patient1User.setPassword(passwordEncoder.encode("password"));
        patient1User.setEnabled(true);
        Patient patient1 = new Patient();
        patient1.setName("Michal");
        patient1.setSurname("Ewan");
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
        patient2.setSurname("Mydliński");
        patient2.setPesel("1353434343");
        patient2.setPhoneNumber("3253666252");
        patient2.setChronicDiseases("Nadcisnienie");
        patient2.setAddress("ul. Skoczna 7/4, Warszawa");
        patient2.setMedicamentsTakenPermamently(null);
        patient2.setUserPatient(patient2User);
        patient2User.setPatient(patient2);

        userRepository.save(patient1User);
        userRepository.save(patient2User);
        patientRepository.save(patient1);
        patientRepository.save(patient2);


        //Patients authorities
        Authorities rolePatient1 = new Authorities();
        rolePatient1.setUsername(patient1User.getUsername());
        rolePatient1.setAuthority("ROLE_PATIENT");
        authorityRepository.save(rolePatient1);

        Authorities rolePatient2 = new Authorities();
        rolePatient2.setUsername(patient2User.getUsername());
        rolePatient2.setAuthority("ROLE_PATIENT");
        authorityRepository.save(rolePatient2);


        //Receptionist
        User receptionistUser = new User();
        receptionistUser.setUsername("receptionist");
        receptionistUser.setPassword(passwordEncoder.encode("password"));
        receptionistUser.setEnabled(true);
        Receptionist receptionist = new Receptionist();
        receptionist.setName("Barbara");
        receptionist.setSurname("Warecka");
        receptionist.setPesel("3543634634634643");
        receptionist.setPhoneNumber("12412412412");
        receptionist.setAddress("al. Jerozolimskie 45/322, Warszawa");
        receptionist.setUserReceptionist(receptionistUser);
        receptionistUser.setReceptionist(receptionist);

        userRepository.save(receptionistUser);
        receptionistRepository.save(receptionist);

        Authorities roleReceptionist = new Authorities();
        roleReceptionist.setUsername(receptionistUser.getUsername());
        roleReceptionist.setAuthority("ROLE_RECEPTION");
        authorityRepository.save(roleReceptionist);


        //Equipment
        Equipment chair = new Equipment();
        chair.setName("Krzesło stomatologiczne");
        chair.setNumber(5);
        equipmentRepository.save(chair);
        Equipment computer = new Equipment();
        computer.setName("Komputer HP3600");
        computer.setNumber(10);
        equipmentRepository.save(computer);


        //Visit and prescription
        Visit visit1 = new Visit();
        visit1.setPatient(patient1);
        visit1.setDoctor(doctor);
        visit1.setRecommendations("Mycie zebow 6 razy dziennie szczoteczka soniczną");
        visit1.setDiagnosis("Kamień nazębny");
        visit1.setDate("2019-03-10");
        visit1.setFinished(true);

        Prescription prescription1 = new Prescription();
        prescription1.setMedicaments("LEK500");
        prescription1.setBranchOfNFZ(1);
        prescription1.setClinicName("ESTOMATOLOGIA");

        visitRepository.save(visit1);
        prescription1.setVisit(visit1);
        prescriptionRepository.save(prescription1);


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
        userRepository.save(doctorUser2);


        Authorities roleAdmin2 = new Authorities();
        roleAdmin2.setAuthority("ROLE_ADMIN");
        roleAdmin2.setUsername(doctorUser2.getUsername());
        authorityRepository.save(roleAdmin2);

        doctorRepository.save(doctor2);

        DoctorSpecialization doctor2Specialization1 = new DoctorSpecialization();
        doctor2Specialization1.setDoctor(doctor2);
        doctor2Specialization1.setSpecialization(ginecologist);
        doctor2Specialization1.setId(new DoctorSpecializationKey(doctor2.getId(), ginecologist.getId()));
        doctor2Specialization1.setLicense("1337");
        doctorSpecializationRepository.save(doctor2Specialization1);

    }

}
