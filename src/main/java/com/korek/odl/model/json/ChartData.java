package com.korek.odl.model.json;

import java.sql.Timestamp;

public class ChartData {
    private Long date;
    private Long value;

    public ChartData() {
    }

    public ChartData(Long date, Long value) {
        this.date = date;
        this.value = value;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

}
