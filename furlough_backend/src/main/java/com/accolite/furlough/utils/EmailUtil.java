package com.accolite.furlough.utils;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

//import com.accolite.email.EmailBuilder;

public class EmailUtil {

    /*final String serviceAccount = "service-account-automatic-mail@helpdesk-v00001.iam.gserviceaccount.com";
    final String pkcsFilePath = "HelpDesk-e34f4325ab39.p12";// src//main//resources//
    final String serviceUser = "furlough@accoliteindia.com";
    final String applicationName = "Accolite Furlough Tracker";

    private final EmailBuilder emailBuilder;
    private static EmailUtil emailUtil;

    private EmailUtil() throws URISyntaxException {

        final URL pkcsFilePathUrl = this.getClass().getClassLoader().getResource(pkcsFilePath);
        emailBuilder = EmailBuilder.createEmailBuilder(new File(pkcsFilePathUrl.toURI()), serviceUser, serviceAccount,
                applicationName);
    }

    public static EmailUtil getInstance() throws URISyntaxException {
        if (emailUtil == null) {
            synchronized (EmailUtil.class) {
                if (emailUtil == null) {
                    emailUtil = new EmailUtil();
                }
            }
        }
        return emailUtil;
    }

    public EmailBuilder getEmailBuilder() {
        return emailBuilder;
    }*/

}
