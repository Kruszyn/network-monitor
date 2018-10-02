package com.korek.odl.model.json;

import java.sql.Timestamp;

public class ChartData {
    private String date;
    private String value;

    public ChartData() {
    }

    public ChartData(String date, String value) {
        this.date = date;
        this.value = value;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
