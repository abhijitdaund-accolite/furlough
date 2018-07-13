package com.accolite.furlough.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accolite.furlough.entity.Admin;

@Repository
public interface AdminRolesRepository
        extends
        JpaRepository<Admin, String> {

}
