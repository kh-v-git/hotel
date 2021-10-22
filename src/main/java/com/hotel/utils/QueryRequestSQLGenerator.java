package com.hotel.utils;

import java.util.List;

public class QueryRequestSQLGenerator {
    private static final String LIKE = "LIKE";
    private static final String OR = "OR";


    public String makeQuery(String sText, List<String> pList) {
        StringBuilder reString = new StringBuilder();
        boolean textIsEmpty = sText.isEmpty();
        boolean listIsEmpty = pList.isEmpty();

        if (!textIsEmpty || !listIsEmpty) {
            reString.append(" WHERE ");
            int i = 0;
            for (String searchParam : pList) {
                reString.append(searchParam.toLowerCase()).append(" ").append(LIKE).append(" ").append("'").append(sText).append("' ");
                i++;
                if (pList.size() > 2 || i < pList.size() - 1) {
                    reString.append(OR).append(" ");
                }
            }
        }
        return reString.toString();
    }

}
