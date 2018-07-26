package com.accolite.furlough.utils;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmailUtil {

    public static String base64Encode(final String token) {
        final byte[] encodedBytes = Base64.getEncoder().encode(token.getBytes());
        return new String(encodedBytes, Charset.forName("UTF-8"));
    }

    public static String base64Decode(final String token) {
        final byte[] decodedBytes = Base64.getDecoder().decode(token.getBytes());
        return new String(decodedBytes, Charset.forName("UTF-8"));
    }

    public static List<String> getDateList(final String dateString) {
        final String[] splitString = dateString.split("!");
        final List<String> dateList = new ArrayList<String>();
        for (int i = 0; i < splitString.length; i++)
            dateList.add(splitString[i]);
        return dateList;
    }

    public static Map<String, List<String>> getMailResponseMap(final String MSID, final String dateString) {
        final List<String> dateList = getDateList(dateString);
        final Map<String, List<String>> responseMap = new HashMap<String, List<String>>();
        responseMap.put(MSID, dateList);
        return responseMap;
    }

    public static void main(final String[] args) throws Exception {
        final String dates = "Tue Jul 03 00:00:00 IST 2018!Thu Jul 05 00:00:00 IST 2018!Wed Jul 04 00:00:00 IST 2018!Mon Jul 02 00:00:00 IST 2018!";
        System.out.println(base64Encode(dates));
        System.out.println(getMailResponseMap("raunak", dates));
    }
}
