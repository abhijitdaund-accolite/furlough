package com.accolite.furlough.rest;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.accolite.furlough.dto.AuthenticateResponse;
import com.accolite.furlough.dto.UserModel;
import com.accolite.furlough.repository.AdminRolesRepository;

@Controller
public class AdminController {

    @Autowired
    private AdminRolesRepository adminRolesRepository;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public AuthenticateResponse authenticate(@Valid @RequestBody final UserModel user) {

        // final AuthenticateResponse response;

        System.out.println(user);

        return this.adminRolesRepository.findById(user.getUsername()).map(admin -> {
            if (admin.getPassword().equals(user.getPassword())) {
                return new AuthenticateResponse(admin, true);
            } else {
                return new AuthenticateResponse(null, false);
            }
        }).orElseThrow(null);

    }
}
