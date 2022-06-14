package com.vpetrou.cs.acceptance.utils;

import com.vpetrou.cs.acceptance.api.ContactAPI;
import io.cucumber.datatable.DataTable;
import io.restassured.response.Response;

import java.util.List;

public class BaseTest {

    public static ContactAPI contactAPI = new ContactAPI();

    public static Response response;

    public static String API_URL;

    public static void setURL() {
        API_URL = System.getProperty("api.url");
        if (API_URL == null || API_URL.isEmpty()) {
            API_URL = "http://localhost:7001/api/v1";
        }
    }

    /*
     * |Label |Label2|
     * |Value |Value2|
     *
     * returns the value under every label
     */
    public String getValue(DataTable dTable, String label) {
        List<List<String>> rows = dTable.asLists();
        int column = getColumn(dTable, label);
        if (column >= 0) {
            return rows.get(1).get(column);
        } else
            return null;
    }

    private int getColumn(DataTable dTable, String label) {
        int column = 0;
        int k = 0;
        boolean flagNotFound = true;
        List<List<String>> rows = dTable.asLists();
        for (List<String> cell : rows) {
            for (int i = 0; i < cell.size(); i++) {
                if (rows.get(k).get(i).equalsIgnoreCase(label)) {
                    column = i;
                    flagNotFound = false;
                    break;
                }
            }
            k++;
        }
        if (flagNotFound) column = -1;
        return column;
    }

}
