package com.accolite.furlough.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accolite.furlough.entity.FurloughRequests;
import com.accolite.furlough.repository.FurloughRequestsRepository;
import com.accolite.furlough.utils.FurloughRequestsIDTracker;

@Service
public class ResourceConfirmedService {

    @Autowired
    private FurloughRequestsRepository furloughRequestsRepository;

    private static final Logger log = LoggerFactory.getLogger(FileStorageService.class);

    public void confirmer(final Map<String, List<String>> dateMap) {
        final SimpleDateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
        for (final Entry<String, List<String>> entry : dateMap.entrySet()) {
            final String mSID = entry.getKey();
            final List<String> dateListString = entry.getValue();
            for (final String date : dateListString) {

                FurloughRequests fr = null;
                try {
                    final Optional<FurloughRequests> value = furloughRequestsRepository
                            .findById(new FurloughRequestsIDTracker(mSID, formatter.parse(date)));
                    if (value.isPresent()) {
                        fr = value.get();
                        fr.setResourceConfirmed(true);
                        fr.setFurloughStatus("OVERLAPPED");
                        furloughRequestsRepository.save(fr);
                    }

                } catch (final ParseException e) {
                    log.error("Error while parsing with error message {}", e.getMessage());
                }

            }

        }
    }
}
