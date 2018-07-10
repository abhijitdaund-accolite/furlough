package com.accolite.furlough.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accolite.furlough.entity.FurloughLog;
import com.accolite.furlough.repository.FurloughLogRepository;

@RestController
public class FurloughLogController {

    @Autowired
    private FurloughLogRepository furloughLogRepository;

    @RequestMapping("/logs/{MSID}")
    List<FurloughLog> getFurloughLogs(@PathVariable final String MSID) {
        return furloughLogRepository.findByMSID(MSID);
    }

    @GetMapping("/logs")
    List<FurloughLog> getLogs() {
        return furloughLogRepository.findAll();
    }
}
