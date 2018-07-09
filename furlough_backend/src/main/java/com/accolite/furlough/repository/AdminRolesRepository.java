package com.accolite.furlough.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accolite.furlough.entity.AccoliteEmployee;
import com.accolite.furlough.entity.AdminModel;


@Repository
public interface AdminRolesRepository extends JpaRepository<AdminModel,String>{

}