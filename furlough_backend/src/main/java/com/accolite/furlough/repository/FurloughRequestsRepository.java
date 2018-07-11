package com.accolite.furlough.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accolite.furlough.entity.FurloughRequests;
import com.accolite.furlough.utils.FurloughRequestsIDTracker;

@Repository
public interface FurloughRequestsRepository
        extends
        JpaRepository<FurloughRequests, FurloughRequestsIDTracker> {
    List<FurloughRequests> findByfurloughID_mSID(String mSID);
}
