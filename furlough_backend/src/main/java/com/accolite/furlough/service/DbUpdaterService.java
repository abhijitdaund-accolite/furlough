package com.accolite.furlough.service;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accolite.furlough.entity.FurloughData;
import com.accolite.furlough.entity.FurloughLog;
import com.accolite.furlough.entity.FurloughRequests;
import com.accolite.furlough.repository.FurloughLogRepository;
import com.accolite.furlough.repository.FurloughRequestsRepository;
import com.accolite.furlough.utils.FurloughRequestsIDTracker;

@Service
public class DbUpdaterService {

    @Autowired
    private FurloughLogRepository furloughLogRepository;

    @Autowired
    private FurloughRequestsRepository furloughRequestsRepository;

    public void updateDB(final List<FurloughLog> logList, final Map<String, FurloughData> map) {

        for (final Entry<String, FurloughData> entry : map.entrySet()) {
            final FurloughData tempObj = entry.getValue();
            final Map<Date, String> requestDates = tempObj.getFurloughDates();
            for (final Entry<Date, String> entry1 : requestDates.entrySet()) {

                final FurloughRequests fRequest = new FurloughRequests();
                fRequest.setMailSent(false);
                fRequest.setFurloughStatus(entry1.getValue());
                fRequest.setResourceConfirmed(false);
                final FurloughRequestsIDTracker fur = new FurloughRequestsIDTracker(entry.getKey(), entry1.getKey());

                fRequest.setFurloughID(fur);

                requestInserter(fRequest);
            }
        }

        final Iterator<FurloughLog> iterator = logList.iterator();
        while (iterator.hasNext()) {
            furloughLogRepository.save(iterator.next());
        }
    }

    public FurloughRequests requestInserter(final FurloughRequests request) {

        if (furloughRequestsRepository.existsById(request.getFurloughID())) {
            final FurloughRequests requestToBeUpdated = furloughRequestsRepository.getOne(request.getFurloughID());
            requestToBeUpdated.setFurloughStatus(request.getFurloughStatus());
            return furloughRequestsRepository.save(requestToBeUpdated);
        } else {
            return furloughRequestsRepository.save(request);
        }
    }
}
