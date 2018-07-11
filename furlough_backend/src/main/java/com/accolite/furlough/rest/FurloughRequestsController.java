package com.accolite.furlough.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.accolite.furlough.entity.FurloughRequests;
import com.accolite.furlough.repository.FurloughRequestsRepository;

@RestController
public class FurloughRequestsController {

    @Autowired
    private FurloughRequestsRepository furloughRequestsRepository;

    @GetMapping("/requests")
    @ResponseBody
    public List<FurloughRequests> getFurloughRequests() {
        return furloughRequestsRepository.findAll();
    }

    @GetMapping("/requests/{mSID}")
    @ResponseBody
    public List<FurloughRequests> getFurLoughRequestsById(@PathVariable final String mSID) {
        return furloughRequestsRepository.findByfurloughID_mSID(mSID);
    }
}
