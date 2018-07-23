package com.accolite.furlough.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accolite.furlough.entity.FurloughData;
import com.accolite.furlough.parserinput.SendJavaMail;
import com.accolite.furlough.repository.MSEmployeeRepository;

@Service
public class MailService {

    @Autowired
    private MSEmployeeRepository msEmployeeRepository;

    public void sendMails(final Map<String, FurloughData> map) throws IOException {

        final List<String> dateList = new ArrayList<String>();

        for (final Entry<String, FurloughData> entry : map.entrySet()) {

            String finalString = "Dear " + entry.getValue().getResourceName() + "\n\n";
            final String mailStart = "This is start of mail \n\n";
            finalString = finalString + mailStart;
            final String mailEnd = "\nThis is end of mail";

            String email = "raunak.maheshwari@accoliteindia.com";
            if (msEmployeeRepository.findById(entry.getValue().getMSID()).isPresent())
                email = msEmployeeRepository.findById(entry.getValue().getMSID()).get().getEmail();
            if (email.equals("N/A"))
                email = "raunak.maheshwari@accoliteindia.com";
            email = "vignesh.b@accoliteindia.com";

            final Map<Date, String> requestDates = entry.getValue().getFurloughDates();
            dateList.clear();
            for (final Entry<Date, String> dateEntry : requestDates.entrySet()) {
                if (dateEntry.getValue().equals("PLANNED")) {
                    dateList.add(dateEntry.getKey().toString());
                }
            }
            final Iterator<String> it = dateList.iterator();
            while (it.hasNext())
                finalString = finalString + it.next() + "\n";
            finalString = finalString + mailEnd;
            System.out.println(finalString + "\n\n");

            final SendJavaMail m = new SendJavaMail(email, finalString);
            m.sendJavaMail();
        }
    }

}
