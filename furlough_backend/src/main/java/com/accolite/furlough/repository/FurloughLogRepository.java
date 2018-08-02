package com.accolite.furlough.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accolite.furlough.entity.FurloughLog;

@Repository
public interface FurloughLogRepository
        extends
        JpaRepository<FurloughLog, String> {
    List<FurloughLog> findByMSID(String mSID);
}
