package com.softserve.actent.verification.controller;

import com.softserve.actent.verification.service.Verification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

@Validated
@Controller
public class VerificationController {

    @Autowired
    private Verification verification;

    @RequestMapping(value = "/confirm", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String confirmEmail(@RequestParam(value = "login")
                                         String login,
                               @RequestParam(value = "uuid")
                                       String uuid){

        if(verification.confirmUser(login, uuid)){
            return "forward:" + "/";
        }else {
            return "forward:" + "/404.html";
        }
    }

}
