package com.accolite.furlough.rest;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestBody.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.accolite.furlough.dto.AuthenticateResponse;
import com.accolite.furlough.entity.AdminModel;
import com.accolite.furlough.repository.AdminRolesRepository;
import com.accolite.furlough.dto.UserModel;

@Controller
public class AdminController{
	
	@Autowired
	private AdminRolesRepository adminRolesRepository;
	
	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public AuthenticateResponse authenticate(@Valid @RequestBody UserModel user) {
		
		AuthenticateResponse response;
		
		System.out.println(user);
		
		return this.adminRolesRepository.findById(user.getUsername()).map(admin -> {
			if(admin.getPassword().equals(user.getPassword())) {
				return new  AuthenticateResponse(admin,true);
			} else {
				return new AuthenticateResponse(null,false);
			}
		}).orElseThrow(null);
		
		
	}
}
