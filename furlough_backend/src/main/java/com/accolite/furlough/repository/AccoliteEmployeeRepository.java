package com.accolite.furlough.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accolite.furlough.entity.AccoliteEmployee;

@Repository
public interface AccoliteEmployeeRepository
        extends
        JpaRepository<AccoliteEmployee, String> {
    AccoliteEmployee findByMSID(String mSId);

    List<AccoliteEmployee> findByMSIDIn(List<String> mSID);
}
