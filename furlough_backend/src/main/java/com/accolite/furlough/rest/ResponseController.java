package com.accolite.furlough.rest;

import java.util.List;
import java.util.Map;

import javax.ws.rs.QueryParam;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.accolite.furlough.utils.Encryption;

@RestController
public class ResponseController {

    // http:10.4.12.142:8080/furlough/verify?msid=GHDF23&dates=jlalhlseef20349023
    @RequestMapping("/verify")
    @ResponseBody
    public String update(@QueryParam(value = "msid") final String msid,
            @QueryParam(value = "token") final String token) {
        if (msid == null || token == null) // Error Handling to be done
            return "The link has expired. Please contact the product owner for help";

        final String decodedDates = Encryption.base64Decode(token);
        final String decodedMSID = Encryption.base64Decode(msid);
        final Map<String, List<String>> map = Encryption.getMailResponseMap(decodedMSID, decodedDates);
        // Function which will use the above map!
        return "Thank you for confirming your furlough dates";
    }

}
