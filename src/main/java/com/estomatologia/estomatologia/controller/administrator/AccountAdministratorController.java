package com.estomatologia.estomatologia.controller.administrator;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.security.RolesAllowed;

@Controller
public class AccountAdministratorController {

    @RolesAllowed("ADMIN")
    @GetMapping("/myaccount/managedoctor")
    public String manageDoctor() {
        return "account/account";
    }

    @RolesAllowed("ADMIN")
    @GetMapping("/myaccount/managepatient")
    public String managePatient() {
        return "account/account";
    }

    @RolesAllowed("ADMIN")
    @GetMapping("/myaccount/managereception")
    public String manageReception() {
        return "account/account";
    }
}
