package com.accolite.furlough.rest;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.accolite.furlough.entity.Admin;
import com.accolite.furlough.repository.AdminRolesRepository;

@Controller
public class AdminController {

    @Autowired
    private AdminRolesRepository adminRolesRepository;

    @PostMapping("/login")
    @ResponseBody
    public boolean authenticate(@Valid @RequestBody final Admin user) {

        final Optional<Admin> adminValue = adminRolesRepository.findById(user.getEmail());
        if (adminValue.isPresent()) {
            return adminValue.get().getPassword().equals(user.getPassword());
        } else {
            return false;
        }

    }
}
