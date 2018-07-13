package com.accolite.furlough.rest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accolite.furlough.dto.DateAndOverlap;
import com.accolite.furlough.dto.FurloughReport;
import com.accolite.furlough.entity.FurloughRequests;
import com.accolite.furlough.entity.MSEmployee;
import com.accolite.furlough.repository.FurloughRequestsRepository;
import com.accolite.furlough.repository.MSEmployeeRepository;
import com.accolite.furlough.utils.FurloughRequestsIDTracker;

@RestController
public class FurloughRequestsController {

    @Autowired
    private FurloughRequestsRepository furloughRequestRepository;

    @Autowired
    private MSEmployeeRepository msEmployeeRepository;

    // http://10.4.12.118:8080/furlough/requests?from=2014-01-01&to=2019-07-01
    @RequestMapping("/requests")
    public List<FurloughReport> getRequestInDateRange(@QueryParam(value = "from") String from,
            @QueryParam(value = "to") String to) {
        final SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date sDate;
        java.util.Date eDate;

        // If no query parameters are passed, we set a default date
        if (from == null || to == null) {
            from = "2014-01-01";
            to = "2020-01-01";
        }

        try {

            sDate = parser.parse(from);
            eDate = parser.parse(to);

        } catch (final ParseException e) {
            return null;
        }

        final List<FurloughRequests> totalFurloughsInRange = this.furloughRequestRepository
                .findByFurloughID_furloughDateBetween(sDate, eDate);
        final Set<String> distinctMSIDs = new HashSet();
        final List<FurloughReport> listReports = new ArrayList();

        final Iterator<FurloughRequests> itr = totalFurloughsInRange.iterator();
        final Map<String, ArrayList<DateAndOverlap>> datesMap = new HashMap();

        while (itr.hasNext()) {
            final FurloughRequests requests = itr.next();
            System.out.println(requests);

            final FurloughRequestsIDTracker idTracker = requests.getFurloughID();
            distinctMSIDs.add(idTracker.getmSID());

            if (!datesMap.containsKey(idTracker.getmSID()))
                datesMap.put(idTracker.getmSID(), new ArrayList<DateAndOverlap>());

            final List<DateAndOverlap> curentDateAndOverlap = datesMap.get(idTracker.getmSID());
            curentDateAndOverlap.add(new DateAndOverlap(idTracker.getFurloughDate(), requests.getFurloughStatus()));
            datesMap.put(idTracker.getmSID(), (ArrayList<DateAndOverlap>) curentDateAndOverlap);
        }

        final Iterator<String> itrMSIDs = distinctMSIDs.iterator();

        while (itrMSIDs.hasNext()) {

            final String MSID = itrMSIDs.next();
            if (msEmployeeRepository.existsById(MSID)) {
                final MSEmployee emp = this.msEmployeeRepository.findById(MSID).get();
                final FurloughReport report = new FurloughReport(emp.getMSID(), datesMap.get(MSID),
                        emp.getResourceName(), emp.getVendorName()

                );

                listReports.add(report);

            }
        }

        return listReports;

    }
}
