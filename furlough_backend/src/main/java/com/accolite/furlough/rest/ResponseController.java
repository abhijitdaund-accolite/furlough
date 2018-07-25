package com.accolite.furlough.rest;

import java.util.List;
import java.util.Map;

import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.accolite.furlough.service.ResourceConfirmedService;
import com.accolite.furlough.utils.EmailUtil;

@RestController
public class ResponseController {
    @Autowired
    private ResourceConfirmedService ResourceConfirmer;

    // http:10.4.12.142:8080/furlough/verify?msid=GHDF23&dates=jlalhlseef20349023
    @RequestMapping("/verify")
    @ResponseBody
    public String update(@QueryParam(value = "msid") final String msid, @QueryParam(value = "token") final String token)
            throws Exception {
        if (msid == null || token == null) // Error Handling to be done
            return "The link has expired. Please contact the product owner for help";

        final String decodedDates = EmailUtil.base64Decode(token);
        final String decodedMSID = EmailUtil.base64Decode(msid);
        final Map<String, List<String>> map = EmailUtil.getMailResponseMap(decodedMSID, decodedDates);
        // Function which will use the above map!
        ResourceConfirmer.confirmer(map);
        return "successFully confirmed in the database";
    }

}
