package com.estomatologia.estomatologia.bootstrap;

import com.estomatologia.estomatologia.model.Doctor;
import com.estomatologia.estomatologia.model.Specialization;
import com.estomatologia.estomatologia.service.DoctorService;
import com.estomatologia.estomatologia.service.SpecializationService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataExample implements CommandLineRunner {

    private final DoctorService doctorService;
    private final SpecializationService specializationService;

    public DataExample(DoctorService doctorService, SpecializationService specializationService) {
        this.doctorService = doctorService;
        this.specializationService = specializationService;
    }


    @Override
    public void run(String... args) throws Exception {
        Doctor doctor1 = new Doctor();
        doctor1.setName("John");
        doctor1.setSurname("Mackintosh");
        doctor1.setPesel("436346346");
        doctor1.setPhoneNumber("43637377");

        doctorService.save(doctor1);

        Specialization specialization1 = new Specialization();
        specialization1.setName("Ginekolog");

        specializationService.save(specialization1);
    }
}
