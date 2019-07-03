package com.accolite.furlough.service;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accolite.furlough.entity.FurloughRequests;
import com.accolite.furlough.repository.FurloughRequestsRepository;
import com.accolite.furlough.utils.FurloughRequestsIDTracker;

@Service
public class ResourceConfirmedService {

    @Autowired
    private FurloughRequestsRepository furloughRequestsRepository;

    public void confirmer(final Map<String, List<String>> dateMap) throws Exception {
        final SimpleDateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
        for (final Entry<String, List<String>> entry : dateMap.entrySet()) {
            final String mSID = entry.getKey();
            final List<String> dateListString = entry.getValue();
            for (final String date : dateListString) {

                final FurloughRequests fr = furloughRequestsRepository
                        .findById(new FurloughRequestsIDTracker(mSID, formatter.parse(date))).get();
                fr.setResourceConfirmed(true);
                fr.setFurloughStatus("OVERLAPPED");
                furloughRequestsRepository.save(fr);

            }

        }
    }
}
