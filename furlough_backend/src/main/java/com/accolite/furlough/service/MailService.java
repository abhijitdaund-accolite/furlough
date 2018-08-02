package com.accolite.furlough.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accolite.furlough.entity.FurloughData;
import com.accolite.furlough.entity.MSEmployee;
import com.accolite.furlough.parserinput.SendJavaMail;
import com.accolite.furlough.repository.MSEmployeeRepository;
import com.accolite.furlough.utils.Constants;
import com.accolite.furlough.utils.EmailUtil;

@Service
public class MailService {

    @Autowired
    private MSEmployeeRepository msEmployeeRepository;

    public void sendMails(final Map<String, FurloughData> map) {

        final List<String> dateList = new ArrayList<>();

        for (final Entry<String, FurloughData> entry : map.entrySet()) {

            StringBuilder finalString = new StringBuilder("Dear " + entry.getValue().getResourceName() + "\n\n");
            final String mailStart = "This is start of mail \n\n";
            finalString = finalString.append(mailStart);
            final String mailEnd = "\nThis is end of mail\n";

            String email = "vignesh.b@accoliteindia.com";

            final Optional<MSEmployee> employeeOptional = msEmployeeRepository.findById(entry.getValue().getMSID());
            String emailTemp = "raunak.maheshwari@accoliteindia.com";
            if (employeeOptional.isPresent())
                emailTemp = employeeOptional.get().getEmail();
            if (emailTemp.equals("N/A"))
                email = "raunak.maheshwari@accoliteindia.com";
            email = "vignesh.b@accoliteindia.com";
            final StringBuilder dateString = new StringBuilder();
            final Map<Date, String> requestDates = entry.getValue().getFurloughDates();
            dateList.clear();
            for (final Entry<Date, String> dateEntry : requestDates.entrySet()) {
                if (dateEntry.getValue().equals("PLANNED")) {
                    dateString.append(dateEntry.getKey().toString() + "!");
                    dateList.add(dateEntry.getKey().toString());
                }
            }
            final Iterator<String> it = dateList.iterator();
            while (it.hasNext())
                finalString.append(it.next() + "\n");

            String confirmLink = "Please click on the following link to confirm the above.";
            confirmLink += "\n\n" + Constants.HOST_URL + "verify/?msid="
                    + EmailUtil.base64Encode(entry.getValue().getMSID());
            confirmLink += "&token=" + EmailUtil.base64Encode(dateString);
            finalString.append("\n" + confirmLink + mailEnd);

            final SendJavaMail m = new SendJavaMail(email, finalString);
            m.sendJavaMail();
        }
    }

}
