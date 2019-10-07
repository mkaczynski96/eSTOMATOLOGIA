package com.estomatologia.estomatologia.controller.receptionist;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.security.RolesAllowed;

@Controller
public class AccountReceptionistController {

    @RolesAllowed("RECEPTION")
    @GetMapping("/myaccount/patients")
    public String patients() {
        return "account/account";
    }

    @RolesAllowed("RECEPTION")
    @GetMapping("/myaccount/doctors")
    public String doctors() {
        return "account/account";
    }

    @RolesAllowed("RECEPTION")
    @GetMapping("/myaccount/createdvisits")
    public String createdVisits() {
        return "account/account";
    }

    @RolesAllowed("RECEPTION")
    @GetMapping("/myaccount/manageequipment")
    public String manageEquipment() {
        return "account/account";
    }

    @RolesAllowed("RECEPTION")
    @GetMapping("/myaccount/managemedicament")
    public String manageMedicament() {
        return "account/account";
    }

}
