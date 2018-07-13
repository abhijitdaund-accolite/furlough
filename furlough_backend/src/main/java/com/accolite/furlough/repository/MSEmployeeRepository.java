package com.accolite.furlough.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.accolite.furlough.entity.MSEmployee;

public interface MSEmployeeRepository
        extends
        JpaRepository<MSEmployee, String> {}
