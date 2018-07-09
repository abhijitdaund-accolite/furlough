package com.accolite.furlough.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;


import com.accolite.furlough.entity.AccoliteEmployee;

@Repository
public interface AccoliteEmployeeRepository extends JpaRepository<AccoliteEmployee,String>{
	
}
