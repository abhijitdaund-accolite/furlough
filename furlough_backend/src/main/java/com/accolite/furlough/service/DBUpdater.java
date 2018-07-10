package com.accolite.furlough.service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Service;

import com.accolite.furlough.entity.FurloughData;
import com.accolite.furlough.entity.FurloughLog;
import com.accolite.furlough.entity.FurloughRequests;
import com.accolite.furlough.repository.FurloughRequestsRepository;
import com.accolite.furlough.utils.FurloughRequestsIDTracker;

@Service
public class DBUpdater {

    public static FurloughRequests requestInserter(final FurloughRequests request,
            final FurloughRequestsRepository obj) {
        System.out.println("Printing findByID : " + obj.findById(request.getFurloughID()));
        System.out.println("DBUpdater.requestInserter(): request: " + request);
        final Object a = obj.save(request);
        obj.flush();
        System.out.println("DBUpdater.requestInserter(): a: " + a);
        System.out.println(a);

        // return obj.findById(request.getFurloughID()).map(currentRequest -> {
        // currentRequest.setFurloughStatus(request.getFurloughStatus());
        // return obj.save(currentRequest);
        // }).orElse(obj.save(request));

        return null;

    }

    // public FurloughLog logInserter( FurloughLog request) {
    // return furloughLogRepository.save(request);
    // }

    public static FurloughRequests updateDB(final List<FurloughLog> logList, final Map<String, FurloughData> map,
            final FurloughRequestsRepository furloughRequestsRepository) {

        for (final Entry<String, FurloughData> entry : map.entrySet()) {
            final FurloughData tempObj = entry.getValue();
            System.out.println(tempObj);
            final Map<Date, String> requestDates = tempObj.getFurloughDates();
            for (final Entry<Date, String> entry1 : requestDates.entrySet()) {

                final FurloughRequests fRequest = new FurloughRequests();
                fRequest.setMailSent(false);
                fRequest.setFurloughStatus(entry1.getValue());
                fRequest.setResourceConfirmed(false);
                final FurloughRequestsIDTracker fur = new FurloughRequestsIDTracker(entry.getKey(), entry1.getKey());

                fRequest.setFurloughID(fur);
                System.out.println(fRequest);
                requestInserter(fRequest, furloughRequestsRepository);
            }
        }
        return null;
    }

}
