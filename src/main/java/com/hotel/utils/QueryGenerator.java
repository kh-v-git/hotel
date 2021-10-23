package com.hotel.utils;

import java.util.List;

public class QueryGenerator {
    private static final String LIKE = "LIKE";
    private static final String OR = "OR";


    public static String makeQuery(String sText, List<String> pList) {
        StringBuilder reString = new StringBuilder();
        boolean textIsEmpty = sText.isEmpty();
        boolean listIsEmpty = pList.isEmpty();

        if (!textIsEmpty || !listIsEmpty) {
            reString.append(" WHERE ");
            int i = 0;
            for (String searchParam : pList) {
                reString.append("LOWER(").append(searchParam).append(")").append(" ").append(LIKE).append(" ").append("LOWER('%").append(sText).append("%')");
                i++;
                if (pList.size() > 1 && i < pList.size()) {
                    reString.append(" ").append(OR).append(" ");
                }
            }
        }
        return reString.toString();
    }

}
