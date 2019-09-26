package com.estomatologia.estomatologia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.security.RolesAllowed;

@Controller
public class AccountController {

    @GetMapping("/myaccount")
    public String myAccount(){
        return "account/account";
    }




    /* MUTUAL */

    @GetMapping("/myaccount/mydata") //TUTAJ ZROBIC /myaccount/mydata?=userId cos w tym stylu
    public String myData(@RequestParam String userId){
        return "account/account";
    }

    @GetMapping("/myaccount/calendarofvisits")
    public String calendarOfVisits(){
        return "account/account";
    }








    /* PATIENT */

    @RolesAllowed("PATIENT")
    @GetMapping("/myaccount/historyofvisits")
    public String historyOfVisits(){
        return "account/account";
    }

    @RolesAllowed("PATIENT")
    @GetMapping("/myaccount/makevisit")
    public String makeVisit(){
        return "account/account";
    }

    @RolesAllowed("PATIENT")
    @GetMapping("/myaccount/availabledoctors")
    public String availabledoctors(){
        return "account/account";
    }

    @RolesAllowed("PATIENT")
    @GetMapping("/myaccount/myrecipes")
    public String myRecipes(){
        return "account/account";
    }





    /* DOCTOR */

    @RolesAllowed("DOCTOR")
    @GetMapping("/myaccount/mypatients")
    public String myPatients(){
        return "account/account";
    }

    @RolesAllowed("DOCTOR")
    @GetMapping("/myaccount/availablemedicaments")
    public String availableMedicaments(){
        return "account/account";
    }





    /* RECEPTION */

    @RolesAllowed("RECEPTION")
    @GetMapping("/myaccount/patients")
    public String patients(){
        return "account/account";
    }

    @RolesAllowed("RECEPTION")
    @GetMapping("/myaccount/doctors")
    public String doctors(){
        return "account/account";
    }

    @RolesAllowed("RECEPTION")
    @GetMapping("/myaccount/createdvisits")
    public String createdVisits(){
        return "account/account";
    }

    @RolesAllowed("RECEPTION")
    @GetMapping("/myaccount/manageequipment")
    public String manageEquipment(){
        return "account/account";
    }

    @RolesAllowed("RECEPTION")
    @GetMapping("/myaccount/managemedicament")
    public String manageMedicament(){
        return "account/account";
    }


    /* ADMIN */

    @RolesAllowed("ADMIN")
    @GetMapping("/myaccount/managedoctor")
    public String manageDoctor(){
        return "account/account";
    }

    @RolesAllowed("ADMIN")
    @GetMapping("/myaccount/managepatient")
    public String managePatient(){
        return "account/account";
    }

    @RolesAllowed("ADMIN")
    @GetMapping("/myaccount/managereception")
    public String manageReception(){
        return "account/account";
    }


}
