package com.accolite.furlough.utils;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmailUtil {

    private EmailUtil() {

    }

    public static String base64Encode(final StringBuilder dateString) {
        final byte[] encodedBytes = Base64.getEncoder().encode(String.valueOf(dateString).getBytes());
        return new String(encodedBytes, Charset.forName(Constants.UTF_8));
    }

    public static String base64Encode(final String dateString) {
        final byte[] encodedBytes = Base64.getEncoder().encode(String.valueOf(dateString).getBytes());
        return new String(encodedBytes, Charset.forName(Constants.UTF_8));
    }

    public static String base64Decode(final String token) {
        final byte[] decodedBytes = Base64.getDecoder().decode(token.getBytes());
        return new String(decodedBytes, Charset.forName(Constants.UTF_8));
    }

    public static List<String> getDateList(final String dateString) {
        final String[] splitString = dateString.split("!");
        final List<String> dateList = new ArrayList<>();
        for (int i = 0; i < splitString.length; i++)
            dateList.add(splitString[i]);
        return dateList;
    }

    public static Map<String, List<String>> getMailResponseMap(final String MSID, final String dateString) {
        final List<String> dateList = getDateList(dateString);
        final Map<String, List<String>> responseMap = new HashMap<>();
        responseMap.put(MSID, dateList);
        return responseMap;
    }

}
