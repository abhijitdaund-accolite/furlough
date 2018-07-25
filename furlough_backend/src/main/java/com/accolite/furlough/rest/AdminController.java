package com.accolite.furlough.rest;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.accolite.furlough.dto.AuthenticateResponse;
import com.accolite.furlough.entity.Admin;
import com.accolite.furlough.repository.AdminRolesRepository;

@Controller
public class AdminController {

    @Autowired
    private AdminRolesRepository adminRolesRepository;

    @PostMapping("/login")
    @ResponseBody
    public AuthenticateResponse authenticate(@Valid @RequestBody final Admin user) {

        if (adminRolesRepository.existsById(user.getEmail())) {
            final Admin admin = adminRolesRepository.findById(user.getEmail()).get();
            if (admin.getPassword().equals(user.getPassword())) {
                return new AuthenticateResponse(admin, true);
            } else {
                return new AuthenticateResponse(admin, false);
            }
        } else {
            return new AuthenticateResponse(null, false);
        }

    }
}
