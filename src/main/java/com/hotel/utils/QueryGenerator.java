package com.hotel.utils;

import java.util.List;

/**
 * QueryGenerator makes part of SQL query from input search arg and list of
 * column names
 */
public class QueryGenerator {
    private static final String LIKE = "LIKE";
    private static final String OR = "OR";
    /**
     *
     * @param sText search text
     * @param pList list of column name(s) for search
     * @return part of query String
     */
    public static String makeQuery(String sText, List<String> pList) {
        StringBuilder reString = new StringBuilder();
        boolean textIsEmpty = sText.isEmpty();
        boolean listIsEmpty = pList.isEmpty();

        if (!textIsEmpty && !listIsEmpty) {
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
